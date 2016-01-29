package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import UMLClasses.UMLPrinter;

public class UMLPrinterTest {

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
	public void testStartDiagramNoSpaces() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Method startDiagram = UMLPrinter.class.getDeclaredMethod("startDiagram", String.class);
		startDiagram.setAccessible(true);
		String param = "BestDiagram";
		startDiagram.invoke(new UMLPrinter(), param);
		assertEquals("digraph BestDiagram{\nrankdir=BT\n", outContent.toString());
	}

	@Test
	public void testStartDiagramSpaces() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method startDiagram = UMLPrinter.class.getDeclaredMethod("startDiagram", String.class);
		startDiagram.setAccessible(true);
		startDiagram.invoke(new UMLPrinter(), "This is a Cool Test");
		assertEquals("digraph This is a Cool Test{\nrankdir=BT\n", outContent.toString());
	}

	@Test
	public void testStartDiagramSpecialCharacters() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Method startDiagram = UMLPrinter.class.getDeclaredMethod("startDiagram", String.class);
		startDiagram.setAccessible(true);
		startDiagram.invoke(new UMLPrinter(), "OMG!<>*$@^@($@+_21");
		assertEquals("digraph OMG!<>*$@^@($@+_21{\nrankdir=BT\n", outContent.toString());
	}

	@Test
	public void testStartDiagramNoName() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Method startDiagram = UMLPrinter.class.getDeclaredMethod("startDiagram", String.class);
		startDiagram.setAccessible(true);
		startDiagram.invoke(new UMLPrinter(), "");
		assertEquals("digraph {\nrankdir=BT\n", outContent.toString());
	}

	@Test
	public void testStartDiagramLargeName() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// Fun fact, this is the Longest word coined by a major author mentioned
		// in Aristophanes' comedy Assemblywomen (Originally written in Greek).
		Method startDiagram = UMLPrinter.class.getDeclaredMethod("startDiagram", String.class);
		startDiagram.setAccessible(true);
		startDiagram.invoke(new UMLPrinter(),
				"Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon");
		assertEquals(
				"digraph Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon{\nrankdir=BT\n",
				outContent.toString());
	}

	@Test
	public void testEndDiagram() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method endDiagram = UMLPrinter.class.getDeclaredMethod("endDiagram");
		endDiagram.setAccessible(true);
		endDiagram.invoke(new UMLPrinter());
		assertEquals("}\n", outContent.toString());
	}

	@Test
	public void testEndDiagramSpace() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Method endDiagram = UMLPrinter.class.getDeclaredMethod("endDiagram");
		endDiagram.setAccessible(true);
		endDiagram.invoke(new UMLPrinter());
		assertTrue(!"} \n".equals(outContent.toString()));
	}
}
