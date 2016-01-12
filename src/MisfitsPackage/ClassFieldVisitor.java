package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Type;

/**
 * ClassFieldVisitor Decorates ClassVisitor so the field is checked for use arrows if needed
 * 
 * @author TheMisfits
 */
public class ClassFieldVisitor extends ClassVisitor {

	/**
	 * Constructs a new ClassFieldVisitor that decorates the old ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public ClassFieldVisitor(int arg0) {
		super(arg0);
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
	public ClassFieldVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	/**
	 * This method decorates the ClassVisitor's visitField function to add
	 * functionality to pass field types to UMLArrow in order to create a UML
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
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc,
				signature, value);
		String type = Type.getType(desc).getClassName();
		UMLArrows.getInstance().addField(type); //Adds field to UML to allow for use arrows to be drawn
		return toDecorate;
	}

}