package MisfitsPackage;

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
	@Override
	public String getPattern() {
		return pattern;
	}
	@Override
	public String getColor() {
		return color;
	}
	@Override
	public String getFillColor() {
		return fillColor;
	}
	@Override
	public boolean isDetected() {
		return isDetected;
	}
	@Override
	public void setDetected(boolean detected) {
		isDetected = detected;
		
	}


}
