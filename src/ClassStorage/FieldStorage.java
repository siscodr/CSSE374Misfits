package ClassStorage;

public class FieldStorage {
	private int access;
	private String name;
	private String type;
	private String label;
	private String color = "";

	public FieldStorage(int access, String type) {
		super();
		this.access = access;
		this.type = type;
		this.name = null;
		
	}

	public FieldStorage(int access, String type, String name) {
		super();
		this.access = access;
		this.type = type;
		this.name = name;
	}
	
	public int getAccess() {
		return access;
	}

	public String getName(){
		return this.name;
	}
	
	public String getType() {
		return type;
	}

	public String getLabel() {
		if (this.label != null) {
			return "label = \" " + label + "\"";
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
