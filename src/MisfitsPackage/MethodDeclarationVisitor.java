package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * ClassMethodVisitor Decorates ClassVisitor so the methods are shown inside of
 * classes in the UML
 * 
 * @author TheMisfits
 */
public class MethodDeclarationVisitor extends ClassVisitor {

	public MethodDeclarationVisitor(int arg0) {
		super(arg0);
	}

	public MethodDeclarationVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		MethodVisitor toDecorate = super.visitMethod(access, name, desc,
				signature, exceptions);

		MethodVisitor toReturn = new MyMethodVisitor(Opcodes.ASM5, toDecorate);

		UMLArrows.getInstance().addMethodToBuffer(access, name, desc);
		return toReturn;
	}
}
