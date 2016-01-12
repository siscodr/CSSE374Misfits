package MisfitsPackage;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

public class MyMethodVisitor extends MethodVisitor {

	public MyMethodVisitor(int value) {
		super(value);
	}

	public MyMethodVisitor(int value, MethodVisitor methodVisitor) {
		super(value, methodVisitor);
	}

	@Override
	public void visitTypeInsn(int opCode, String val) {
		super.visitTypeInsn(opCode, val);
		UMLArrows.getInstance().addUse(val);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, boolean bool) {
		super.visitMethodInsn(opcode, owner, name, desc, bool);
		String toClean = Type.getReturnType(desc).getClassName();
		UMLArrows arrows = UMLArrows.getInstance();
		arrows.addUse(toClean);
		arrows.addUse(owner);
		}
}
