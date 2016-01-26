package MisfitsPackage;

public interface PatternDetector {

	public String getPattern();
	public String getColor();
	public String getFillColor();
	public boolean isDetected();
	public void setDetected(boolean detected);

}
