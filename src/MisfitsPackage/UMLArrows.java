package MisfitsPackage;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

/**
 * UMLArrows is a singleton class used to maintain the output of the UML and to
 * collect information on the classes used
 * 
 * @author TheMisfits
 */
public class UMLArrows {
	private static UMLArrows ourArrows = new UMLArrows();
	private ArrayList<String> fields;
	private ArrayList<String> uses;
	private String supers;
	private ArrayList<String> interfaces;
	private StringBuffer fieldBuffer = new StringBuffer();
	private StringBuffer methodBuffer = new StringBuffer();

	/**
	 * Constructs a UMLArrows
	 */
	private UMLArrows() {
		resetUMLArrows();
	}

	/**
	 * Gets the current instance of UMLArrow
	 * 
	 * @return UMLArrow
	 */
	public static UMLArrows getInstance() {
		return ourArrows;
	}

	/**
	 * Getter for the fields associated with the current class
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getFields() {
		return this.fields;
	}

	/**
	 * Getter for the classes that are 'used' by the current class.
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getUses() {
		return this.uses;
	}

	/**
	 * Getter for the interfaces that the current class implements.
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getInterfaces() {
		return interfaces;
	}

	/**
	 * Getter for the class that the current class extends.
	 * 
	 * @return String
	 */
	public String getSupers() {
		return supers;
	}

	/**
	 * Resets the ArrayLists used to make arrows.
	 * 
	 * @return No return value.
	 */
	public void resetUMLArrows() {
		fields = new ArrayList<String>();
		uses = new ArrayList<String>();
		interfaces = new ArrayList<String>();
		supers = "";
		fieldBuffer = new StringBuffer();
		methodBuffer = new StringBuffer();
	}

	/**
	 * Append the given string to the end of the FieldBuffer
	 * 
	 * @param toAppend
	 *            A String to append to the current FieldBuffer
	 * @return No return value.
	 */
	public void addToFieldBuffer(String toAppend) {
		this.fieldBuffer.append(toAppend);
	}

	/**
	 * Append the given string to the end of the MethodBuffer
	 * 
	 * @param toAppend
	 *            A String to append to the current MethodBuffer
	 * @return No return value.
	 */
	public void addToMethodBuffer(String toAppend) {
		this.methodBuffer.append(toAppend);
	}

	/**
	 * Adds the given class to the list of classes that the current class uses.
	 * 
	 * @param currentType
	 *            A String to represent the class that is being used by the
	 *            current class.
	 * @return No return value.
	 */
	public void addUse(String currentType) {
		String cleanType = WorkerForArrows.stripFunction(currentType);
		if (checkExistingArrow(cleanType))
			uses.add(cleanType);
	}

	/**
	 * Takes a Method descriptor and adds all the types to the list of classes
	 * being used by the current class.
	 * 
	 * @param desc
	 *            A method descriptor that can be used to determine the types
	 *            used by the current class.
	 * @return No return value.
	 */
	public void addUses(String desc) {
		List<String> stypes = WorkerForArrows.getTypesFromDesc(desc);
		for (String types : stypes) {
			addUse(types);
		}
	}

	/**
	 * Adds the given class to the List of classes associated with current
	 * class.
	 * 
	 * @param type
	 *            The type to add to fields.
	 * 
	 * @return No return value.
	 */
	public void addField(String type) {
		String cleanType = WorkerForArrows.stripFunction(type);
		if (checkExistingArrow(cleanType))
			fields.add(cleanType);
	}

	/**
	 * Adds the given class to the List of classes associated with current
	 * class.
	 * 
	 * @param desc
	 *            The field's Descriptor.
	 * 
	 * @return No return value.
	 */
	public void addFieldDesc(String desc) {
		String currentType = Type.getType(desc).getClassName();
		addField(currentType);
	}

	/**
	 * Adds the given class to the List of classes that this class extends.
	 * 
	 * @param currentType
	 *            A String to represent a class that is extended by the current
	 *            class.
	 * @return No return value.
	 */
	public void setSuper(String currentType) {
		if (currentType != null) {
			String cleanType = WorkerForArrows.stripFunction(currentType);
			if (checkExistingArrow(cleanType))
				supers = (cleanType);
		}
	}

