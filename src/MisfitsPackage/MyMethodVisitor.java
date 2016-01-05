package MisfitsPackage;

import jdk.internal.org.objectweb.asm.MethodVisitor;

public class MyMethodVisitor extends MethodVisitor {

	public MyMethodVisitor(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public MyMethodVisitor(int value, MethodVisitor methodVisitor) {
		super(value, methodVisitor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitTypeInsn(int opCode, String val) {
		super.visitTypeInsn(opCode, val);
		String cleanType = DesignParser.stripFunction(val);
		if (!DesignParser.uses.contains(cleanType) && !DesignParser.fields.contains(cleanType)
				&& !DesignParser.takes.contains(cleanType) && !DesignParser.toDelete.contains(cleanType)) {
			DesignParser.uses.add(cleanType);
		}

	}

}
