package UMLClasses;

public interface PatternDetector {

	public String getPattern();

	public String getColor();

	public String getFillColor();
	
	public void detect(String className, String desc, int access);

	public boolean isDetected();
	
	//TODO: set to private method once detect is fully implemented.
	public void setDetected(boolean detected);

}
