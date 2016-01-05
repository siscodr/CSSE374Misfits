package TestMisfitsPackage;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

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
		
		String expected = "TestClass -> TestInterface1 [arrowhead=\"onormal\", style=\"dashed\"];"+System.lineSeparator()+
				"TestClass -> TestInterface2 [arrowhead=\"onormal\", style=\"dashed\"];"+System.lineSeparator()+
				"TestClass -> SuperTestClass [arrowhead=\"onormal\"];"+System.lineSeparator()+
				"   TestClass ["+System.lineSeparator()+
				"     shape=\"record\""+System.lineSeparator()+
				"     label = \"{TestClass|";
		
		
		// redirect stdout to save the result to a byte array
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		visitor.visit(version, access, name, signature, superName, interfaces);
		
		String actual = new String(out.toByteArray());
		
		assertEquals(expected, actual);
		
	}
	
}
