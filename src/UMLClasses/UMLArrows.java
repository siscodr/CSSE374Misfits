package UMLClasses;

import java.util.ArrayList;
import java.util.List;

import ClassStorage.ClassContainer;
import ClassStorage.FieldStorage;
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
	private ClassContainer currentClass;
	private ArrayList<ClassContainer> classes = new ArrayList<ClassContainer>();

	/**
	 * Constructs a UMLArrows
	 */
	private UMLArrows() {
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
		if (currentClass != null) {
			classes.add(currentClass);
		}
		currentClass = new ClassContainer(WorkerForArrows.stripFunction(className));
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
		currentClass.addUse(currentType);
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
		currentClass.addUse(desc);
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
		currentClass.addField(type, access);
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
		currentClass.addFieldDesc(desc, access);
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
		currentClass.addMethodDesc(name, desc, access);
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
		currentClass.setSuper(currentType);
	}
	
	public ArrayList<ClassContainer> getClasses(){
		return classes;
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
		currentClass.addInterface(currentType);
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
			currentClass.addToFieldBuffer(temp);
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

			currentClass.addToMethodBuffer(temp);
		}
	}

	public ArrayList<PatternDetector> getDetectors() {
		return detectors;
	}
	public void printClasses(String nameOfDiagram) {
		UMLPrinter.printClasses(nameOfDiagram, this.classes);
	}

	public void setIsInterface(boolean isInterface) {
		currentClass.setIsInterface(isInterface);	
	}

}
