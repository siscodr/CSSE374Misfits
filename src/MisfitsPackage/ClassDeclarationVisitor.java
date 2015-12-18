package MisfitsPackage;

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
		String newName = name.replace(".","_");
		newName = newName.replace("/","_");
		
		for(int i = 0; i< interfaces.length; i++){
		String newInterface = interfaces[i].replace("/", "_");
		newInterface = newInterface.replace(".", "_");
		System.out.println(newName + " -> " + newInterface + " [arrowhead=\"onormal\", style=\"dashed\"];");
		}
		//Draw extend arrows here
		if(superName != null) {
			String newSuper = superName.replace("/", "_");
			newSuper = newSuper.replace(".", "_");
			System.out.println(newName + " -> " + newSuper + " [arrowhead=\"onormal\"];");
		}
		
		//Class starts here
		System.out.println("   " + newName + " [");
		System.out.println("     shape=\"record\"");
		System.out.print("     label = \"{" + newName + "|");
		
//		System.out.print("Class: "+name);
//		if(superName != null)
//		System.out.print(" extends "+superName);
//		if(interfaces.length > 0)
//		System.out.println(" implements "+ Arrays.toString(interfaces));	
		super.visit(version,  access,  name,  signature,  superName,  interfaces);
	}
}
