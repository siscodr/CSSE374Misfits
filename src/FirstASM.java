

import java.io.IOException;
import java.io.PrintWriter;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

public class FirstASM {
	public static void main(String[] args) throws IOException{
		ClassReader reader = new ClassReader("problem.asm.FirstASM");
		
		ClassVisitor visitor = new TraceClassVisitor(new PrintWriter(System.out));
	
		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		
		
	}
}
