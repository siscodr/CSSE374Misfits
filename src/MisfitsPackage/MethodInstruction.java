package MisfitsPackage;

public class MethodInstruction implements Instruction {

	String name;
	String caller;
	String callee;

	@Override
	public void printInstruction() {
		System.out.println(this.toString());
	}

	@Override
	public void execute() {
		// TODO: Get to call this method here.
	}

	@Override
	public String toString() {
		return null;
	}

}
