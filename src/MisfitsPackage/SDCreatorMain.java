/**
 * 
 */
package MisfitsPackage;

import java.io.IOException;

/**
 * @author soulsbjw
 *
 */
public class SDCreatorMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		DesignParser.makeSD(args); // Runs the program
		WorkerForArrows.stripFunction("hi");
		}

}
