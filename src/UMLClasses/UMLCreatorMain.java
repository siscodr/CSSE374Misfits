package UMLClasses;

import java.io.IOException;

import MisfitsPackage.ClassFinder;
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
		UMLArrows.getInstance().addDetector(
				new SingletonDetector("orange", "purple"));
		UMLArrows.getInstance().addDetector(
				new DecoratorDetector("green", "blue"));
		UMLArrows.getInstance().addDetector(
				new CompositeDetector("deepskyblue1", "coral"));
		UMLArrows.getInstance().addDetector(
				new AdapterDetector("deeppink", "grey40"));
		if (args.length == 1) {
			DesignParser.makeUML(ClassFinder.getClasses(args[0])); // Runs the
																	// program
		}
		else if(args.length == 0) {
			System.out.println("Not enough Arguements");
		}
		else {
			DesignParser.makeUML(args);
		}
	}
}
