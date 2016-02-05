package UMLClasses;

import java.util.ArrayList;

import ClassStorage.ClassContainer;
import ClassStorage.MethodStorage;
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

	public String getPattern() {
		return pattern;
	}

	public String getColor() {
		return color;
	}

	public String getFillColor() {
		return fillColor;
	}

	public boolean isDetected() {
		return isDetected;
	}

	public void setDetected(boolean detected) {
		isDetected = detected;
	}

	public void detect(ClassContainer currentClass) {
		if (currentClass.getSupers() == null) {
			return;
		}
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
	}

}
