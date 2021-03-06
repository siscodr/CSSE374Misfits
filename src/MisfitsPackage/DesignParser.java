package MisfitsPackage;

import java.io.IOException;

import SDClasses.SDArrows;
import UMLClasses.UMLArrows;
import Visitors.ClassFieldDeclarationVisitor;
import Visitors.ClassFieldVisitor;
import Visitors.ClassMethodVisitor;
import Visitors.InterfaceDeclarationVisitor;
import Visitors.MethodDeclarationVisitor;
import Visitors.SuperDeclarationVisitor;
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
	 * Makes a UML diagram code appear in the console for GraphViz for the given
	 * classes using the Decorator and Visitor design patterns.
	 * 
	 * @param classes
	 *            class names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void makeUML(String[] classes) throws IOException {
		// Creates whiteList for the classes to draw on UML
		WorkerForArrows.addWhitelist(classes);
		
		for (String className : classes) {
			
			UMLArrows.getInstance().resetUMLArrows(className);
			makeReader(className);
		}
		UMLArrows.getInstance().resetUMLArrows("");
		//DONE MAKING CLASSES
		UMLArrows.getInstance().detect();
		UMLArrows.getInstance().printClasses("misfit_diagram");
	}

	/**
	 * Makes a SD diagram code appear in the console for SDEdit for the given
	 * classes using the Decorator and Visitor design patterns.
	 * 
	 * @param classes
	 *            class names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void makeSD(String[] classes) throws IOException {
		WorkerForArrows.addWhitelist(classes);
		for (String className : classes) {
			SDArrows.getInstance().setCurrentClass(WorkerForArrows.stripFunction(className));
			makeReader(className);
		}
		System.out.println();
		SDArrows.getInstance().executeFromMain(classes[0]);
	}

	/**
	 * Makes the Decorated ClassVisitor with all the Visitor Decorators
	 * 
	 * @param classes
	 *            class names for the classes to be turned into an UML
	 * @throws IOException
	 *             Exception where string doesn't link to a class
	 */
	public static void makeReader(String className) throws IOException {
		ClassReader reader = new ClassReader(className);

		ClassVisitor InterfaceVisitor = new InterfaceDeclarationVisitor(Opcodes.ASM5); // Interface
																						// Arrows

		ClassVisitor SuperVisitor = new SuperDeclarationVisitor(Opcodes.ASM5, InterfaceVisitor); // Extends
																									// Arrows

		ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, SuperVisitor); // Fields
																						// for
																						// Classes

		ClassVisitor fieldUsesVisitor = new ClassFieldDeclarationVisitor(Opcodes.ASM5, fieldVisitor); // Field's
																										// Association
																										// arrows

		ClassVisitor methodUsesVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldUsesVisitor); // Method's
																									// Uses
																									// arrows

		ClassVisitor methodVisitor = new MethodDeclarationVisitor(Opcodes.ASM5, methodUsesVisitor); // Method
																									// for
																									// Classes

		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
	}


}
