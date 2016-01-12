package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * ClassMethodVisitor Decorates ClassVisitor so the Use Arrows can be drawn
 * 
 * @author TheMisfits
 */
public class ClassMethodVisitor extends ClassVisitor {

	public ClassMethodVisitor(int arg0) {
		super(arg0);
	}

	public ClassMethodVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		MethodVisitor toDecorate = super.visitMethod(access, name, desc,
				signature, exceptions);

		MethodVisitor toReturn = new MyMethodVisitor(Opcodes.ASM5, toDecorate);

		UMLArrows.getInstance().addUses(desc);
		return toReturn;
	}
}
