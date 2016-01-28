package ClassStorage;

public class FieldStorage {
	private int access;
	private String type;
	private String label;

	public FieldStorage(int access, String type) {
		super();
		this.access = access;
		this.type = type;
	}

	public int getAccess() {
		return access;
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

	public void setLabel(String label) {
		this.label = label;
	}

}
