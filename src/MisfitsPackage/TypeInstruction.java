package MisfitsPackage;

public class TypeInstruction implements Instruction {

	int opCode;
	String type;

	public TypeInstruction(int opCode, String type) {
		super();
		this.opCode = opCode;
		this.type = type;
	}

	@Override
	public void printInstruction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// Do nothing!
	}

	@Override
	public String toString() {
		String currentClass = SDArrows.getInstance().getCurrentClass();
		return currentClass + ":" + type + ".create()";
	}

}
