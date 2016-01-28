package UMLClasses;

import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;
import jdk.internal.org.objectweb.asm.Opcodes;

public class SingletonDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;

	public SingletonDetector(String color, String fillColor) {
		this.color = color;
		this.fillColor = fillColor;
		this.isDetected = false;
		this.pattern = "Singleton";
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
		String className = currentClass.getClassName();
		for (FieldStorage field : currentClass.getFields()) {
			if (field.getType().equals(className) && field.getAccess() == (Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC)) {
				setDetected(true);
			}
		}
	}

}
