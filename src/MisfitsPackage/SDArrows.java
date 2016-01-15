package MisfitsPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class SDArrows {
	// TODO: DEAL WITH FOR LOOPS
	private static SDArrows ourArrows = new SDArrows();
	// need methods
	private HashMap<String, ArrayList<Instruction>> instructionSets = new HashMap<String, ArrayList<Instruction>>();

	private String currentClass;
	private ArrayList<String> classes = new ArrayList<String>();

	public SDArrows() {
	}

	/**
	 * Gets the current instance of SDArrow
	 * 
	 * @return SDArrow
	 */
	public static SDArrows getInstance() {
		return ourArrows;
	}

	public void printClass(String className) {
		String cleanName = WorkerForArrows.stripFunction(className);
		System.out.print(":" + cleanName);
	}

	public void setCurrentClass(String className) {
		currentClass = className;
		System.out.println(className+":"+className+"[a]");
	}

	public void addItemsToHashMap(String methodName, ArrayList<Instruction> instructions) {
		instructionSets.put(currentClass + "." + methodName, instructions);
	}

	public HashMap<String, ArrayList<Instruction>> getHashMap() {
		return instructionSets;
	}

	public String getCurrentClass() {
		return currentClass;
	}

	// Only to be run when the currentClass is the Main
	public void executeFromMain(String mainClass) {
		String cleanClass = WorkerForArrows.stripFunction(mainClass);
		ArrayList<Instruction> mainInstructions = instructionSets.get(cleanClass + ".main");
		if (mainInstructions.size() > 0) {
			for (Instruction instr : mainInstructions) {
				instr.execute(cleanClass);
			}
		}
	}

	public void execute(String methodString, String className) {
		ArrayList<Instruction> methodInstructions = instructionSets.get(methodString);
		if (methodInstructions != null) {
			for (Instruction instr : methodInstructions) {
				instr.execute(className);
			}
		}
	}

	public void checkClasses(String cleanOwner) {
		if(!this.classes.contains(cleanOwner)){
			addClass(cleanOwner);
		}
	}

	private void addClass(String cleanOwner) {
		this.classes.add(cleanOwner);
	}

}
