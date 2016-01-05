package MisfitsPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class DesignParser {
	static public ArrayList<String> fields = new ArrayList<String>();
	static public ArrayList<String> uses = new ArrayList<String>();
	static public ArrayList<String> takes = new ArrayList<String>();
	static public ArrayList<String> toDelete = new ArrayList<String>(Arrays.asList("boolean", "byte", "short", "int", "double", "java_lang_String", "long", "float", "char"));
	static public String classString = new String();
	static public Boolean firstMethod;

	public static void main(String[] args) throws IOException {
		for (String className : args) {
			classString = stripFunction(className);
			firstMethod = true;

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
				System.out.println(field + " -> " + classString
						+ " [arrowhead=\"diamond\"];");
			for (String field : takes)
				System.out.println(field + " -> " + classString
						+ " [arrowhead=\"odiamond\"];");
			System.out.println("}");
			DesignParser.fields = new ArrayList<String>();
			DesignParser.uses = new ArrayList<String>();
			DesignParser.takes = new ArrayList<String>();
		}
	}

	public static String stripFunction(String toStrip) {
		toStrip = toStrip.replace(".", "_");
		toStrip = toStrip.replace("/", "_");
		toStrip = toStrip.replace("[", "");
		toStrip = toStrip.replace("]", "");
		return toStrip;
	}

	public static Boolean getFirstMethod() {
		return firstMethod;
	}

	public static void setFirstMethod(Boolean firstMethod) {
		DesignParser.firstMethod = firstMethod;
	}
}
