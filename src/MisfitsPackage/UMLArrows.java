package MisfitsPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

public class UMLArrows {
	private static UMLArrows ourArrows = new UMLArrows();
	private ArrayList<String> fields;
	private ArrayList<String> uses;
	private ArrayList<String> supers;
	private ArrayList<String> interfaces;
	private StringBuffer fieldBuffer = new StringBuffer();
	private StringBuffer methodBuffer = new StringBuffer();

	private ArrayList<String> toDelete = new ArrayList<String>(Arrays.asList(
			"boolean", "java_lang", "java_util"));

	private UMLArrows() {
		clearArrows();
	}

	public static UMLArrows getInstance() {
		return ourArrows;
	}

	public ArrayList<String> getFields() {
		return fields;
	}

	public ArrayList<String> getUses() {
		return uses;
	}

	public ArrayList<String> getInterfaces() {
		return interfaces;
	}

	public ArrayList<String> getSupers() {
		return supers;
	}

	public ArrayList<String> getToDelete() {
		return toDelete;
	}

	public void clearArrows() {
		fields = new ArrayList<String>();
		uses = new ArrayList<String>();
		interfaces = new ArrayList<String>();
		supers = new ArrayList<String>();
	}

	public void addToFieldBuffer(String toAppend) {
		this.fieldBuffer.append(toAppend);
	}

	public void addToMethodBuffer(String toAppend) {
		this.methodBuffer.append(toAppend);
	}

	public void addUse(String currentType) {
		String cleanType = stripFunction(currentType);
		if (checkExistingArrow(cleanType))
			uses.add(cleanType);
	}

	public void addUses(String desc) {
		List<String> stypes = getTypesFromDesc(desc);
		for (String types : stypes) {
			addUse(types);
		}
	}

	public void addField(String currentType) {
		String cleanType = stripFunction(currentType);
		if (checkExistingArrow(cleanType))
			fields.add(cleanType);
	}

	public void addSuper(String currentType) {
		String cleanType = stripFunction(currentType);
		if (checkExistingArrow(cleanType))
			supers.add(cleanType);
	}

	public void addInterface(String currentType) {
		String cleanType = stripFunction(currentType);
		if (checkExistingArrow(cleanType))
			interfaces.add(cleanType);
	}

	public void printClass(String name) {
		String newName = stripFunction(name);
		System.out.println("   " + newName
				+ " [\n     shape=\"record\"     label = \"{" + newName + "|"
				+ fieldBuffer.toString() + "|" + methodBuffer.toString()
				+ "\n}\"\n];");
		printArrows(newName);
	}

	public void addFieldToBuffer(int access, String name, String type) {
		if (name.charAt(0) != '<') {
			String symbol = "";
			if ((access & Opcodes.ACC_PUBLIC) != 0) {
				symbol = "+";
			} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
				symbol = "-";
			} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
				symbol = "#";
			}
			String type2 = stripFunction(type);
			String temp = symbol + " " + name + " : " + type2 + "\\l";
			this.addToFieldBuffer(temp);
		}
	}

	public void addMethodToBuffer(int access, String name, String desc) {
		if (name.charAt(0) != '<') {

			String rType = Type.getReturnType(desc).getClassName();
			List<String> stypes = getTypesFromDesc(desc);
			String symbol = "";
			if ((access & Opcodes.ACC_PUBLIC) != 0) {
				symbol = "+";
			} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
				symbol = "-";
			} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
				symbol = "#";
			}
			String returnType = stripFunction(rType);
			String temp = symbol + name + "(" + stypes.toString() + ") : "
					+ returnType + "\\l ";

			this.addToMethodBuffer(temp);
		}
	}

	private boolean unwantedTypes(String cleanType) {
		for (String text : toDelete) {
			if (cleanType.contains(text)) {
				return false;
			}
		}
		return true;
	}

	private List<String> getTypesFromDesc(String desc) {
		Type[] argTypes = Type.getArgumentTypes(desc);
		List<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
		}
		return stypes;
	}

	private boolean checkExistingArrow(String cleanType) {
		return !uses.contains(cleanType) && !fields.contains(cleanType)
				&& !supers.contains(cleanType)
				&& !interfaces.contains(cleanType) && unwantedTypes(cleanType);
	}

	private String stripFunction(String toStrip) {
		toStrip = toStrip.replace(".", "_");
		toStrip = toStrip.replace("/", "_");
		toStrip = toStrip.replace("[", "");
		toStrip = toStrip.replace("]", "");
		return toStrip;
	}

	private void printArrows(String classString) {
		printUses(classString);
		printFields(classString);
		printInterfaces(classString);
		printSupers(classString);
		UMLArrows.getInstance().clearArrows();
	}

	private void printUses(String className) {
		for (String types : this.uses) {
			if (types.contains("_")) {
				System.out.println(className + " -> " + types
						+ " [arrowhead=\"vee\", style=\"dashed\"];");
			}
		}
	}

	private void printFields(String className) {
		for (String field : this.fields) {
			if (field.contains("_")) {
				System.out.println(field + " -> " + className
						+ " [arrowhead=\"vee\"];");
			}
		}
	}

	private void printInterfaces(String className) {
		for (String interf : this.interfaces) {
			if (interf.contains("_")) {
				System.out.println(className + " -> " + interf
						+ " [arrowhead=\"onormal\", style=\"dashed\"];");
			}
		}
	}

	private void printSupers(String className) {
		for (String extended : this.supers) {
			if (extended.contains("_")) {
				System.out.println(className + " -> " + extended
						+ " [arrowhead=\"onormal\"];");
			}
		}
	}

}
