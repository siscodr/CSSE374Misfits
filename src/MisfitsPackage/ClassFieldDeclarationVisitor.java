package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Type;

/**
 * ClassFieldDeclarationVisitor Decorates ClassVisitor so the field will be
 * shown in the class in the UML
 * 
 * @author TheMisfits
 */
public class ClassFieldDeclarationVisitor extends ClassVisitor {

	/**
	 * Constructs a new ClassFieldDeclarationVisitor that decorates the old
	 * ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public ClassFieldDeclarationVisitor(int arg0) {
		super(arg0);
	}

	/**
	 * Constructs a new ClassFieldDeclarationVisitor that decorates the old
	 * ClassVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A ClassVisitor for this class to Decorate
	 */
	public ClassFieldDeclarationVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	/**
	 * This method decorates the ClassVisitor's visitField function to add
	 * functionality to pass fields to UMLArrow in order to create a UML
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
		// Adds a field in the UML for given class
		UMLArrows.getInstance().addFieldToBuffer(access, name, type);
		return toDecorate;
	}

}