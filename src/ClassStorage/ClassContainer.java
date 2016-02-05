package ClassStorage;

import java.util.ArrayList;
import java.util.List;

import MisfitsPackage.WorkerForArrows;
import jdk.internal.org.objectweb.asm.Type;

public class ClassContainer {
	private String className;
	private String label;
	private ArrayList<FieldStorage> fields;
	private ArrayList<MethodStorage> methods;
	private ArrayList<ArrowStorage> uses;
	private ArrowStorage supers;
	private ArrayList<ArrowStorage> interfaces;
	private StringBuffer fieldBuffer;
	private StringBuffer methodBuffer;
	private boolean isInterface;
	private ArrayList<MethodFieldsStorage> methodsField;
	private String color;
	private String fillColor;
	private ArrayList<String> collectionDataTypes;

	public ClassContainer(String className) {
		this.className = className;
		this.fields = new ArrayList<FieldStorage>();
		this.methods = new ArrayList<MethodStorage>();
		this.uses = new ArrayList<ArrowStorage>();
		this.supers = null;
		this.interfaces = new ArrayList<ArrowStorage>();
		this.fieldBuffer = new StringBuffer();
		this.methodBuffer = new StringBuffer();
		this.isInterface = false;
		this.methodsField = new ArrayList<MethodFieldsStorage>();
		this.collectionDataTypes = new ArrayList<String>();
		label = "";
		color = "";
		fillColor = "";
	}

	public ArrayList<String> getCollectionDataTypes() {
		return collectionDataTypes;
	}

	public void addCollectionDataType(String dataType) {
		if (WorkerForArrows.unwantedTypes(dataType)) {
			collectionDataTypes.add(dataType);
		}
	}

	public String getColor() {
		if (color != "") {
			return "color=\"" + color + "\" ";
		}
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFillColor() {
		if (fillColor != "") {
			return ", fillcolor=\"" + fillColor + "\" style=\"filled\"";
		}
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public ArrayList<MethodFieldsStorage> getMethodsField() {
		return methodsField;
	}

	public void setMethodsField(ArrayList<MethodFieldsStorage> methodsField) {
		this.methodsField = methodsField;
	}

	public String getLabel() {
		if (this.label != "") {
			return "\\n\\<\\<" + this.label + "\\>\\>";
		}
		return "";
	}

	public void setLabel(String label) {
		this.label = label;
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

	public ArrayList<ArrowStorage> getUses() {
		return uses;
	}

	public ArrowStorage getSupers() {
		return supers;
	}

	public ArrayList<ArrowStorage> getInterfaces() {
		return interfaces;
	}

	public StringBuffer getMethodBuffer() {
		return methodBuffer;
	}

	public StringBuffer getfieldBuffer() {
		return fieldBuffer;
	}

	public void setIsInterface(boolean isInterface) {
		this.isInterface = isInterface;
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
			this.uses.add(new ArrowStorage(cleanType));
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
			if (WorkerForArrows.unwantedTypes(cleanType)) {
				this.supers = new ArrowStorage(cleanType);
			}
		}
	}

	public void addInterface(String interfaceName) {
		String cleanType = WorkerForArrows.stripFunction(interfaceName);
		if (checkExistingArrow(cleanType))
			interfaces.add(new ArrowStorage(cleanType));
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
		boolean flag;
		flag = WorkerForArrows.unwantedTypes(cleanType);
		if (flag) {
			flag = checkfields(cleanType);
		}
		if (flag) {
			flag = checkUses(cleanType);
		}
		if (flag) {
			flag = checkInterfaces(cleanType);
		}
		if (supers != null) {
			if (cleanType.equals(supers.getTargetType())) {
				flag = false;
			}
		}

		// !uses.contains(cleanType) && checkfields(cleanType) &&
		// !supers.getTargetType().equals(cleanType)
		// && !interfaces.contains(cleanType) &&
		// WorkerForArrows.unwantedTypes(cleanType);
		return flag;
	}

	private boolean checkInterfaces(String cleanType) {
		for (ArrowStorage tempInterface : interfaces) {
			if (cleanType.equals(tempInterface.getTargetType())) {
				return false;
			}
		}
		return true;
	}

	private boolean checkUses(String cleanType) {
		for (ArrowStorage use : uses) {
			if (cleanType.equals(use.getTargetType())) {
				return false;
			}
		}
		return true;
	}

	private boolean checkfields(String cleanType) {
		for (FieldStorage field : this.fields) {
			if (field.getType().equals(cleanType)) {
				return false;
			}
		}
		return true;
	}

	public boolean getIsInterface() {
		return this.isInterface;
	}

	public void addMethodFields(MethodFieldsStorage methodFieldsStorage) {
		this.methodsField.add(methodFieldsStorage);
	}

}
