package UMLClasses;

import java.util.ArrayList;

import ClassStorage.ArrowStorage;
import ClassStorage.ClassContainer;

public class CompositeDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;
	private String component;

	public CompositeDetector(String color, String fillColor) {
		this.color = color;
		this.fillColor = fillColor;
		this.isDetected = false;
		this.pattern = "Composite";
	}

	public String getPattern() {
		return this.pattern;
	}

	public String getColor() {
		return this.color;
	}

	public String getFillColor() {
		return this.fillColor;
	}

	public boolean isDetected() {
		return this.isDetected;
	}

	public void reset() {
		setDetected(false);
	}
	
	private void setDetected(boolean detected) {
		this.isDetected = detected;
	}

	public void detect(ClassContainer currentClass) {
		findComponent(currentClass);
		
		if (!this.isDetected) {
			return;
		}
		labelComponent();
		findLeaves(currentClass.getClassName());
	}

	private void labelComponent() {
		for (ClassContainer currentClass : UMLArrows.getInstance().getClasses()) {
			if (currentClass.getClassName().equals(this.component)) {
				currentClass.setLabel("Component");
				return;
			}
		}
		System.out.println("ERROR: Component Not Found");
	}

	private void findLeaves(String currentClassName) {
		ArrayList<String> leaves = new ArrayList<String>();
		ArrayList<ClassContainer> classes = UMLArrows.getInstance().getClasses();
		for (ClassContainer cls : classes) {
			if (!cls.getIsInterface() && !cls.getLabel().contains("Composite")
					&& !cls.getLabel().contains("Component")) {
				boolean isLeaf = false;
				ArrayList<ArrowStorage> parents = new ArrayList<ArrowStorage>();
				if (cls.getSupers() != null) {
					parents.add(cls.getSupers());
				}
				for (ArrowStorage arrow : cls.getInterfaces()) {
					parents.add(arrow);
				}
				while (parents.size() != 0 && !isLeaf) {
					for (ArrowStorage parent : parents) {
						if (parent.getTargetType().equals(this.component)) {
							leaves.add(cls.getClassName());
							isLeaf = true;
						}
					}
					if (!isLeaf) {
						parents = getNextGeneration(parents);
					}
				}
			}
		}
		labelLeaves(leaves);
	}

	private void labelLeaves(ArrayList<String> leaves) {
		for (String leaf : leaves) {
			for (ClassContainer currentClass : UMLArrows.getInstance().getClasses()) {
				if (currentClass.getClassName().equals(leaf)) {
					currentClass.setLabel("Leaf");
				}
			}
		}
	}

	private void findComponent(ClassContainer currentClass) {
		if (currentClass.getIsInterface()) {
			return;
		}
		ArrayList<String> possibleComponents = currentClass.getCollectionDataTypes();
		if (possibleComponents.size() == 0) {
			return;
		}
		ArrayList<ArrowStorage> parents = new ArrayList<ArrowStorage>();
		if (currentClass.getSupers() != null) {
			parents.add(currentClass.getSupers());
		}
		for (ArrowStorage interfaces : currentClass.getInterfaces()) {
			parents.add(interfaces);
		}
		while (parents.size() != 0 && !this.isDetected) {
			for (ArrowStorage parent : parents) {
				for (String type : possibleComponents) {
					if (parent.getTargetType().equals(type)) {
						this.setDetected(true);
						this.component = type;
					}
				}
			}
			if (!this.isDetected) {
				parents = getNextGeneration(parents);
			}
		}
	}

	private ArrayList<ArrowStorage> getNextGeneration(ArrayList<ArrowStorage> parents) {
		ArrayList<ArrowStorage> toReturn = new ArrayList<ArrowStorage>();
		for (ArrowStorage parent : parents) {
			ClassContainer parentClass = null;
			for (ClassContainer tempClass : UMLArrows.getInstance().getClasses()) {
				if (tempClass.getClassName().equals(parent.getTargetType())) {
					parentClass = tempClass;
				}
			}
			if (parentClass != null) {
				if (parentClass.getSupers() != null) {
					toReturn.add(parentClass.getSupers());
				}
				for (ArrowStorage interfaces : parentClass.getInterfaces()) {
					toReturn.add(interfaces);
				}
			}
		}
		return toReturn;
	}
}
