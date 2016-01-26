package Visitors;

import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.signature.SignatureVisitor;

public class mySignatureVisitor extends SignatureVisitor {

	public mySignatureVisitor(int api) {
		super(api);
	}

	public void visitClassType(String name) {
		UMLArrows.getInstance().addField(name);
		super.visitClassType(name);
	}
}
