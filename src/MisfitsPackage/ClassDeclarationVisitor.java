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
		
		System.out.println("   " + name + " [");
		System.out.println("     shape=\"record\"");
		System.out.print("     label = \"{" + name + "|");
		
//		System.out.print("Class: "+name);
//		if(superName != null)
//		System.out.print(" extends "+superName);
//		if(interfaces.length > 0)
//		System.out.println(" implements "+ Arrays.toString(interfaces));	
		super.visit(version,  access,  name,  signature,  superName,  interfaces);
		postVisit();
	}

	public void postVisit(){
		System.out.println("}\"");
		System.out.println("];");
	}
	
}
