package UMLClasses;

public class MethodStorage {
	private String name;
	private String desc;
	private int access;

	public MethodStorage(String name, String desc, int access) {
		super();
		this.name = name;
		this.access = access;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public int getAccess() {
		return access;
	}

}
