package UMLClasses;

import java.util.ArrayList;

import ClassStorage.ArrowStorage;
import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;

public class UMLPrinter {
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
	 */
	public static void printClasses(String nameOfDiagram, ArrayList<ClassContainer> classes) {
		String pattern;
		String color;
		String fillColor;
		String preLabel;
		// Will use hashmap here in future
		/*
		 * if(isSingle){ pattern = "\\n\\<\\<Singleton\\>\\>"; color =
		 * "color=\"purple\""; }
		 */
		startDiagram(nameOfDiagram);
		for (ClassContainer tempClass : classes) {
			pattern = "";
			color = "";
			fillColor = "";
			preLabel = "";
			if (tempClass.getIsInterface()) {
				preLabel = "\\<\\<Interface\\>\\> \\n";
			}
				pattern = "\\n \\<\\<" + tempClass.getLabel() + "\\>\\>";
				color = "color=\"" + tempClass.getColor() + "\"";
				fillColor = ", fillcolor=\"" + tempClass.getFillColor() + "\" style=\"filled\"";
			System.out.print("   " + tempClass.getClassName() + " [\n     shape=\"record\"  " + color + fillColor
					+ "    label = \"{" + preLabel + tempClass.getClassName() + pattern + tempClass.getLabel() + "|"
					+ tempClass.getfieldBuffer().toString() + "|" + tempClass.getMethodBuffer().toString()
					+ "\n}\"\n];\n");
			printArrows(tempClass);
		}
		endDiagram();
	}

	/**
	 * Makes the initial diagram starting code with given name (Should only be
	 * ran once per diagram).
	 * 
	 * @param nameOfDiagram
	 *            The name in which the diagram is to be titled.
	 */
	private static void startDiagram(String nameOfDiagram) {
		System.out.print("digraph " + nameOfDiagram + "{\nrankdir=BT\n");
	}

	/**
	 * Makes the end diagram code (Should only be ran once per diagram).
	 */
	private static void endDiagram() {
		System.out.print("}\n");
	}

	/**
	 * Prints all the arrows to the console.
	 * 
	 * @param classString
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return value
	 */
	private static void printArrows(ClassContainer tempClass) {
		printUses(tempClass);
		printFields(tempClass);
		printInterfaces(tempClass);
		printSupers(tempClass);
	}

	/**
	 * Prints all 'Use' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private static void printUses(ClassContainer tempClass) {
		for (ArrowStorage types : tempClass.getUses()) {
			if (types.getTargetType().contains("_") && !types.getTargetType().equals(tempClass.getClassName())) {
				System.out.println(tempClass.getClassName() + " -> " + types.getTargetType() + " [ " + types.getLabel()
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
	private static void printFields(ClassContainer tempClass) {
		for (FieldStorage field : tempClass.getFields()) {
			if (field.getType().contains("_") && !field.getType().equals(tempClass.getClassName())) {
				System.out.println(tempClass.getClassName() + " -> " + field.getType() + " [" + field.getLabel()
						+ "arrowhead=\"vee\"];");
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
	private static void printInterfaces(ClassContainer tempClass) {
		for (ArrowStorage interf : tempClass.getInterfaces()) {
			if (interf.getTargetType().contains("_") && !interf.getTargetType().equals(tempClass.getClassName())) {
				System.out.println(tempClass.getClassName() + " -> " + interf.getTargetType() + " [ "
						+ interf.getLabel() + "arrowhead=\"onormal\", style=\"dashed\"];");
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
	private static void printSupers(ClassContainer tempClass) {
		ArrowStorage extension = tempClass.getSupers();
		if (extension == null) {
			return;
		}
		if (extension.getTargetType().contains("_") && !extension.getTargetType().equals(tempClass.getClassName())) {
			System.out.println(tempClass.getClassName() + " -> " + tempClass.getSupers().getTargetType() + " ["
					+ extension.getLabel() + "arrowhead=\"onormal\"];");
		}
	}
}
