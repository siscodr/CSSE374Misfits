package MisfitsPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class SDArrows {
	//TODO: DEAL WITH FOR LOOPS
	private static SDArrows ourArrows = new SDArrows();
	//need methods
	private HashMap<String,ArrayList<Instruction>> instructionSets = new HashMap<String, ArrayList<Instruction>>();
	
	private String currentClass;
	
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
	
	public void printClass(String className){
		String cleanName = WorkerForArrows.stripFunction(className);
		System.out.print(":" + cleanName);		
	}

	public void setCurrentClass(String className) {
		currentClass=className;
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

	
	
}
