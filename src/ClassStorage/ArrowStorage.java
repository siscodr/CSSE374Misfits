package ClassStorage;

public class ArrowStorage {
	private String targetType;
	private String label = "";
	private String color = "";

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

	public String getColor() {
		if (color != "") {
			return "color=\"" + color + "\" ";
		}
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
