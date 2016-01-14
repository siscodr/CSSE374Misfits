package MisfitsPackage;

import java.util.ArrayList;

public class SDArrows {
	//TODO: DEAL WITH FOR LOOPS
	private static SDArrows ourArrows = new SDArrows();
	//need methods

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
	
	
}
