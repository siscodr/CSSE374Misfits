package ClassStorage;

public class ArrowStorage {
	private String targetType;
	private String label = "";

	public ArrowStorage(String targetType) {
		super();
		this.targetType = targetType;
	}

	public String getTargetType() {
		return targetType;
	}

	public String getLabel() {
		if(this.label != ""){
			return "label = \" " + this.label +"\",";
		}
		return "";
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
