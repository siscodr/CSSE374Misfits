package MisfitsPackage;


import java.util.Arrays;

import jdk.internal.org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor
{

	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ClassDeclarationVisitor(int arg0, ClassVisitor arg1){
		super(arg0,arg1);
	}
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
		for(int i = 0; i< interfaces.length; i++){
		System.out.println(name + " -> " + interfaces[i] + " [arrowhead=\"onormal\", style=\"dashed\"];");
		}
		
		System.out.println("Class: "+name+" extends "+superName+" implements "+ Arrays.toString(interfaces));
		super.visit(version,  access,  name,  signature,  superName,  interfaces);
	}
}
