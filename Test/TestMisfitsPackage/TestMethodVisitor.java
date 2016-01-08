package TestMisfitsPackage;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

import org.junit.Test;

import MisfitsPackage.ClassMethodVisitor;
import MisfitsPackage.DesignParser;

public class TestMethodVisitor {

	@Test
	public void testVisitMethod() {
		
		String signature="Signature"; // these are not yet used in implementation
		String[] exceptions = new String[2]; //same
		exceptions[0]="Exception 1"; //same
		exceptions[1]="Exception 2"; //same
		
		DesignParser.setFirstMethod(true);
		
		int access = Opcodes.ACC_PUBLIC;
		String name = "testMethod";
		String desc = Type.getMethodDescriptor(Type.getType(int.class),
                Type.getType(double.class));
		
		
		String expected = "|+testMethod([double]) : int\\l ";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		ClassMethodVisitor visitor = new ClassMethodVisitor(Opcodes.ASM5);
		visitor.visitMethod(access, name, desc, signature, exceptions);

		String actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
		//I am now testing the access branch
		DesignParser.setFirstMethod(true);
		access = Opcodes.ACC_PRIVATE;
		name = "testMethod";
		desc = Type.getMethodDescriptor(Type.getType(int.class),
                Type.getType(double.class));
		
		
		expected = "|-testMethod([double]) : int\\l ";

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor = new ClassMethodVisitor(Opcodes.ASM5);
		visitor.visitMethod(access, name, desc, signature, exceptions);

		actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
		//I am not testing the protected
		//I am now testing the access branch
		DesignParser.setFirstMethod(true);
		access = Opcodes.ACC_PROTECTED;
		name = "testMethod";
		desc = Type.getMethodDescriptor(Type.getType(int.class),
                Type.getType(double.class));
		
		
		expected = "|#testMethod([double]) : int\\l ";

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor = new ClassMethodVisitor(Opcodes.ASM5);
		visitor.visitMethod(access, name, desc, signature, exceptions);

		actual = new String(out.toByteArray());

		assertEquals(expected, actual);
	}
	
	
}
