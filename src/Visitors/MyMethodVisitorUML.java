package Visitors;

import java.util.ArrayList;

import ClassStorage.MethodFieldsStorage;
import MisfitsPackage.WorkerForArrows;
import SDClasses.Instruction;
import SDClasses.SDArrows;
import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class MyMethodVisitorUML extends MethodVisitor {
	private ArrayList<String> types;
	private String name;

	/**
	 * Constructs a new MyMethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public MyMethodVisitorUML(int api, String name) {
		super(api);
		types = new ArrayList<String>();
		this.name = name;
	}

	/**
	 * Constructs a new MyMethodVisitor that decorates the old MethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A MethodVisitor for this class to Decorate
	 */
	public MyMethodVisitorUML(int api, MethodVisitor toDecorate, String name) {
		super(api, toDecorate);
		types = new ArrayList<String>();
		this.name = name;
	}

	public void visitFieldInsn(int opCode, String owner, String name, String desc) {
		super.visitFieldInsn(opCode, owner, name, desc);
		String cleanType = WorkerForArrows.stripFunction(owner);
		if(!types.contains(cleanType)){
			types.add(cleanType);
		}
	}

	public void visitEnd() {
		UMLArrows.getInstance().addMethodFields(new MethodFieldsStorage(name, types));
		super.visitEnd();
	}

}
