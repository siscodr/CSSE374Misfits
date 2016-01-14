package MisfitsPackage;

public class TypeInstruction implements Instruction {

	int opCode;
	String var;

	public TypeInstruction(int opCode, String var) {
		super();
		this.opCode = opCode;
		this.var = var;
	}

	@Override
	public void printInstruction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// Do nothing?
	}

	@Override
	public String toString() {
		return null;
	}

}
