package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;

/**
 * SuperDeclarationVisitor decorates ClassVisitor so the extends Arrows can be
 * drawn
 * 
 * @author TheMisfits
 */
public class SuperDeclarationVisitor extends ClassVisitor {

	/**
	 * Constructs a new SuperDeclarationVisitor
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public SuperDeclarationVisitor(int api) {
		super(api);
	}

	/**
	 * Constructs a new SuperDeclarationVisitor that decorates the old
	 * ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A ClassVisitor for this class to Decorate
	 */
	public SuperDeclarationVisitor(int api, ClassVisitor toDecorate) {
		super(api, toDecorate);
	}

	/**
	 * This method decorates the ClassVisitor's visit function to add
	 * functionality to pass Super classes (Class Extensions) to UMLArrow in
	 * order to create a UML
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

		// Store super arrows (class extensions) into UMLArrows
		UMLArrows.getInstance().setSuper(superName);

		// Does the decoratee's visit function
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
