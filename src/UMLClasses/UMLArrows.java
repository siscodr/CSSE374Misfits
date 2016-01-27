package UMLClasses;

import java.util.ArrayList;
import java.util.List;

import ClassStorage.ClassContainer;
import MisfitsPackage.WorkerForArrows;
import jdk.internal.org.objectweb.asm.Type;

/**
 * UMLArrows is a singleton class used to maintain the output of the UML and to
 * collect information on the classes used
 * 
 * @author TheMisfits
 */
public class UMLArrows {
	private static UMLArrows ourArrows = new UMLArrows();
	private ArrayList<PatternDetector> detectors = new ArrayList<PatternDetector>();
	private StringBuffer fieldBuffer = new StringBuffer();
	private StringBuffer methodBuffer = new StringBuffer();
	private ClassContainer currentClass;
	
	/**
	 * Constructs a UMLArrows
	 */
	private UMLArrows() {
		resetUMLArrows("");
	}

	/**
	 * Gets the current instance of UMLArrow
	 * 
	 * @return UMLArrow
	 */
	public static UMLArrows getInstance() {
		return ourArrows;
	}

	public void addDetector(PatternDetector patternDetector) {
		detectors.add(patternDetector);
	}

	public void resetDetectors() {
		for (PatternDetector detector : detectors) {
			detector.setDetected(false);
		}
	}
	
	
	/**
	 * Resets the ArrayLists used to make arrows.
	 * 
	 * @return No return value.
	 */
	public void resetUMLArrows(String className) {
		currentClass = new ClassContainer(WorkerForArrows.stripFunction(className));
		fieldBuffer = new StringBuffer();
		methodBuffer = new StringBuffer();
		resetDetectors();
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
		if (currentClass.checkExistingArrow(cleanType))
			currentClass.addUse(cleanType);
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
	public void addField(String type, int access) {
		String cleanType = WorkerForArrows.stripFunction(type);
		if (currentClass.checkExistingArrow(cleanType))
			currentClass.addField(new FieldStorage(access, cleanType));
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
	public void addFieldDesc(String desc, int access) {
		String currentType = Type.getType(desc).getClassName();
		addField(currentType, access);
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
			if (currentClass.checkExistingArrow(cleanType))
				currentClass.setSuper(cleanType);
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
		if (currentClass.checkExistingArrow(cleanType))
			currentClass.addInterface(cleanType);
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
	public void printClass() {
		String pattern = "";
		String color = "";
		String fillColor = "";
		// Will use hashmap here in future
		/*
		 * if(isSingle){ pattern = "\\n\\<\\<Singleton\\>\\>"; color =
		 * "color=\"purple\""; }
		 */
		for (PatternDetector detector : detectors) {
			detector.detect(currentClass);
			if (detector.isDetected()) {
				pattern = "\\n\\<\\<" + detector.getPattern() + "\\>\\>";
				color = "color=\"" + detector.getColor() + "\"";
				fillColor = ", fillcolor=\"" + detector.getFillColor() + "\" style=\"filled\"";
			}
		}
		System.out.print("   " + currentClass.getClassName() + " [\n     shape=\"record\"  " + color + fillColor + "    label = \"{"
				+ currentClass.getClassName() + pattern + "|" + fieldBuffer.toString() + "|" + methodBuffer.toString() + "\n}\"\n];\n");
		printArrows();
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
			String temp = symbol + name + "(" + stypes.toString() + ") : " + returnType + "\\l ";

			this.addToMethodBuffer(temp);
		}
	}

	/**
	 * Prints all the arrows to the console.
	 * 
	 * @param classString
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return value
	 */
	private void printArrows() {
		printUses();
		printFields();
		printInterfaces();
		printSupers();
	}

	/**
	 * Prints all 'Use' arrows with this class.
	 * 
	 * @param className
	 *            A String representation that has been Stripped of the current
	 *            class
	 * @return No return type.
	 */
	private void printUses() {
		for (String types : currentClass.getUses()) {
			if (types.contains("_")) {
				System.out.println(currentClass.getClassName() + " -> " + types + " [arrowhead=\"vee\", style=\"dashed\"];");
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
	private void printFields() {
		for (FieldStorage field : currentClass.getFields()) {
			if (field.getType().contains("_")) {
				System.out.println(currentClass.getClassName() + " -> " + field.getType() + " [arrowhead=\"vee\"];");
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
	private void printInterfaces() {
		for (String interf : currentClass.getInterfaces()) {
			if (interf.contains("_")) {
				System.out.println(currentClass.getClassName() + " -> " + interf + " [arrowhead=\"onormal\", style=\"dashed\"];");
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
	private void printSupers() {
		if (currentClass.getSupers().contains("_")) {
			System.out.println(currentClass.getClassName() + " -> " + currentClass.getSupers() + " [arrowhead=\"onormal\"];");
		}
	}
}
