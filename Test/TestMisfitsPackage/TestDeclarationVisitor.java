package TestMisfitsPackage;

import static org.junit.Assert.*;
import jdk.internal.org.objectweb.asm.Opcodes;

import org.junit.Test;

import MisfitsPackage.ClassDeclarationVisitor;

public class TestDeclarationVisitor {

	@Test
	public void testVisit() {
		ClassDeclarationVisitor visitor= new ClassDeclarationVisitor(Opcodes.ASM5);
		int version=1;
		int access=0;
		String name="TestClass";
		String signature = "Signature";
		String superName = "SuperTestClass";
		String[] interfaces=new String[2];
		interfaces[0]="TestInterface1";
		interfaces[1]="TestInterface2";
		visitor.visit(version, access, name, signature, superName, interfaces);
		fail("Not yet implemented");
	}
	
}
