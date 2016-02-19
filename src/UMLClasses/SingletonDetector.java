package UMLClasses;

import java.util.ArrayList;

import jdk.internal.org.objectweb.asm.Opcodes;
import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;
import ClassStorage.PatternStorage;

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

	public SingletonDetector(Object threshold){
		this.color = "orange";
		this.fillColor = "purple";
		this.isDetected = false;
		this.pattern = "Singleton";
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
		String className = currentClass.getClassName();
		String headClass = null;
		ArrayList<String> classes = new ArrayList<String>();
		for (FieldStorage field : currentClass.getFields()) {
			if (field.getType().equals(className) && field.getAccess() == (Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC)) {
				headClass = className;
				classes.add(headClass);
				setDetected(true);
			}
		}
		if(isDetected()){
			UMLArrows.getInstance().addPattern(new PatternStorage(this.pattern, headClass, classes));
		}
	}
}
