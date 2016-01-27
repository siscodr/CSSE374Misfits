package UMLClasses;

public class AdapterDetector implements PatternDetector {
	private String color;
	private String pattern;
	private String fillColor;
	private boolean isDetected;
	
	public AdapterDetector(String color, String fillColor){
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

	public void detect(String className, String desc, int access) {
		// TODO Auto-generated method stub
		
	}

}
