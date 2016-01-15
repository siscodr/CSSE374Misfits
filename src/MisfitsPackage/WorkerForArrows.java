package MisfitsPackage;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

/**
 * The purpose of this class is to do a lot of the processing work, and to
 * eliminate as much duplicated code.
 * 
 * @author TheMisfits
 */
public class WorkerForArrows {
	private static ArrayList<String> whitelist = new ArrayList<String>();

	/**
	 * Getter for the WhiteList for the current set of classes.
	 * 
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getWhitelist() {
		return whitelist;
	}

	/**
	 * This function adds the given strings to the whiteList for the classes
	 * that the UML should draw
	 * 
	 * @param classes
	 *            the classes to add to the whiteList.
	 * @return No return value.
	 */
	public static void addWhitelist(String[] classes) {
		for (int i = 0; i < classes.length; i++) {
			whitelist.add(stripFunction(classes[i]));
		}
	}

	/**
	 * Takes the given string and checks against the whiteList.
	 * 
	 * @param cleanType
	 *            String to be checked against the whiteList
	 * @return boolean If the given String is whiteListed
	 */
	public static boolean unwantedTypes(String cleanType) {
		for (String whitelisted : whitelist) {
			if (cleanType.equals(whitelisted)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Takes a method descriptor and returns a list of the parameters
	 * 
	 * @param desc
	 *            A method descriptor.
	 * @return List<String> A list of the parameter Types from a method
	 *         descriptors
	 */
	public static List<String> getTypesFromDesc(String desc) {
		Type[] argTypes = Type.getArgumentTypes(desc);
		List<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
		}
		return stypes;
	}

	/**
	 * Takes an Opcode and turns it into a symbol to be used in the UML to
	 * determine access of the method/field
	 * 
	 * @param access
	 *            An Opcode representation of a symbol
	 * @return String The string is a symbol to represent the access of the
	 *         method/Field
	 */
	public static String makeSymbol(int access) {
		String symbol = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			symbol = "-";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			symbol = "#";
		}
		return symbol;
	}

	/**
	 * Takes the given String and puts it in the consistent format.
	 * 
	 * @param toStrip
	 *            A String that needs to be in a consistent format.
	 * @return String A String without ".""/""[""]"
	 */
	public static String stripFunction(String toStrip) {
		toStrip = toStrip.replace(".", "_");
		toStrip = toStrip.replace("/", "_");
		toStrip = toStrip.replace("[", "");
		toStrip = toStrip.replace("]", "");
		return toStrip;
	}
}
