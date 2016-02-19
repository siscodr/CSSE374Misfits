package UMLClasses;

import java.util.ArrayList;

import ClassStorage.ClassContainer;
import ClassStorage.MethodStorage;
import ClassStorage.PatternStorage;
import MisfitsPackage.WorkerForArrows;

public class DecoratorDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;

	public DecoratorDetector(String color, String fillColor) {
		this.color = color;
		this.fillColor = fillColor;
		this.isDetected = false;
		this.pattern = "Decorator";
	}
	
	public DecoratorDetector(Object threshold){
		this.color = "green";
		this.fillColor = "blue";
		this.isDetected = false;
		this.pattern = "Decorator";
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
		if (currentClass.getSupers() == null) {
			return;
		}
		ArrayList<String> classes = new ArrayList<String>();
		String extension = currentClass.getSupers().getTargetType();
		while (extension != null) {
			// System.out.println("This is our Extension: " + extension);
			if (!extension.equals("")) {
				for (MethodStorage method : currentClass.getMethods()) {
					if (method.getName().equals("<init>")) {
						for (String param : WorkerForArrows.getTypesFromDesc(method.getDesc())) {
							if (WorkerForArrows.stripFunction(param).equals(extension)) {
								for (ClassContainer tempclass : UMLArrows.getInstance().getClasses()) {
									if (tempclass.getClassName().equals(extension)) {
										tempclass.setLabel("Component");
										classes.add(tempclass.getClassName());
									}
								}
								setDetected(true);
							}
						}
					}
				}
				if (isDetected) {
					currentClass.getSupers().setLabel("<<Decorates>>");
					break;
				}
				ArrayList<ClassContainer> possibleClasses = UMLArrows.getInstance().getClasses();
				for (ClassContainer cls : possibleClasses) {
					if (cls.getClassName().equals(extension)) {
						if (cls.getSupers() != null) {
							extension = cls.getSupers().getTargetType();
						} else {
							extension = null;
						}
					}
				}
			}
		}
		// if(currentClass.getClassName().equals(methodName) &&
		// desc.contains(interfaceName)){

		// }
		if(isDetected()){
			classes.add(currentClass.getClassName());
			UMLArrows.getInstance().addPattern(new PatternStorage(this.pattern, currentClass.getClassName(), classes));
		}
	}
}
