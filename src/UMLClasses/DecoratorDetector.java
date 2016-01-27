package UMLClasses;

import ClassStorage.ClassContainer;

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
		// TODO Auto-generated method stub
		// if(className.equals(methodName) && desc.contains(interfaceName)){
		// setDetected(true);
		// }
	}

}
