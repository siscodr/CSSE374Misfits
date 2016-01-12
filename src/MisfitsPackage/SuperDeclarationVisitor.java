package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;

public class SuperDeclarationVisitor extends ClassVisitor {

	/**
	 * Constructs a new ClassDeclarationVisitor
	 * 
	 * @param arg0
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public SuperDeclarationVisitor(int api) {
		super(api);
	}
	
	public SuperDeclarationVisitor(int api, ClassVisitor toDecorate) {
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
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {

		UMLArrows arrows = UMLArrows.getInstance(); // Get the Singleton
													// Instance of UMLArrows

		if (superName != null) {
			arrows.addSuper(superName); // Store super arrows (class extensions)
										// into UMLArrows
		}

		// Does the decoratee's visit function
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
