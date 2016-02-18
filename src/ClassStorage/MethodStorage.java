package ClassStorage;

import java.util.ArrayList;

public class MethodStorage {
	private String name;
	private String desc;
	private int access;
	private ArrayList<MethodCallStorage> methodCalls;

	public MethodStorage(String name, String desc, int access, ArrayList<MethodCallStorage> methodCalls) {
		super();
		this.name = name;
		this.access = access;
		this.desc = desc;
		this.methodCalls = methodCalls;
	}

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.desc;
	}

	public int getAccess() {
		return this.access;
	}
	
	public ArrayList<MethodCallStorage> getMethodCalls(){
		return this.methodCalls;
	}

}
