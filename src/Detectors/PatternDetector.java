package Detectors;

import ClassStorage.ClassContainer;

public interface PatternDetector {

	public String getPattern();

	public String getColor();

	public String getFillColor();
	
	public void detect(ClassContainer currentClass);

	public boolean isDetected();
	
	public void reset();

}
