package TestMisfitsPackage;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

import org.junit.Test;

import MisfitsPackage.ClassFieldVisitor;

public class TestFieldVisitor {

	@Test
	public void testVisitField() {
		int access=0;
		String name="testField";
		String desc = Type.getDescriptor(int.class);
		String signature = "Signature";
		Object value = null;
		
		String expected =" testField : int\\l";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		ClassFieldVisitor visitor= new ClassFieldVisitor(Opcodes.ASM5);
		visitor.visitField(access, name, desc, signature, value);
		
		String actual = new String(out.toByteArray());
		
		assertEquals(expected, actual);
	}

}
