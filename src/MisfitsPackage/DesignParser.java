package MisfitsPackage;

import java.io.IOException;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class DesignParser {
	private int cheese;
	
	public static void main(String[] args) throws IOException{
		for(String className: args){
			ClassReader reader = new ClassReader(className);
			
			ClassVisitor decIVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
			
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decIVisitor);
			
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
			
			System.out.println("digraph misfit_diagram{");
			System.out.println("rankdir=BT;");
			
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		}
	}
}
