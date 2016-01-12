package MisfitsPackage;

import java.io.IOException;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * This purpose of this class to be used in conjunction with Decorators to
 * create a UML diagram
 * 
 * @author TheMisfits
 */
public class DesignParser {

	/**
	 * Main just calls makeUML (Split for better design and testing)
	 * 
	 * @param args
	 *            Class names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void main(String[] args) throws IOException {
		makeUML(args); // Runs the program
	}

	/**
	 * Makes a UML diagram code appear in the console for GraphViz for the given
	 * classes using the Decorator and Visitor design patterns.
	 * 
	 * @param classes
	 *            lass names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void makeUML(String[] classes) throws IOException {
		startDiagram("misfit_diagram");
		for (String className : classes) {

			ClassReader reader = new ClassReader(className);

			ClassVisitor InterfaceVisitor = new InterfaceDeclarationVisitor(Opcodes.ASM5);
			
			ClassVisitor SuperVisitor = new SuperDeclarationVisitor(Opcodes.ASM5, InterfaceVisitor);

			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5,
					SuperVisitor);

			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5,
					fieldVisitor);

			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);

			UMLArrows.getInstance().printClass(className);
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
	public static void startDiagram(String nameOfDiagram) {
		System.out.println("digraph " + nameOfDiagram + "{\nrankdir=BT");
	}

	/**
	 * Makes the end diagram code (Should only be ran once per diagram).
	 */
	public static void endDiagram() {
		System.out.println("}");
	}
}
