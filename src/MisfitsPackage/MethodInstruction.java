package MisfitsPackage;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.org.objectweb.asm.Type;

public class MethodInstruction implements Instruction {

	String name;
	String owner;
	ArrayList<String> params = new ArrayList<String>();
	String returns = "";

	public MethodInstruction(String owner, String name, String desc) {
		this.owner = owner;
		this.name = name;
		List<String> parameters = WorkerForArrows.getTypesFromDesc(desc);
		for (String type : parameters) {
			this.params.add(WorkerForArrows.stripFunction(type));
			this.returns = WorkerForArrows.stripFunction(Type.getReturnType(desc).toString());
		}
	}

	public void printInstruction() {
		System.out.println(this.toString());
	}

	public void execute() {
		String cleanOwner = WorkerForArrows.stripFunction(owner);
		if (WorkerForArrows.unwantedTypes(cleanOwner)) {
			SDArrows.getInstance().checkClasses(cleanOwner);
			System.out.println(toString());
			SDArrows.getInstance().execute(cleanOwner + "." + name);
		}
	}

	@Override
	public String toString() {
		return  ":" + returns + "=" + owner + "." + name + "(" + getParamString() + ")";
	}

	private String getParamString() {
		String toReturn = "";
		if (this.params.size() != 0) {
			toReturn = params.get(0);
		}
		for (int i = 1; i < params.size(); i++) {
			toReturn += ", " + params.get(i);
		}
		return toReturn;
	}
}
