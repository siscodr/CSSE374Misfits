package MisfitsPackage;


import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor {

	public ClassFieldVisitor(int arg0){
		super(arg0);
	}
	
	public ClassFieldVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value){
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();
		
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
		String type2 = type.replace("/", "_");
		type2 = type2.replace(".", "_");
		if(name.charAt(0)!='<'){
			DesignParser.buffer.append(DesignParser.classString +  " -> " + type2 + " [arrowhead=\"diamond\"];\n");
			System.out.print(symbol+ " " + name + " : " + type +"\\l");
		}
		return toDecorate;
		
	}
}
