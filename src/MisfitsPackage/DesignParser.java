package MisfitsPackage;

import java.io.IOException;
import java.util.ArrayList;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class DesignParser {
	static public ArrayList<String> fields = new ArrayList<String>();
	static public ArrayList<String> uses = new ArrayList<String>();
	static public String classString = new String();

	public static void main(String[] args) throws IOException {
		for (String className : args) {
			classString = stripFunction(className);

			ClassReader reader = new ClassReader(className);

			ClassVisitor decIVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5,
					decIVisitor);

			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5,
					fieldVisitor);

			System.out.println("digraph misfit_diagram{");
			System.out.println("rankdir=BT;");

			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			System.out.println("}\"");
			System.out.println("];");
			for (String types : uses)
				System.out.println(classString + " -> " + types
						+ " [arrowhead=\"vee\", style=\"dashed\"];");
			for (String field : fields)
				System.out.println(fields + " -> " + classString
						+ " [arrowhead=\"diamond\"];");
		}
		System.out.println("}");
	}

	public static String stripFunction(String toStrip) {
		toStrip = toStrip.replace(".", "_");
		toStrip = toStrip.replace("/", "_");
		return toStrip;
	}
}
