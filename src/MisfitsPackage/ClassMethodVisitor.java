package MisfitsPackage;


import java.util.ArrayList;
import java.util.List;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor {

	public ClassMethodVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc,
				signature, exceptions);

		String returnType = Type.getReturnType(desc).getClassName();

		Type[] argTypes = Type.getArgumentTypes(desc);

		List<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
		}
		
		String symbol = "";
		if((access & Opcodes.ACC_PUBLIC)!= 0){
			symbol="+";
		}
		else if((access & Opcodes.ACC_PRIVATE)!=0){
			symbol="-";
		}
		else if((access & Opcodes.ACC_PROTECTED)!=0){
			symbol="#";
		}
		if(name.charAt(0)!='<'){
			System.out.print(symbol + name + "("+ stypes.toString()+") : " + returnType + "\\l ");
		}
		return toDecorate;
	}
}
