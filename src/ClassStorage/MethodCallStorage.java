package ClassStorage;

public class MethodCallStorage {
	private String name;
	private String owner;
	private String desc;
	
	public MethodCallStorage(String name, String owner, String desc) {
		super();
		this.name = name;
		this.owner = owner;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public String getDesc() {
		return desc;
	}
}
