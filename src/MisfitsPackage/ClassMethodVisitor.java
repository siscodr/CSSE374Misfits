package MisfitsPackage;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

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

		UMLArrows arrows = UMLArrows.getInstance();
		
		String rType = Type.getReturnType(desc).getClassName();

		Type[] argTypes = Type.getArgumentTypes(desc);

		List<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
		}

		for (String types : stypes) {
			arrows.addUse(types);
		}
		
		arrows.addMethodToBuffer(access, name, stypes, rType);
		return toReturn;
	}
}
