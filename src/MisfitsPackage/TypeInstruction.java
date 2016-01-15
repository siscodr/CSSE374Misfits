package MisfitsPackage;

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


	public void execute() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		String currentClass = SDArrows.getInstance().getCurrentClass();
		return currentClass + ":" + type + ".create()";
	}

}
