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
		Field fields = UMLArrows.class.getDeclaredField("uses");
		fields.setAccessible(true);
		ArrayList<String> usesv1 = new ArrayList<String>();
		fields.set(arrows, usesv1);
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
		Field fields = UMLArrows.class.getDeclaredField("uses");
		fields.setAccessible(true);
		ArrayList<String> usesv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		fields.set(arrows, usesv1);
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
		Field fields = UMLArrows.class.getDeclaredField("interfaces");
		fields.setAccessible(true);
		ArrayList<String> interfacesv1 = new ArrayList<String>();
		fields.set(arrows, interfacesv1);
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
		Field fields = UMLArrows.class.getDeclaredField("interfaces");
		fields.setAccessible(true);
		ArrayList<String> interfacesv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		fields.set(arrows, interfacesv1);
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
		Field fields = UMLArrows.class.getDeclaredField("supers");
		fields.setAccessible(true);
		String supersv1 = "";
		fields.set(arrows, supersv1);
		// Checking for same instance using ==
		assertTrue(arrows.getSupers() == supersv1);
	}

	@Test
	public void testgetSupersValues() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		UMLArrows arrows = UMLArrows.getInstance();
		Field fields = UMLArrows.class.getDeclaredField("supers");
		fields.setAccessible(true);
		String supersv1 = "Understood";
		fields.set(arrows, supersv1);
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
		Field fields = UMLArrows.class.getDeclaredField("whitelist");
		fields.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>();
		fields.set(arrows, whitelistv1);
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
		Field fields = UMLArrows.class.getDeclaredField("whitelist");
		fields.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList(
				"String", "cóol", "Understood"));
		fields.set(arrows, whitelistv1);
		// Checking for same instance using ==
		assertEquals(arrows.getWhitelist(), whitelistv1);
		ArrayList<String> whitelistv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(!arrows.getWhitelist().equals(whitelistv2));
	}
}
