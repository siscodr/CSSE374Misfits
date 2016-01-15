package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * MethodDeclarationVisitor Decorates ClassVisitor so the methods are shown
 * inside of classes in the UML
 * 
 * @author TheMisfits
 */
public class MethodDeclarationVisitor extends ClassVisitor {

	/**
	 * Constructs a new MethodDeclarationVisitor
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public MethodDeclarationVisitor(int api) {
		super(api);
	}

	/**
	 * Constructs a new MethodDeclarationVisitor that decorates the old
	 * ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A ClassVisitor for this class to Decorate
	 */
	public MethodDeclarationVisitor(int api, ClassVisitor toDecorate) {
		super(api, toDecorate);
	}

	/**
	 * This method decorates the ClassVisitor's visitMethod function to add
	 * functionality to put methods in the UML in their respective classes
	 * 
	 * TODO: Currently it also decorates MethodVisitor with a new MethodVisitor
	 * 
	 * @param access
	 *            the method's access flags.
	 * @param name
	 *            the method's name.
	 * @param desc
	 *            the method's descriptor.
	 * @param signature
	 *            the method's signature. May be null if the method parameters,
	 *            return type and exceptions do not use generic types.
	 * @param exceptions
	 *            the internal names of the method's exception classes.
	 */
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		MethodVisitor toDecorate = super.visitMethod(access, name, desc,
				signature, exceptions);

		// Decorates the current MethodVisitor
		MethodVisitor toReturn = new MyMethodVisitor(Opcodes.ASM5, toDecorate, name);
	
		// Sends required variables to UMLArrows to add the method to the class
		System.out.println("name is: " + name );
		UMLArrows.getInstance().addMethodToBuffer(access, name, desc);

		return toReturn;
	}
}
