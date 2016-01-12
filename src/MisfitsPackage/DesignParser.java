package MisfitsPackage;

import java.io.IOException;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class DesignParser {
	public static void main(String[] args) throws IOException {
		startDiagram("misfit_diagram");
		for (String className : args) {

			ClassReader reader = new ClassReader(className);

			ClassVisitor decIVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5,
					decIVisitor);

			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5,
					fieldVisitor);

			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);

			UMLArrows.getInstance().printClass(className);
		}
		endDiagram();
	}

	public static void startDiagram(String nameOfDiagram) {
		System.out.println("digraph " + nameOfDiagram + "{");
		System.out.println("rankdir=BT;");
	}

	public static void endDiagram() {
		System.out.println("}");
	}
}
