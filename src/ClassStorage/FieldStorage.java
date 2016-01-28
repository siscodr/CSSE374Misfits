package ClassStorage;

public class FieldStorage {
	private int access;
	private String type;
	private String label;

	public FieldStorage(int access, String type) {
		super();
		this.access = access;
		this.type = type;
		this.label = "";
	}

	public int getAccess() {
		return access;
	}

	public String getType() {
		return type;
	}
	
	public String getLabel() {
		if(label != ""){
			return "label = \" " + label +"\"";
		}
		return label;
	}


}
