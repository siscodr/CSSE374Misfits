package ClassStorage;

import java.util.ArrayList;
import java.util.List;

import MisfitsPackage.WorkerForArrows;
import jdk.internal.org.objectweb.asm.Type;

public class ClassContainer {
	private String className;
	private ArrayList<FieldStorage> fields;
	private ArrayList<MethodStorage> methods;
	private ArrayList<String> uses;
	private String supers;
	private ArrayList<String> interfaces;
	private StringBuffer fieldBuffer;
	private StringBuffer methodBuffer;

	public ClassContainer(String className) {
		this.className = className;
		this.fields = new ArrayList<FieldStorage>();
		this.methods = new ArrayList<MethodStorage>();
		this.uses = new ArrayList<String>();
		this.supers = "";
		this.interfaces = new ArrayList<String>();
		this.fieldBuffer = new StringBuffer();
		this.methodBuffer = new StringBuffer();
	}

	public String getClassName() {
		return className;
	}

	public ArrayList<FieldStorage> getFields() {
		return fields;
	}

	public ArrayList<MethodStorage> getMethods() {
		return methods;
	}

	public ArrayList<String> getUses() {
		return uses;
	}

	public String getSupers() {
		return supers;
	}

	public ArrayList<String> getInterfaces() {
		return interfaces;
	}

	public StringBuffer getMethodBuffer() {
		return methodBuffer;
	}

	public StringBuffer getfieldBuffer() {
		return fieldBuffer;
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
			this.uses.add(cleanType);
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
	 * Adds the given fields to the List of fields associated with current
	 * class.
	 * 
	 * @param type
	 *            The type to add to fields.
	 * 
	 * @return No return value.
	 */
	public void addField(String type, int access) {
		String cleanType = WorkerForArrows.stripFunction(type);
		if (checkExistingArrow(cleanType))
			fields.add(new FieldStorage(access, cleanType));
	}

	/**
	 * Adds the given field to the List of fields associated with current class.
	 * 
	 * @param desc
	 *            The field's Descriptor.
	 * 
	 * @return No return value.
	 */
	public void addFieldDesc(String desc, int access) {
		String currentType = Type.getType(desc).getClassName();
		addField(currentType, access);
	}

	/**
	 * Adds the given method to the List of classes associated with current
	 * class.
	 * 
	 * @param desc
	 *            The method's Descriptor.
	 * 
	 * @return No return value.
	 */
	public void addMethodDesc(String name, String desc, int access) {
		this.methods.add(new MethodStorage(name, desc, access));
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
				this.supers = (cleanType);
		}
	}

	public void addInterface(String interfaceName) {
		String cleanType = WorkerForArrows.stripFunction(interfaceName);
		if (checkExistingArrow(cleanType))
			interfaces.add(cleanType);
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
	 * Checks the given string to see if it should be added to the diagram.
	 * 
	 * @param cleanType
	 *            A String that has been Stripped prior to being passed to be
	 *            checked.
	 * @return boolean If the arrow should be added to the diagram
	 */
	public boolean checkExistingArrow(String cleanType) {
		return !uses.contains(cleanType) && checkfields(cleanType) && !supers.contains(cleanType)
				&& !interfaces.contains(cleanType) && WorkerForArrows.unwantedTypes(cleanType);
	}

	private boolean checkfields(String cleanType) {
		for (FieldStorage field : this.fields) {
			if (field.getType().equals(cleanType)) {
				return false;
			}
		}
		return true;
	}

}
