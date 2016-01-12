package MisfitsPackage;

import jdk.internal.org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {

	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
	}

	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		
		UMLArrows arrows = UMLArrows.getInstance();
		// store interface arrows
		for (int i = 0; i < interfaces.length; i++) {
			arrows.addInterface(interfaces[i]);
		}
		// store extend arrow here
		if (superName != null) {
			arrows.addSuper(superName);
		}
		super.visit(version, access, name, signature, superName, interfaces);
	}
}
