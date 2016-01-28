package ClassStorage;

public class ArrowStorage {
	private String targetType;
	private String label;

	public ArrowStorage(String targetType) {
		super();
		this.targetType = targetType;
		this.label = "";
	}

	public String getTargetType() {
		return targetType;
	}

	public String getLabel() {
		if(label != ""){
			return "label = \" " + label +"\"";
		}
		return label;
	}
}
