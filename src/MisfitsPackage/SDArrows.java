package MisfitsPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class SDArrows {
	//TODO: DEAL WITH FOR LOOPS
	private static SDArrows ourArrows = new SDArrows();
	//need methods
	private HashMap<String,HashMap<String, String>> varNames = new HashMap<String,HashMap<String,String>>();
	
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

	public HashMap<String,HashMap<String, String>> getVarNames() {
		return varNames;
	}

	public void setVarNames(HashMap<String,String> variableNames) {
		//TODO: figure out which method is the key & add the hashmap
		//TODO: figure out where we are calling the MyMethodVisitor
	}

	public void addMethod(String name) {
		this.varNames.put(name, null);
	}


	
}
