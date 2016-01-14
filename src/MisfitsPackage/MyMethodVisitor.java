package MisfitsPackage;

import java.util.ArrayList;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Attribute;
import jdk.internal.org.objectweb.asm.Handle;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.TypePath;

/**
 * MyMethodVisitor allows the functionality to pull types from inside of a
 * method for the purpose of a UML
 * 
 * @author TheMisfits
 */
public class MyMethodVisitor extends MethodVisitor {

	private ArrayList<Instruction> instructions;
	/**
	 * Constructs a new MyMethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public MyMethodVisitor(int api, ArrayList<Instruction> instructions) {
		super(api);
		this.instructions = instructions;
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
	public MyMethodVisitor(int api, MethodVisitor toDecorate, ArrayList<Instruction> instructions) {
		super(api, toDecorate);
		this.instructions = instructions;
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
	
	public AnnotationVisitor VisitAnnotation(String desc, boolean visible) {
		return super.visitAnnotation(desc, visible);
	}
	
	public void visitCode() {
		super.visitCode();
	}
	
	public void visitEnd() {
		super.visitEnd();
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

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// TODO Auto-generated method stub
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		// TODO Auto-generated method stub
		return super.visitAnnotationDefault();
	}

	@Override
	public void visitAttribute(Attribute attr) {
		// TODO Auto-generated method stub
		super.visitAttribute(attr);
	}

	@Override
	public void visitFieldInsn(int opCode, String owner, String name, String desc) {
		// TODO Auto-generated method stub
		super.visitFieldInsn(opCode, owner, name, desc);
	}

	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
		// TODO Auto-generated method stub
		super.visitFrame(type, nLocal, local, nStack, stack);
	}

	@Override
	public void visitIincInsn(int var, int increment) {
		// TODO Auto-generated method stub
		super.visitIincInsn(var, increment);
	}

	@Override
	public void visitInsn(int opCode) {
		// TODO Auto-generated method stub
		super.visitInsn(opCode);
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		// TODO Auto-generated method stub
		return super.visitInsnAnnotation(typeRef, typePath, desc, visible);
	}

	@Override
	public void visitIntInsn(int opCode, int operand) {
		// TODO Auto-generated method stub
		super.visitIntInsn(opCode, operand);
	}

	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
		// TODO Auto-generated method stub
		super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
	}

	@Override
	public void visitJumpInsn(int opCode, Label label) {
		// TODO Auto-generated method stub
		super.visitJumpInsn(opCode, label);
	}

	@Override
	public void visitLabel(Label label) {
		// TODO Auto-generated method stub
		super.visitLabel(label);
	}

	@Override
	public void visitLdcInsn(Object cst) {
		// TODO Auto-generated method stub
		super.visitLdcInsn(cst);
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		// TODO Auto-generated method stub
		super.visitLineNumber(line, start);
	}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end,
			int[] index, String desc, boolean visible) {
		// TODO Auto-generated method stub
		return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, desc, visible);
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		// TODO Auto-generated method stub
		super.visitLookupSwitchInsn(dflt, keys, labels);
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		// TODO Auto-generated method stub
		super.visitMaxs(maxStack, maxLocals);
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		// TODO Auto-generated method stub
		super.visitMultiANewArrayInsn(desc, dims);
	}

	@Override
	public void visitParameter(String name, int access) {
		// TODO Auto-generated method stub
		super.visitParameter(name, access);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
		// TODO Auto-generated method stub
		return super.visitParameterAnnotation(parameter, desc, visible);
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
		// TODO Auto-generated method stub
		super.visitTableSwitchInsn(min, max, dflt, labels);
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		// TODO Auto-generated method stub
		return super.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
		// TODO Auto-generated method stub
		super.visitTryCatchBlock(start, end, handler, type);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		// TODO Auto-generated method stub
		return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
	}

	@Override
	public void visitVarInsn(int opCode, int var) {
		// TODO Auto-generated method stub
		super.visitVarInsn(opCode, var);
	}
}