	/**
	 * Adds the given class to the List of classes that interfaces of this class
	 * .
	 * 
	 * @param currentType
	 *            A String to represent a class that is an interface for the
	 *            current class.
	 * @return No return value.
	 */
	public void addInterface(String currentType) {
		String cleanType = WorkerForArrows.stripFunction(currentType);
		if (checkExistingArrow(cleanType))
			interfaces.add(cleanType);
	}

	/**
	 * Print all the Buffers and Arrows to the Console. Then resets fields to
	 * setup for the next class.
	 * 
	 * TODO:Add functionality to allow to export this as a file (Out of project
	 * scope).
	 * 
	 * @param name
	 *            A String representation of name of the current class
	 * @return No return value
	 */
	public void printClass(String name) {
		String newName = WorkerForArrows.stripFunction(name);
		System.out.print("   " + newName
				+ " [\n     shape=\"record\"     label = \"{" + newName + "|"
				+ fieldBuffer.toString() + "|" + methodBuffer.toString()
				+ "\n}\"\n];\n");
		printArrows(newName);
		UMLArrows.getInstance().resetUMLArrows();
	}

	/**
	 * Takes the given parameters to add to the field buffer in GraphViz format.
	 * 
	 * @param access
	 *            The field's access flags.
	 * @param name
	 *            The Internal name of the field.
	 * @param desc
	 *            The field's descriptor.
	 * @return No return value.
	 */
	public void addFieldToBuffer(int access, String name, String desc) {
		String type = Type.getType(desc).getClassName();
		if (name.charAt(0) != '<') {
			String symbol = WorkerForArrows.makeSymbol(access);
			String type2 = WorkerForArrows.stripFunction(type);
			String temp = symbol + " " + name + " : " + type2 + "\\l";
			this.addToFieldBuffer(temp);
		}
	}

	/**
	 * Takes the given parameters to add to the method buffer in GraphViz
	 * format.
	 * 
	 * @param access
	 *            The method's access flags.
	 * @param name
	 *            The Internal name of the method.
	 * @param desc
	 *            The method's descriptor.
	 * @return No return value.
	 */
	public void addMethodToBuffer(int access, String name, String desc) {
		if (name.charAt(0) != '<') {

			String rType = Type.getReturnType(desc).getClassName();
			List<String> stypes = WorkerForArrows.getTypesFromDesc(desc);
			String symbol = WorkerForArrows.makeSymbol(access);
			String returnType = WorkerForArrows.stripFunction(rType);
			String temp = symbol + name + "(" + stypes.toString() + ") : "
					+ returnType + "\\l ";

			this.addToMethodBuffer(temp);
		}
	}

	/**
	 * Checks the given string to see if it should be added to the diagram.
	 * 
	 * @param cleanType
	 *            A String that has been Stripped prior to being passed to be
	 *            checked.
	 * @return boolean If the arrow should be added to the diagram
	 */
	private boolean checkExistingArrow(String cleanType) {
		return !uses.contains(cleanType) && !fields.contains(cleanType)
				&& !supers.contains(cleanType)
				&& !interfaces.contains(cleanType)
				&& WorkerForArrows.unwantedTypes(cleanType);
	}

	/**
	 * Prints all the arrows to the console.
	 * 
	 * @param classString
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return value
	 */
	private void printArrows(String classString) {
		printUses(classString);
		printFields(classString);
		printInterfaces(classString);
		printSupers(classString);
	}

	/**
	 * Prints all 'Use' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private void printUses(String className) {
		for (String types : this.uses) {
			if (types.contains("_")) {
				System.out.println(className + " -> " + types
						+ " [arrowhead=\"vee\", style=\"dashed\"];");
			}
		}
	}

	/**
	 * Prints all 'Association' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private void printFields(String className) {
		for (String field : this.fields) {
			if (field.contains("_")) {
				System.out.println(className + " -> " + field
						+ " [arrowhead=\"vee\"];");
			}
		}
	}

	/**
	 * Prints all 'Interface' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private void printInterfaces(String className) {
		for (String interf : this.interfaces) {
			if (interf.contains("_")) {
				System.out.println(className + " -> " + interf
						+ " [arrowhead=\"onormal\", style=\"dashed\"];");
			}
		}
	}

	/**
	 * Prints all 'Extension' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private void printSupers(String className) {
		if (supers.contains("_")) {
			System.out.println(className + " -> " + supers
					+ " [arrowhead=\"onormal\"];");
		}
	}
}
