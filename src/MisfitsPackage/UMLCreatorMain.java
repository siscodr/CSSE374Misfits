package MisfitsPackage;

import java.io.IOException;

public class UMLCreatorMain {
	/**
	 * Main just calls makeUML from DesignParser (Split for better design and testing)
	 * 
	 * @param args
	 *            Class names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void main(String[] args) throws IOException {
		DesignParser.makeUML(args); // Runs the program
	}
}