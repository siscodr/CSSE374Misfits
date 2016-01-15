package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testprintClassEmpty(){
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("");
		assertEquals(":", outContent.toString());
	}
	@Test
	public void testprintClassString(){
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("java/lang/Object");
		assertEquals(":java_lang_Object", outContent.toString());
	}
	@Test
	public void testprintClassNumeric(){
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("8675309");
		assertEquals(":8675309", outContent.toString());
	}
	@Test
	public void testprintClassLong(){
		SDArrows arrows = SDArrows.getInstance();
		arrows.printClass("Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon");
		assertEquals(":Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon", outContent.toString());
	}
	public void testaddItemsToHashMap(){
		
	}
}
