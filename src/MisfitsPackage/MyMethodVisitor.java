package MisfitsPackage;

import java.util.ArrayList;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

/**
 * MyMethodVisitor allows the functionality to pull types from inside of a
 * method for the purpose of a UML
 * 
 * @author TheMisfits
 */
public class MyMethodVisitor extends MethodVisitor {

	private ArrayList<Instruction> instructions;
	private String name;

	/**
	 * Constructs a new MyMethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public MyMethodVisitor(int api, String name) {
		super(api);
		this.instructions = new ArrayList<Instruction>();
		this.name = name;
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
	public MyMethodVisitor(int api, MethodVisitor toDecorate, String name) {
		super(api, toDecorate);
		this.instructions = new ArrayList<Instruction>();
		this.name = name;
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
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean bool) {
		super.visitMethodInsn(opcode, owner, name, desc, bool);

		String toClean = Type.getReturnType(desc).getClassName();
		UMLArrows arrows = UMLArrows.getInstance();
		// Adds first use arrow here
		arrows.addUse(toClean);
		// Adds second use arrow here
		arrows.addUse(owner);

		// For SD
		Instruction instr = new MethodInstruction(owner, name, desc);
		instructions.add(instr);

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
		Instruction instr = new TypeInstruction(opCode, val);
		instructions.add(instr);

		// Creates an use arrow
		UMLArrows.getInstance().addUse(val);
	}

	@Override
	public void visitVarInsn(int opCode, int var) {
		// Instruction instr = new VariableInstruction(Opcode)
		// instructions.add(instr);
		//// aLoad is for references, the others are primitives (iLoad, fload,
		// dload, lload)
		//// aStore is for references, the others are primitives (iStore,
		// fStore, dStore, lStore)
		//// ret is a Return instruction
		super.visitVarInsn(opCode, var);
	}

	// Not used in current implementation
	public AnnotationVisitor VisitAnnotation(String desc, boolean visible) {
		return super.visitAnnotation(desc, visible);
	}

	public void visitCode() {
		super.visitCode();
	}

	public void visitEnd() {
		SDArrows.getInstance().addItemsToHashMap(name, instructions);
		super.visitEnd();
	}

	// Not used for current implementation
//	@Override
//	public void visitFieldInsn(int opCode, String owner, String name, String desc) {
//		//// Instruction fieldInsn()
//		super.visitFieldInsn(opCode, owner, name, desc);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
//		super.visitLocalVariable(name, desc, signature, start, end, index);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
//		return super.visitAnnotation(desc, visible);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitAnnotationDefault() {
//		return super.visitAnnotationDefault();
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitAttribute(Attribute attr) {
//		super.visitAttribute(attr);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
//		super.visitFrame(type, nLocal, local, nStack, stack);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitIincInsn(int var, int increment) {
//		super.visitIincInsn(var, increment);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitInsn(int opCode) {
//		super.visitInsn(opCode);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
//		return super.visitInsnAnnotation(typeRef, typePath, desc, visible);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitIntInsn(int opCode, int operand) {
//		super.visitIntInsn(opCode, operand);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
//		super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
//	}
//
//	// Unnecessary for our implementation (deals with ifs)
//	@Override
//	public void visitJumpInsn(int opCode, Label label) {
//		super.visitJumpInsn(opCode, label);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitLabel(Label label) {
//		super.visitLabel(label);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitLdcInsn(Object cst) {
//		super.visitLdcInsn(cst);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitLineNumber(int line, Label start) {
//		super.visitLineNumber(line, start);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end,
//			int[] index, String desc, boolean visible) {
//		return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, desc, visible);
//	}
//
//	// Unnecessary for our implementation (deals with switch statements)
//	@Override
//	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
//		super.visitLookupSwitchInsn(dflt, keys, labels);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitMaxs(int maxStack, int maxLocals) {
//		super.visitMaxs(maxStack, maxLocals);
//	}
//
//	// Might be useful - makes new arrays
//	@Override
//	public void visitMultiANewArrayInsn(String desc, int dims) {
//		super.visitMultiANewArrayInsn(desc, dims);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitParameter(String name, int access) {
//		super.visitParameter(name, access);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
//		return super.visitParameterAnnotation(parameter, desc, visible);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
//		super.visitTableSwitchInsn(min, max, dflt, labels);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
//		return super.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
//		super.visitTryCatchBlock(start, end, handler, type);
//	}
//
//	// Unnecessary for our implementation
//	@Override
//	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
//		return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
//	}
//
}
