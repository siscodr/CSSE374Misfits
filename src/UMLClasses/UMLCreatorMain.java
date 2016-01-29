package UMLClasses;

import java.io.IOException;

import MisfitsPackage.DesignParser;

public class UMLCreatorMain {
	/**
	 * Main just calls makeUML from DesignParser (Split for better design and
	 * testing)
	 * 
	 * @param args
	 *            Class names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void main(String[] args) throws IOException {
		UMLArrows.getInstance().addDetector(new SingletonDetector("orange", "purple"));
		UMLArrows.getInstance().addDetector(new DecoratorDetector("green", "blue"));
		UMLArrows.getInstance().addDetector(new AdapterDetector("deeppink", "grey40"));
		DesignParser.makeUML(args); // Runs the program
	}
}
