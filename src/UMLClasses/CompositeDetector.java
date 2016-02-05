package UMLClasses;

import java.util.ArrayList;

import ClassStorage.ClassContainer;

public class CompositeDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;
	
	public CompositeDetector(String color, String fillColor) {
		this.color = color;
		this.fillColor = fillColor;
		this.isDetected = false;
		this.pattern = "Composite";
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
	@Override
	public void detect(ClassContainer currentClass) {
		if(currentClass.getIsInterface()){
			return;
		}
		ArrayList<String> possibleComponents = currentClass.getCollectionDataTypes();
		if(possibleComponents.size() == 0) {
			return;
		}
		
		

	}

}
