package SDClasses;

public interface Instruction {
	public void printInstruction();

	public void execute(String caller, int depth);
}