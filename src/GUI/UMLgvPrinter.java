package GUI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ClassStorage.ArrowStorage;
import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;
import UMLClasses.UMLArrows;

public class UMLgvPrinter {
	/**
	 * Print all the Buffers and Arrows to the Console. Then resets fields to
	 * setup for the next class.
	 * 
	 * TODO:Add functionality to allow to export this as a file (Out of project
	 * scope).
	 * 
	 * @param name
	 *            A String representation of name of the current class
	 * @return No return value
	 * @throws FileNotFoundException 
	 */
	public static void printClasses(String nameOfDiagram, ArrayList<String> whitelist) throws FileNotFoundException {
		PrintWriter printFile = new PrintWriter("drawableGraph.gv");
		ArrayList<ClassContainer> classes = UMLArrows.getInstance().getClasses();
		String pattern;
		String color;
		String fillColor;
		String preLabel;
		// Will use hashmap here in future
		/*
		 * if(isSingle){ pattern = "\\n\\<\\<Singleton\\>\\>"; color =
		 * "color=\"purple\""; }
		 */
		startDiagram(nameOfDiagram, printFile);
		for (ClassContainer tempClass : classes) {
			if (whitelist.contains(tempClass.getClassName())) {
				pattern = "";
				color = "";
				fillColor = "";
				preLabel = "";
				if (tempClass.getIsInterface()) {
					preLabel = "\\<\\<Interface\\>\\> \\n";
				}
				pattern = tempClass.getLabel();
				color = tempClass.getColor();
				fillColor = tempClass.getFillColor();
				printFile.print("   " + tempClass.getClassName()
						+ " [\n     shape=\"record\"  " + color + fillColor
						+ "    label = \"{" + preLabel
						+ tempClass.getAnnotation() + tempClass.getClassName()
						+ pattern + "|" + tempClass.getfieldBuffer().toString()
						+ "|" + tempClass.getMethodBuffer().toString()
						+ "\n}\"\n];\n");
				printArrows(tempClass, whitelist, printFile);
			}
		}
		endDiagram(printFile);
		printFile.close();
	}

	/**
	 * Makes the initial diagram starting code with given name (Should only be
	 * ran once per diagram).
	 * 
	 * @param nameOfDiagram
	 *            The name in which the diagram is to be titled.
	 */
	private static void startDiagram(String nameOfDiagram, PrintWriter printFile) {
		printFile.print("digraph " + nameOfDiagram + "{\nrankdir=BT\n");
	}

	/**
	 * Makes the end diagram code (Should only be ran once per diagram).
	 */
	private static void endDiagram(PrintWriter printFile) {
		printFile.print("}\n");
	}

	/**
	 * Prints all the arrows to the console.
	 * 
	 * @param classString
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return value
	 */
	private static void printArrows(ClassContainer tempClass, ArrayList<String> whitelist, PrintWriter printFile) {
		printUses(tempClass, whitelist, printFile);
		printFields(tempClass, whitelist, printFile);
		printInterfaces(tempClass, whitelist, printFile);
		printSupers(tempClass, whitelist, printFile);
	}

	/**
	 * Prints all 'Use' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private static void printUses(ClassContainer tempClass, ArrayList<String> whitelist, PrintWriter printFile) {
		for (ArrowStorage types : tempClass.getUses()) {
			if (whitelist.contains(types.getTargetType()) && types.getTargetType().contains("_")
					&& !types.getTargetType().equals(tempClass.getClassName())) {
				printFile.println(tempClass.getClassName() + " -> "
						+ types.getTargetType() + " [ " + types.getColor()
						+ " " + types.getLabel()
						+ "arrowhead=\"vee\", style=\"dashed\"];");
			}
		}
	}

	/**
	 * Prints all 'Association' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private static void printFields(ClassContainer tempClass, ArrayList<String> whitelist, PrintWriter printFile) {
		for (FieldStorage field : tempClass.getFields()) {
			if (whitelist.contains(field.getType()) && field.getType().contains("_")
					&& !field.getType().equals(tempClass.getClassName())) {
				printFile.println(tempClass.getClassName() + " -> "
						+ field.getType() + " [" + field.getColor() + " "
						+ field.getLabel() + "arrowhead=\"vee\"];");
			}
		}
	}

	/**
	 * Prints all 'Interface' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private static void printInterfaces(ClassContainer tempClass, ArrayList<String> whitelist, PrintWriter printFile) {
		for (ArrowStorage interf : tempClass.getInterfaces()) {
			if (whitelist.contains(interf.getTargetType()) && interf.getTargetType().contains("_")
					&& !interf.getTargetType().equals(tempClass.getClassName())) {
				printFile.println(tempClass.getClassName() + " -> "
						+ interf.getTargetType() + " [ " + interf.getColor()
						+ " " + interf.getLabel()
						+ "arrowhead=\"onormal\", style=\"dashed\"];");
			}
		}
	}

	/**
	 * Prints all 'Extension' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private static void printSupers(ClassContainer tempClass, ArrayList<String> whitelist, PrintWriter printFile) {
		ArrowStorage extension = tempClass.getSupers();
		if (extension == null) {
			return;
		}
		if (whitelist.contains(extension.getTargetType()) && extension.getTargetType().contains("_")
				&& !extension.getTargetType().equals(tempClass.getClassName())) {
			printFile.println(tempClass.getClassName() + " -> "
					+ tempClass.getSupers().getTargetType() + " ["
					+ extension.getColor() + " " + extension.getLabel()
					+ "arrowhead=\"onormal\"];");
		}
	}
}
