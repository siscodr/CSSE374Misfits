package UMLClasses;

public class FieldStorage {
	private int access;
	private String type;

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

}
