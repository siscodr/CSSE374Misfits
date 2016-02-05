package Visitors;

import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.signature.SignatureVisitor;

public class mySignatureVisitor extends SignatureVisitor {
	private int access;
	
	public mySignatureVisitor(int api, int access) {
		super(api);
		this.access = access;
	}

	public void visitClassType(String name) {
		UMLArrows.getInstance().addField(name, this.access);
		UMLArrows.getInstance().addCollectionDataType(name);
		super.visitClassType(name);
	}
}
