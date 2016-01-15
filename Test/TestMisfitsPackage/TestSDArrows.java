package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MisfitsPackage.Instruction;
import MisfitsPackage.SDArrows;

public class TestSDArrows {

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
		assertEquals("SDArrows", (SDArrows.getInstance().getClass().getSimpleName()));
	}

	@Test
	public void testgetSameInstance() {
		SDArrows arrows = SDArrows.getInstance();
		// == used to test if it is the same Instance.
		assertTrue(arrows == SDArrows.getInstance());
	}

	@Test
	public void testprintClassEmpty() {
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("");
		assertEquals(":", outContent.toString());
	}

	@Test
	public void testprintClassString() {
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("java/lang/Object");
		assertEquals(":java_lang_Object", outContent.toString());
	}

	@Test
	public void testprintClassNumeric() {
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("8675309");
		assertEquals(":8675309", outContent.toString());
	}

	@Test
	public void testprintClassLong() {
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass(
				"Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon");
		assertEquals(
				":Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon",
				outContent.toString());
	}

	@Test
	public void testaddItemsToHashMap()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		SDArrows arrows = SDArrows.getInstance();
		Field hashmap = SDArrows.class.getDeclaredField("instructionSets");
		hashmap.setAccessible(true);
		HashMap<String, ArrayList<Instruction>> fieldsv1 = new HashMap<String, ArrayList<Instruction>>();
		hashmap.set(arrows, fieldsv1);
		Field cclass = SDArrows.class.getDeclaredField("currentClass");
		cclass.setAccessible(true);
		cclass.set(arrows, "TheMisfits");
		HashMap<String, ArrayList<Instruction>> fieldsv2 = new HashMap<String, ArrayList<Instruction>>();
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		arrows.addItemsToHashMap("master", instructions);
		fieldsv2.put("TheMisfits.master", instructions);
		assertEquals(fieldsv2, hashmap.get(arrows));
	}
}
