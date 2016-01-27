package ClassStorage;

import java.util.ArrayList;

import MisfitsPackage.WorkerForArrows;
import UMLClasses.FieldStorage;

public class ClassContainer {
	private String className;
	private ArrayList<FieldStorage> fields;
	private ArrayList<String> uses;
	private String supers;
	private ArrayList<String> interfaces;

	public ClassContainer(String className) {
		this.className = className;
		this.fields = new ArrayList<FieldStorage>();
		this.uses = new ArrayList<String>();
		this.supers = "";
		this.interfaces = new ArrayList<String>();
	}

	public ClassContainer(String className, ArrayList<FieldStorage> fields, ArrayList<String> uses, String supers,
			ArrayList<String> interfaces) {
		this.className = className;
		this.fields = fields;
		this.uses = uses;
		this.supers = supers;
		this.interfaces = interfaces;
	}

	public String getClassName() {
		return className;
	}

	public ArrayList<FieldStorage> getFields() {
		return fields;
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
	
	public void setSuper(String supers){
		this.supers = supers;
	}
	
	public void addUse(String use){
		uses.add(use);
	}

	public void addField(FieldStorage field){
		fields.add(field);
	}
	
	public void addInterface(String interfaceName){
		interfaces.add(interfaceName);
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
