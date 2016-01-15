package MisfitsPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class SDArrows {
	// This is how deep we want to search into the method calls
	private static final int DEPTHLIMIT = 6;
	private static SDArrows ourArrows = new SDArrows();
	// Stores methods
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

	/**
	 * Takes the current method that is being processed and stores it to the
	 * currentClass variable.
	 * 
	 * @param className
	 *            A String representation of the current class.
	 * @return No return value.
	 */
	public void setCurrentClass(String className) {
		currentClass = className;
		System.out.println(className + ":" + className + "[a]");
	}

	/**
	 * Strips the given class and appends a semicolon to the front.
	 * 
	 * @param className
	 *            A String representation of the current class.
	 * @return No return value.
	 */
	public void printClass(String className) {
		String cleanName = WorkerForArrows.stripFunction(className);
		System.out.print(":" + cleanName);
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
				instr.execute(cleanClass, 0);
			}
		}
	}

	public void execute(String methodString, String className, int depth) {
		ArrayList<Instruction> methodInstructions = instructionSets.get(methodString);
		if (methodInstructions != null) {
			for (Instruction instr : methodInstructions) {
				if (depth < DEPTHLIMIT)
					instr.execute(className, depth + 1);
			}
		}
	}

	public void checkClasses(String cleanOwner) {
		if (!this.classes.contains(cleanOwner)) {
			addClass(cleanOwner);
		}
	}

	private void addClass(String cleanOwner) {
		this.classes.add(cleanOwner);
	}

}
