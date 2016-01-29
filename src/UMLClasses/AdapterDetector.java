package UMLClasses;

import java.util.ArrayList;

import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;
import ClassStorage.MethodFieldsStorage;

public class AdapterDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;
	private FieldStorage adapteeField;

	public AdapterDetector(String color, String fillColor) {
		this.color = color;
		this.fillColor = fillColor;
		this.isDetected = false;
		this.pattern = "Adapter";
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
		if (checkFields(currentClass)) {
			setDetected(true);
		}
	}

	private boolean checkFields(ClassContainer currentClass) {
		ArrayList<MethodFieldsStorage> methods = currentClass.getMethodsField();
		boolean fieldInMethods = false;
		for (FieldStorage field : currentClass.getFields()) {
			if (!field.getType().equals(currentClass.getClassName())) {
				boolean adaptee = true;
				for (MethodFieldsStorage method : methods) {
					boolean fieldInMethod = false;
					for (String type : method.getFields()) {
						//System.out.println(field.getType() + " : is in the method: " + type);
						if (field.getType().equals(type)) {
							fieldInMethod = true;
							//System.out.println(field.getType() + " : PASSED : " + method.getName());
						}
					}
					if (!fieldInMethod) {
						adaptee = false;
					}
				}
				if (adaptee) {
					fieldInMethods = true;
					adapteeField = field;
					//System.out.println("I am the adaptee: " + field.getType());
				}
			}
		}
		return fieldInMethods;
	}

}
