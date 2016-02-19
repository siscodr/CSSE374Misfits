package Detectors;

import java.util.ArrayList;

import UMLClasses.UMLArrows;
import ClassStorage.ArrowStorage;
import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;
import ClassStorage.MethodFieldsStorage;
import ClassStorage.PatternStorage;

public class AdapterDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;
	private ArrayList<FieldStorage> adapteeField;
	private ArrayList<String> classes;

	public AdapterDetector(String color, String fillColor) {
		this.color = color;
		this.fillColor = fillColor;
		this.isDetected = false;
		this.pattern = "Adapter";
		this.adapteeField = new ArrayList<FieldStorage>();
		this.classes = new ArrayList<String>();
	}
	
	public AdapterDetector(Object threshold){
		this.color = "deeppink";
		this.fillColor = "grey40";
		this.isDetected = false;
		this.pattern = "Adapter";
		this.adapteeField = new ArrayList<FieldStorage>();
		this.classes = new ArrayList<String>();
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

	public void reset() {
		setDetected(false);
		this.adapteeField = new ArrayList<FieldStorage>();
		this.classes = new ArrayList<String>();
	}

	private void setDetected(boolean detected) {
		this.isDetected = detected;
	}

	public void detect(ClassContainer currentClass) {
		if (!currentClass.getLabel().contains("Decorator")) {
			if (checkFields(currentClass) && checkTarget(currentClass)) {
				setDetected(true);
				for (FieldStorage field : this.adapteeField) {
					labelAdaptees(field);
				}
			}
		}
		if(isDetected()){
			classes.add(currentClass.getClassName());
			UMLArrows.getInstance().addPattern(new PatternStorage(this.pattern, currentClass.getClassName(), this.classes));
		}
	}

	private boolean checkTarget(ClassContainer currentClass) {
		boolean isAdapter = false;
		for (ArrowStorage inter : currentClass.getInterfaces()) {
			isAdapter = true;
			labelTargets(inter);
		}
		if (currentClass.getSupers() != null) {
			isAdapter = true;
			labelTargets(currentClass.getSupers());
		}
		return isAdapter;
	}

	private void labelTargets(ArrowStorage arrows) {
		for (ClassContainer currentClass : UMLArrows.getInstance().getClasses()) {
			if (currentClass.getClassName().equals(arrows.getTargetType())) {
				currentClass.setLabel("Target");
				this.classes.add(currentClass.getClassName());
			}
		}
	}

	private void labelAdaptees(FieldStorage field) {
		field.setLabel("<<Adapts>>");
		for (ClassContainer currentClass : UMLArrows.getInstance().getClasses()) {
			if (currentClass.getClassName().equals(field.getType())) {
				currentClass.setLabel("Adaptee");
				this.classes.add(currentClass.getClassName());
			}
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
						// System.out.println(field.getType() +
						// " : is in the method: " + type);
						if (field.getType().equals(type)) {
							fieldInMethod = true;
							// System.out.println(field.getType() +
							// " : PASSED : " + method.getName());
						}
					}
					if (!fieldInMethod) {
						adaptee = false;
					}
				}
				if (adaptee) {
					fieldInMethods = true;
					adapteeField.add(field);
					// System.out.println("I am the adaptee: " +
					// field.getType());
				}
			}
		}
		return fieldInMethods;
	}

}
