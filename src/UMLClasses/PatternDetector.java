package UMLClasses;

import ClassStorage.ClassContainer;

public interface PatternDetector {

	public String getPattern();

	public String getColor();

	public String getFillColor();
	
	public void detect(ClassContainer currentClass);

	public boolean isDetected();
	
	//TODO: set to private method once detect is fully implemented.
	public void setDetected(boolean detected);

}
