package MisfitsPackage;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

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
		if (!DesignParser.uses.contains(cleanType)
				&& !DesignParser.fields.contains(cleanType)
				&& !DesignParser.takes.contains(cleanType)
				&& DesignParser.unwantedTypes(cleanType)) {
			DesignParser.uses.add(cleanType);
		}

	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, boolean bool) {
		super.visitMethodInsn(opcode, owner, name, desc, bool);
		String toClean = Type.getReturnType(desc).getClassName();
		if (toClean.length() != 1) {
			String cleanType = DesignParser.stripFunction(toClean);
			if (!DesignParser.uses.contains(cleanType)
					&& !DesignParser.fields.contains(cleanType)
					&& !DesignParser.takes.contains(cleanType)
					&& DesignParser.unwantedTypes(cleanType)) {
				DesignParser.uses.add(cleanType);
			}
		}
		String toClean2 = owner;
		String cleanType = DesignParser.stripFunction(toClean2);
		if (!DesignParser.uses.contains(cleanType)
				&& !DesignParser.fields.contains(cleanType)
				&& !DesignParser.takes.contains(cleanType)
				&& DesignParser.unwantedTypes(cleanType)) {
			DesignParser.uses.add(cleanType);
		}
	}

}
