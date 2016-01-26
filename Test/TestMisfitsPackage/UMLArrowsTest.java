package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MisfitsPackage.WorkerForArrows;
import UMLClasses.PatternDetector;
import UMLClasses.SingletonDetector;
import UMLClasses.UMLArrows;
import jdk.internal.org.objectweb.asm.Opcodes;

public class UMLArrowsTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void testgetInstance() {
		assertEquals("UMLArrows",
				(UMLArrows.getInstance().getClass().getSimpleName()));
	}

	@Test
	public void testgetSameInstance() {
		UMLArrows arrows = UMLArrows.getInstance();
		// == used to test if it is the same Instance.
		assertTrue(arrows == UMLArrows.getInstance());
	}

	@Test
	public void testgetFields() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		ArrayList<String> fieldsv1 = new ArrayList<String>();
		fields.set(arrows, fieldsv1);
		// Checking for same instance using ==
		assertTrue(arrows.getFields() == fieldsv1);
		ArrayList<String> fieldsv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(arrows.getFields() != fieldsv2);
	}

	@Test
	public void testgetFieldsValues() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		ArrayList<String> fieldsv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		fields.set(arrows, fieldsv1);
		// Checking for same instance using ==
		assertEquals(fieldsv1, arrows.getFields());
		ArrayList<String> fieldsv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(!arrows.getFields().equals(fieldsv2));
	}

	@Test
	public void testgetUses() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		ArrayList<String> usesv1 = new ArrayList<String>();
		uses.set(arrows, usesv1);
		// Checking for same instance using ==
		assertTrue(arrows.getUses() == usesv1);
		ArrayList<String> usesv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(arrows.getUses() != usesv2);
	}

	@Test
	public void testgetUsesValues() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		ArrayList<String> usesv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		uses.set(arrows, usesv1);
		// Checking for same instance using ==
		assertEquals(usesv1, arrows.getUses());
		ArrayList<String> usesv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(!arrows.getUses().equals(usesv2));
	}

	@Test
	public void testgetInterfaces() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		ArrayList<String> interfacesv1 = new ArrayList<String>();
		interfaces.set(arrows, interfacesv1);
		// Checking for same instance using ==
		assertTrue(arrows.getInterfaces() == interfacesv1);
		ArrayList<String> interfacesv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(arrows.getInterfaces() != interfacesv2);
	}

	@Test
	public void testgetInterfacesValues() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		ArrayList<String> interfacesv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		interfaces.set(arrows, interfacesv1);
		// Checking for same instance using ==
		assertEquals(interfacesv1, arrows.getInterfaces());
		ArrayList<String> interfacesv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(!arrows.getInterfaces().equals(interfacesv2));
	}

	@Test
	public void testgetSupers() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		String supersv1 = "";
		supers.set(arrows, supersv1);
		// Checking for same instance using ==
		assertTrue(arrows.getSupers() == supersv1);
	}

	@Test
	public void testgetSupersValues() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		String supersv1 = "Understood";
		supers.set(arrows, supersv1);
		// Checking for same instance using ==
		assertEquals(supersv1, arrows.getSupers());
		String supersv2 = "Hello_World";
		// Checking for different instance using !=
		assertTrue(!arrows.getSupers().equals(supersv2));
	}

	@Test
	public void testresetUMLArrows() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		arrows.resetUMLArrows();
		// Field whitelist =
		// WorkerForArrows.class.getDeclaredField("whitelist");
		// whitelist.setAccessible(true);
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		// assertEquals(new ArrayList<String>(), whitelist.get(arrows));
		assertEquals("", supers.get(arrows));
		assertEquals(new ArrayList<String>(), interfaces.get(arrows));
		assertEquals(new ArrayList<String>(), uses.get(arrows));
		assertEquals(new ArrayList<String>(), fields.get(arrows));
	}

	@Test
	public void testresetUMLArrowsValues() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		// Get the fields

		// //Whitelist is not reset in Reset Arrows, since it is consistent
		// //between all class in UML
		// Field whitelist =
		// WorkerForArrows.class.getDeclaredField("whitelist");
		// whitelist.setAccessible(true);
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		// Set the values
		// whitelist.set(arrows,new ArrayList<String>(Arrays.asList(
		// "String", "cóol", "Understood")));
		supers.set(arrows, "This is a cool String");
		interfaces.set(
				arrows,
				new ArrayList<String>(Arrays.asList("Looks interesting",
						"cóol", "Understood")));
		uses.set(
				arrows,
				new ArrayList<String>(Arrays.asList("String", "cóol",
						"Testing123")));
		fields.set(
				arrows,
				new ArrayList<String>(Arrays.asList("Helllllo", "cóol",
						"Mic Check?")));
		// Reset
		arrows.resetUMLArrows();
		// Test
		// assertEquals(whitelist.get(arrows), new ArrayList<String>());
		assertEquals("", supers.get(arrows));
		assertEquals(new ArrayList<String>(), interfaces.get(arrows));
		assertEquals(new ArrayList<String>(), uses.get(arrows));
		assertEquals(new ArrayList<String>(), fields.get(arrows));
	}

	@Test
	public void testaddToFieldBufferSpace() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = " ";
		UMLArrows arrows = UMLArrows.getInstance();
		Field fieldBuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) fieldBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToFieldBuffer(toAdd);
		assertEquals(newBuffer, fieldBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToFieldBufferString() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = "I am a string";
		UMLArrows arrows = UMLArrows.getInstance();
		Field fieldBuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) fieldBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToFieldBuffer(toAdd);
		assertEquals(newBuffer, fieldBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToFieldBufferSymbols() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = "(#)^%@#%^@#$)@#$*@#&$)@!_)*";
		UMLArrows arrows = UMLArrows.getInstance();
		Field fieldBuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) fieldBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToFieldBuffer(toAdd);
		assertEquals(newBuffer, fieldBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToFieldBufferNothing() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = "";
		UMLArrows arrows = UMLArrows.getInstance();
		Field fieldBuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) fieldBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToFieldBuffer(toAdd);
		assertEquals(newBuffer, fieldBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToMethodBufferSpace() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = " ";
		UMLArrows arrows = UMLArrows.getInstance();
		Field methodBuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) methodBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToMethodBuffer(toAdd);
		assertEquals(newBuffer, methodBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToMethodBufferString() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = "I am a string";
		UMLArrows arrows = UMLArrows.getInstance();
		Field methodBuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) methodBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToMethodBuffer(toAdd);
		assertEquals(newBuffer, methodBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToMethodBufferSymbols() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = "(#)^%@#%^@#$)@#$*@#&$)@!_)*";
		UMLArrows arrows = UMLArrows.getInstance();
		Field methodBuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) methodBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToMethodBuffer(toAdd);
		assertEquals(newBuffer, methodBuffer.get(arrows).toString());
	}

	@Test
	public void testaddToMethodBufferNothing() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		String toAdd = "";
		UMLArrows arrows = UMLArrows.getInstance();
		Field methodBuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodBuffer.setAccessible(true);
		StringBuffer currentBuffer = (StringBuffer) methodBuffer.get(arrows);
		String newBuffer = (currentBuffer.toString() + toAdd);
		arrows.addToMethodBuffer(toAdd);
		assertEquals(newBuffer, methodBuffer.get(arrows).toString());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testaddUseEmptyString() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList(""));
		whitelist.set(arrows, whitelistv1);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		ArrayList<String> usesarray = (ArrayList<String>) uses.get(arrows);
		String toAdd = "";
		usesarray.add(toAdd);
		arrows.addUse(toAdd);
		assertEquals(usesarray, uses.get(arrows));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testaddUseNumerics() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("12093174120"));
		whitelist.set(arrows, whitelistv1);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		ArrayList<String> usesarray = (ArrayList<String>) uses.get(arrows);
		String toAdd = "12093174120";
		usesarray.add(toAdd);
		arrows.addUse(toAdd);
		assertEquals(usesarray, uses.get(arrows));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testaddUseString() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("HeyHeyHeyThisisaCoolTest"));
		whitelist.set(arrows, whitelistv1);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		ArrayList<String> usesarray = (ArrayList<String>) uses.get(arrows);
		// We must avoid having the stripper from touching this string
		String toAdd = "HeyHeyHeyThisisaCoolTest";
		usesarray.add(toAdd);
		arrows.addUse(toAdd);
		assertEquals(usesarray, uses.get(arrows));
	}

	@Test
	public void testaddUses() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList(
				"int", "double", "byte"));
		whitelist.set(arrows, whitelistv1);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		uses.set(arrows, new ArrayList<String>());
		ArrayList<String> usesarray = new ArrayList<String>(Arrays.asList(
				"int", "double", "byte"));
		String desc = "(IDB)Ljava/lang/Object";
		arrows.addUses(desc);
		assertEquals(usesarray, uses.get(arrows));
	}

	@Test
	public void testaddFieldObject() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("java_lang_Object"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		// We must avoid having the stripper from touching this string
		String toAdd = "java_lang_Object";
		String type = "java/lang/Object";
		fieldsarray.add(toAdd);
		arrows.addField(type);
		assertEquals(fieldsarray, fields.get(arrows));
	}

	@Test
	public void testaddFieldUMLArrows() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage_UMLArrowsTest"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		// We must avoid having the stripper from touching this string
		String toAdd = "TestMisfitsPackage_UMLArrowsTest";
		String type = "TestMisfitsPackage/UMLArrowsTest";
		fieldsarray.add(toAdd);
		arrows.addField(type);
		assertEquals(fieldsarray, fields.get(arrows));
	}

	@Test
	public void testaddFieldDescObject() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("java_lang_Object"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		// We must avoid having the stripper from touching this string
		String toAdd = "java_lang_Object";
		String desc = "Ljava/lang/Object;";
		int access = Opcodes.ACC_PUBLIC;
		fieldsarray.add(toAdd);
		arrows.addFieldDesc(desc, access);
		assertEquals(fieldsarray, fields.get(arrows));
	}

	@Test
	public void testaddFieldDescUMLArrowsTest() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage_UMLArrowsTest"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		// We must avoid having the stripper from touching this string
		String toAdd = "TestMisfitsPackage_UMLArrowsTest";
		String desc = "LTestMisfitsPackage/UMLArrowsTest;";
		fieldsarray.add(toAdd);
		int access = Opcodes.ACC_PUBLIC;
		arrows.addFieldDesc(desc, access);
		assertEquals(fieldsarray, fields.get(arrows));
	}

	@Test
	public void testaddFieldDescSingletonTrue() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage_UMLArrowsTest"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		
		SingletonDetector detect = new SingletonDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		Field className = UMLArrows.class.getDeclaredField("className");
		className.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String toAdd = "TestMisfitsPackage_UMLArrowsTest";
		String desc = "LTestMisfitsPackage/UMLArrowsTest;";
		className.set(arrows, toAdd);
		fieldsarray.add(toAdd);
		int access = Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC; 
		// An Opcode of private and static should make isSingle true
		arrows.addFieldDesc(desc, access);
		assertEquals(true, detect.isDetected());
	}

	@Test
	public void testaddFieldDescSingletonPublicOnly()
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage_UMLArrowsTest"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		
		SingletonDetector detect = new SingletonDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		Field className = UMLArrows.class.getDeclaredField("className");
		className.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String toAdd = "TestMisfitsPackage_UMLArrowsTest";
		String desc = "LTestMisfitsPackage/UMLArrowsTest;";
		className.set(arrows, toAdd);
		fieldsarray.add(toAdd);
		int access = Opcodes.ACC_PUBLIC; // An Opcode of public should leave
										// isSingle as false
		arrows.addFieldDesc(desc, access);
		assertEquals(false, detect.isDetected());
	}

	@Test
	public void testaddFieldDescSingletonPrivateOnly()
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage_UMLArrowsTest"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		
		SingletonDetector detect = new SingletonDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		Field className = UMLArrows.class.getDeclaredField("className");
		className.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String toAdd = "TestMisfitsPackage_UMLArrowsTest";
		String desc = "LTestMisfitsPackage/UMLArrowsTest;";
		className.set(arrows, toAdd);
		fieldsarray.add(toAdd);
		int access = Opcodes.ACC_PRIVATE; // An Opcode of only private should leave
										// isSingle as false
		arrows.addFieldDesc(desc, access);
		assertEquals(false, detect.isDetected());
	}

	@Test
	public void testaddFieldDescSingletonStaticOnly()
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage_UMLArrowsTest"));
		whitelist.set(arrows, whitelistv1);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);

		SingletonDetector detect = new SingletonDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		ArrayList<String> fieldsarray = new ArrayList<String>();
		fields.set(arrows, new ArrayList<String>());
		Field className = UMLArrows.class.getDeclaredField("className");
		className.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String toAdd = "TestMisfitsPackage_UMLArrowsTest";
		String desc = "LTestMisfitsPackage/UMLArrowsTest;";
		className.set(arrows, toAdd);
		fieldsarray.add(toAdd);
		int access = Opcodes.ACC_STATIC; // An Opcode of only static should leave
										// isSingle as false
		arrows.addFieldDesc(desc, access);
		assertEquals(false, detect.isDetected());
	}

	@Test
	public void testsetSuperString() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("HeyHeyHeyThisisaCoolTest"));
		whitelist.set(arrows, whitelistv1);
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String theSuper = "HeyHeyHeyThisisaCoolTest";
		arrows.setSuper(theSuper);
		assertEquals(theSuper, supers.get(arrows));
	}

	@Test
	public void testsetSuperNumerics() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("56789987655678"));
		whitelist.set(arrows, whitelistv1);
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String theSuper = "56789987655678";
		arrows.setSuper(theSuper);
		assertEquals(theSuper, supers.get(arrows));
	}

	@Test
	public void testsetSuperSpecial() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("#$%^&*(<>?:\"{}';'"));
		whitelist.set(arrows, whitelistv1);
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		// We must avoid having the stripper from touching this string
		String theSuper = "#$%^&*(<>?:\"{}';'";
		arrows.setSuper(theSuper);
		assertEquals(theSuper, supers.get(arrows));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testaddInterfaceEmptyString() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList(""));
		whitelist.set(arrows, whitelistv1);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		ArrayList<String> interfacesarray = (ArrayList<String>) interfaces
				.get(arrows);
		String toAdd = "";
		interfacesarray.add(toAdd);
		arrows.addInterface(toAdd);
		assertEquals(interfacesarray, interfaces.get(arrows));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testaddInterfaceString() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("MegaInterface"));
		whitelist.set(arrows, whitelistv1);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		ArrayList<String> interfacesarray = (ArrayList<String>) interfaces
				.get(arrows);
		String toAdd = "MegaInterface";
		interfacesarray.add(toAdd);
		arrows.addInterface(toAdd);
		assertEquals(interfacesarray, interfaces.get(arrows));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testaddInterfaceNumerics() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("0937240432"));
		whitelist.set(arrows, whitelistv1);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		ArrayList<String> interfacesarray = (ArrayList<String>) interfaces
				.get(arrows);
		String toAdd = "0937240432";
		interfacesarray.add(toAdd);
		arrows.addInterface(toAdd);
		assertEquals(interfacesarray, interfaces.get(arrows));
	}

	@Test
	public void testprintClassNoBuffers() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		Field fieldbuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldbuffer.setAccessible(true);
		Field methodbuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodbuffer.setAccessible(true);
		Field className = UMLArrows.class.getDeclaredField("className");
		className.setAccessible(true);
		className.set(arrows, "test");
		supers.set(arrows, "");
		interfaces.set(arrows, new ArrayList<String>());
		uses.set(arrows, new ArrayList<String>());
		fields.set(arrows, new ArrayList<String>());
		fieldbuffer.set(arrows, new StringBuffer());
		methodbuffer.set(arrows, new StringBuffer());
		arrows.printClass();
		assertEquals(
				"   test [\n     shape=\"record\"      label = \"{test||\n}\"\n];\n",
				outContent.toString());
	}

	@Test
	public void testprintClass() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		Field fieldbuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldbuffer.setAccessible(true);
		Field methodbuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodbuffer.setAccessible(true);
		Field className = UMLArrows.class.getDeclaredField("className");
		className.setAccessible(true);
		className.set(arrows, "test");
		supers.set(arrows, "");
		interfaces.set(arrows, new ArrayList<String>());
		uses.set(arrows, new ArrayList<String>());
		fields.set(arrows, new ArrayList<String>());
		StringBuffer fTemp = new StringBuffer();
		fTemp.append("I was thinking about this buffer and it was a good idea.");
		StringBuffer mTemp = new StringBuffer();
		mTemp.append("This is a powerful MethodBuffer.");
		fieldbuffer.set(arrows, fTemp);
		methodbuffer.set(arrows, mTemp);
		arrows.printClass();
		assertEquals(
				"   test [\n     shape=\"record\"      label = \"{test|I was thinking about this buffer and it was a good idea.|This is a powerful MethodBuffer.\n}\"\n];\n",
				outContent.toString());
	}

	@Test
	public void testaddFieldToBuffer() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("java_lang_Object"));
		whitelist.set(arrows, whitelistv1);
		Field fieldbuffer = UMLArrows.class.getDeclaredField("fieldBuffer");
		fieldbuffer.setAccessible(true);
		fieldbuffer.set(arrows, new StringBuffer());
		arrows.addFieldToBuffer(Opcodes.ACC_PUBLIC, "test",
				"Ljava/lang/Object;");
		assertEquals("+ test : java_lang_Object\\l", fieldbuffer.get(arrows)
				.toString());
	}

	@Test
	public void testaddMethodToBuffer() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(
				Arrays.asList("java_lang_Object"));
		whitelist.set(arrows, whitelistv1);
		Field methodbuffer = UMLArrows.class.getDeclaredField("methodBuffer");
		methodbuffer.setAccessible(true);
		methodbuffer.set(arrows, new StringBuffer());
		arrows.addMethodToBuffer(Opcodes.ACC_PUBLIC, "test",
				"()Ljava/lang/Object;");
		assertEquals("+test([]) : java_lang_Object\\l ",
				methodbuffer.get(arrows).toString());
	}

}
