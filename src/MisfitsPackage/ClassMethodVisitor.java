package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

/**
 * ClassMethodVisitor Decorates ClassVisitor so the Use Arrows can be drawn
 * 
 * @author TheMisfits
 */
public class ClassMethodVisitor extends ClassVisitor {

	/**
	 * Constructs a new ClassMethodVisitor
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public ClassMethodVisitor(int api) {
		super(api);
	}

	/**
	 * Constructs a new ClassMethodVisitor that decorates the old ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A ClassVisitor for this class to Decorate
	 */
	public ClassMethodVisitor(int api, ClassVisitor toDecorate) {
		super(api, toDecorate);
	}

	/**
	 * This method decorates the ClassVisitor's visitMethod function to add
	 * functionality to pass types to create use arrows in the UML
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

		MethodVisitor methodVisitor = super.visitMethod(access, name, desc,
				signature, exceptions);
		// Add use arrows
		UMLArrows.getInstance().addUses(desc);
		
		SDArrows.getInstance().addMethod(name);
		System.out.println(name);
		return methodVisitor;
	}
}
