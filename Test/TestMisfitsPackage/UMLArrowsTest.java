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

import MisfitsPackage.UMLArrows;

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
		assertEquals(arrows.getFields(), fieldsv1);
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
		assertEquals(arrows.getUses(), usesv1);
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
		assertEquals(arrows.getInterfaces(), interfacesv1);
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
		assertEquals(arrows.getSupers(), supersv1);
		String supersv2 = "Hello_World";
		// Checking for different instance using !=
		assertTrue(!arrows.getSupers().equals(supersv2));
	}

	@Test
	public void testgetWhiteList() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = UMLArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>();
		whitelist.set(arrows, whitelistv1);
		// Checking for same instance using ==
		assertTrue(arrows.getWhitelist() == whitelistv1);
		ArrayList<String> whitelistv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(arrows.getWhitelist() != whitelistv2);
	}

	@Test
	public void testgetWhitelistValues() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field whitelist = UMLArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		whitelist.set(arrows, whitelistv1);
		// Checking for same instance using ==
		assertEquals(arrows.getWhitelist(), whitelistv1);
		ArrayList<String> whitelistv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(!arrows.getWhitelist().equals(whitelistv2));
	}

	@Test
	public void testresetUMLArrows() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		arrows.resetUMLArrows();
		Field whitelist = UMLArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		Field supers = UMLArrows.class.getDeclaredField("supers");
		supers.setAccessible(true);
		Field interfaces = UMLArrows.class.getDeclaredField("interfaces");
		interfaces.setAccessible(true);
		Field uses = UMLArrows.class.getDeclaredField("uses");
		uses.setAccessible(true);
		Field fields = UMLArrows.class.getDeclaredField("fields");
		fields.setAccessible(true);
		assertEquals(whitelist.get(arrows), new ArrayList<String>());
		assertEquals(supers.get(arrows), "");
		assertEquals(interfaces.get(arrows), new ArrayList<String>());
		assertEquals(uses.get(arrows), new ArrayList<String>());
		assertEquals(fields.get(arrows), new ArrayList<String>());
	}

	@Test
	public void testresetUMLArrowsValues() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		// Get the fields

		// //Whitelist is not reset in Reset Arrows, since it is consistent
		// //between all class in UML
		// Field whitelist = UMLArrows.class.getDeclaredField("whitelist");
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
		assertEquals(supers.get(arrows), "");
		assertEquals(interfaces.get(arrows), new ArrayList<String>());
		assertEquals(uses.get(arrows), new ArrayList<String>());
		assertEquals(fields.get(arrows), new ArrayList<String>());
	}
}
