package Visitors;

import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.signature.SignatureReader;
import jdk.internal.org.objectweb.asm.signature.SignatureVisitor;

/**
 * ClassFieldVisitor Decorates ClassVisitor so the field is checked for
 * association arrows if needed
 * 
 * @author TheMisfits
 */
public class ClassFieldVisitor extends ClassVisitor {

	/**
	 * Constructs a new ClassFieldVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public ClassFieldVisitor(int api) {
		super(api);
	}

	/**
	 * Constructs a new ClassFieldVisitor that decorates the old ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A ClassVisitor for this class to Decorate
	 */
	public ClassFieldVisitor(int api, ClassVisitor toDecorate) {
		super(api, toDecorate);
	}

	/**
	 * This method decorates the ClassVisitor's visitField function to add
	 * functionality to pass field descriptors to UMLArrow in order to create a
	 * UML
	 * 
	 * @param access
	 *            the field's access flags
	 * @param name
	 *            the Field's name
	 * @param desc
	 *            the field's descriptor
	 * @param signature
	 *            the field's signature. May be null if the field's type does
	 *            not use generic types.
	 * @param value
	 *            the field's initial value. This parameter, which may be null
	 *            if the field does not have an initial value
	 */
	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		if (signature != null) {
			SignatureReader reader = new SignatureReader(signature);
			SignatureVisitor visitor = new mySignatureVisitor(Opcodes.ASM5, access);
			// Adds field to UML to allow for association arrows to be drawn
			reader.accept(visitor);
		}
		UMLArrows.getInstance().addFieldDesc(desc, access, name);
		return toDecorate;
	}

}