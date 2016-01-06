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
		DesignParser.setFirstMethod(true);
		
		int access =0;
		String name = "testMethod";
		String desc = Type.getMethodDescriptor(Type.getType(int.class),
                Type.getType(double.class));
		String signature="Signature";
		String[] exceptions = new String[2];
		exceptions[0]="Exception 1";
		exceptions[1]="Exception 2";
		
		String expected = "|testMethod([double]) : int\\l ";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		ClassMethodVisitor visitor = new ClassMethodVisitor(Opcodes.ASM5);
		visitor.visitMethod(access, name, desc, signature, exceptions);

		String actual = new String(out.toByteArray());

		assertEquals(expected, actual);
	}
}
