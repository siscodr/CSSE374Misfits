package Visitors;

import java.util.ArrayList;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import ClassStorage.MethodCallStorage;
import ClassStorage.MethodFieldsStorage;
import ClassStorage.MethodStorage;
import MisfitsPackage.WorkerForArrows;
import SDClasses.Instruction;
import SDClasses.SDArrows;
import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class MyMethodVisitorCalls extends MethodVisitor {
	private ArrayList<MethodCallStorage> methodsCalled;
	private String name;
	private String desc;
	private int access;

	/**
	 * Constructs a new MyMethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 */
	public MyMethodVisitorCalls(int api, String name, String desc, int access) {
		super(api);
		this.methodsCalled = new ArrayList<MethodCallStorage>();
		this.name = name;
		this.desc = desc;
		this.access = access;
	}

	/**
	 * Constructs a new MyMethodVisitor that decorates the old MethodVisitor.
	 * 
	 * @param api
	 *            the ASM API version implemented by this visitor. Must be one
	 *            of Opcodes.ASM4.
	 * @param toDecorate
	 *            A MethodVisitor for this class to Decorate
	 * @param access
	 * @param desc
	 */
	public MyMethodVisitorCalls(int api, MethodVisitor toDecorate, String name,
			String desc, int access) {
		super(api, toDecorate);
		this.methodsCalled = new ArrayList<MethodCallStorage>();
		this.name = name;
		this.desc = desc;
		this.access = access;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		this.methodsCalled.add(new MethodCallStorage(name, owner, desc));
	}

	public void visitEnd() {
		UMLArrows.getInstance().addMethodDesc(name, desc, access, methodsCalled);
		super.visitEnd();
	}

}
