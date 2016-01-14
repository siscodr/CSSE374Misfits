package MisfitsPackage;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

/**
 * MyMethodVisitor allows the functionality to pull types from inside of a
 * method for the purpose of a UML
 * 
 * @author TheMisfits
 */
public class MyMethodVisitor extends MethodVisitor {

	/**
	 * Constructs a new MyMethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public MyMethodVisitor(int api) {
		super(api);
	}

	/**
	 * Constructs a new MyMethodVisitor that decorates the old MethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A MethodVisitor for this class to Decorate
	 */
	public MyMethodVisitor(int api, MethodVisitor toDecorate) {
		super(api, toDecorate);
	}

	/**
	 * Decorates the visitTypeInsn method to get an use arrow for when a class
	 * is constructed
	 * 
	 * @param opcode
	 *            the opcode of the type instruction to be visited
	 * @param val
	 *            the operand of the instruction to be visited. This operand
	 *            must be the internal name of an object or array class
	 */
	@Override
	public void visitTypeInsn(int opCode, String val) {
		super.visitTypeInsn(opCode, val);
		// Creates an use arrow
		UMLArrows.getInstance().addUse(val);
	}

	/**
	 * Decorates the visitTypeInsn method to get an use arrow for when a class
	 * is constructed
	 * 
	 * @param opcode
	 *            the opcode of the type instruction to be visited.
	 * @param owner
	 *            the internal name of the method's owner class.
	 * @param name
	 *            the method's name.
	 * @param desc
	 *            the method's descriptor.
	 * @param bool
	 *            if the method's owner class is an interface.
	 */
	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, boolean bool) {
		super.visitMethodInsn(opcode, owner, name, desc, bool);

		String toClean = Type.getReturnType(desc).getClassName();

		UMLArrows arrows = UMLArrows.getInstance();
		// Adds first use arrow here
		arrows.addUse(toClean);
		// Adds second use arrow here
		arrows.addUse(owner);
	}
	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index){
		super.visitLocalVariable(name, desc, signature, start, end, index);
		//TODO: figure out the sd arrows method that we will pass this into
		//SDArrows.getInstance().setVarNames(null);
	}
}
