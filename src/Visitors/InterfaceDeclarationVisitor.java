package Visitors;

import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.ClassVisitor;

/**
 * InterfaceDeclarationVisitor Decorates ClassVisitor so the interface Arrows
 * can be drawn
 * 
 * @author TheMisfits
 */
public class InterfaceDeclarationVisitor extends ClassVisitor {

	/**
	 * Constructs a new InterfaceDeclarationVisitor
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public InterfaceDeclarationVisitor(int api) {
		super(api);
	}

	/**
	 * Constructs a new InterfaceDeclarationVisitor that decorates the old
	 * ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A ClassVisitor for this class to Decorate
	 */
	public InterfaceDeclarationVisitor(int api, ClassVisitor toDecorate) {
		super(api, toDecorate);
	}

	/**
	 * This method decorates the ClassVisitor's visit function to add
	 * functionality to pass interfaces to UMLArrow in order to create a UML
	 *
	 * @param version
	 *            the class version
	 * @param access
	 *            the class's access flags (see Opcodes). This parameter also
	 *            indicates if the class is deprecated
	 * @param name
	 *            the internal name of the class
	 * @param signature
	 *            the signature of this class. May be null if the class is not a
	 *            generic one, and does not extend or implement generic classes
	 *            or interfaces.
	 * @param superName
	 *            The name of a class that this class extends from (Only should
	 *            be null in case of Object)
	 * @param interfaces
	 *            An array of classes that the class inherits methods from
	 */
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

		UMLArrows arrows = UMLArrows.getInstance(); // Get the Singleton
													// Instance of UMLArrows

		for (int i = 0; i < interfaces.length; i++) {
			arrows.addInterface(interfaces[i]); // Store interface arrows into
												// UMLArrows
		}

		// Does the decoratee's visit function
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
