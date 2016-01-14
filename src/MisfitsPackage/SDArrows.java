package MisfitsPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class SDArrows {
	//TODO: DEAL WITH FOR LOOPS
	private static SDArrows ourArrows = new SDArrows();
	//need methods
	private HashMap<String,ArrayList<Instruction>> varNames = new HashMap<String, ArrayList<Instruction>>();
	
	private String currentClass;
	
	public SDArrows() {
		resetSDArrows();
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
	 * Resets the ArrayLists used to make arrows.
	 * 
	 * @return No return value.
	 */
	public void resetSDArrows() {
		//TODO: Reset
	}
	
	public void printClass(String className){
		String cleanName = WorkerForArrows.stripFunction(className);
		System.out.print("< > :" + "<" + cleanName + ">");
		
	}


	public void setVarNames(HashMap<String,String> variableNames) {
		//TODO: figure out which method is the key & add the hashmap
		//TODO: figure out where we are calling the MyMethodVisitor
	}

	public void addMethod(String name) {
		this.varNames.put(currentClass+"."+name, null);
	}

	public void setCurrentClass(String className) {
		currentClass=className;
	}


	
}
