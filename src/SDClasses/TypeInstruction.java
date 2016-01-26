package SDClasses;

import MisfitsPackage.WorkerForArrows;

public class TypeInstruction implements Instruction {

	int opCode;
	String type;

	public TypeInstruction(int opCode, String type) {
		super();
		this.opCode = opCode;
		this.type = type;
	}

	public void printInstruction() {
		// TODO Auto-generated method stub

	}

	public void execute(String caller, int depth) {
		if (WorkerForArrows.unwantedTypes(type)) {
			System.out.println(caller + toString());
		}
	}

	@Override
	public String toString() {
		return ":" + type + ".create()";
	}

}
