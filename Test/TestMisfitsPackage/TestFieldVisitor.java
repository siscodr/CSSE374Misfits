package TestMisfitsPackage;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

import org.junit.Test;

import MisfitsPackage.ClassDeclarationVisitor;
import MisfitsPackage.ClassFieldVisitor;
import MisfitsPackage.DesignParser;

public class TestFieldVisitor {

	@Test
	public void testConstructorWithoutClassVisitor() {
		ClassFieldVisitor visitor = new ClassFieldVisitor(Opcodes.ASM5);
		assertNotNull(visitor);
	}
	
	@Test
	public void testConstructorWithClassVisitor() {
		ClassDeclarationVisitor genericVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		ClassFieldVisitor visitor = new ClassFieldVisitor(Opcodes.ASM5,genericVisitor);
		assertNotNull(visitor);
	}
	
	@Test
	public void testVisitField() {
		int access = Opcodes.ACC_PUBLIC;
		String name = "testField";
		String desc = Type.getDescriptor(int.class);
		String signature = "Signature";		//Not used in implementation
		Object value = null;				//Not used in implementation

		String expected = "+ testField : int\\l";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		ClassFieldVisitor visitor = new ClassFieldVisitor(Opcodes.ASM5);
		visitor.visitField(access, name, desc, signature, value);

		String actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
		//test again with private access
		access=Opcodes.ACC_PRIVATE;
		
		expected = "- testField : int\\l";

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor = new ClassFieldVisitor(Opcodes.ASM5);
		visitor.visitField(access, name, desc, signature, value);

		actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
		//test again with protected access
		access=Opcodes.ACC_PROTECTED;
		
		expected = "# testField : int\\l";

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor = new ClassFieldVisitor(Opcodes.ASM5);
		visitor.visitField(access, name, desc, signature, value);

		actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
		//test again with no access
		access=0;
		
		expected = " testField : int\\l";

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor = new ClassFieldVisitor(Opcodes.ASM5);
		visitor.visitField(access, name, desc, signature, value);

		actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
		//test again with repeated type
		desc = Type.getDescriptor(int.class);
		//DesignParser.toDelete.add(Type.getType(desc).getClassName());
		access=Opcodes.ACC_PUBLIC;
		
		expected = "+ testField : int\\l";

		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor = new ClassFieldVisitor(Opcodes.ASM5);
		visitor.visitField(access, name, desc, signature, value);

		actual = new String(out.toByteArray());

		assertEquals(expected, actual);
				
	}

}
