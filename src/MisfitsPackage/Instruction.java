package MisfitsPackage;

public interface Instruction {
	public void printInstruction();

	public void execute(String caller, int depth);
}