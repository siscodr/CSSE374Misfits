package TestMisfitsPackage;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MisfitsPackage.DesignParser;

public class TestDesignParser {

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
	public void testMakeUML() {

	}

	@Test
	public void testStartDiagramNoSpaces() {
		DesignParser.startDiagram("BestDiagram");
		assertEquals("digraph BestDiagram{\nrankdir=BT\n",
				outContent.toString());
	}

	@Test
	public void testStartDiagramSpaces() {
		DesignParser.startDiagram("This is a Cool Test");
		assertEquals("digraph This is a Cool Test{\nrankdir=BT\n",
				outContent.toString());
	}

	@Test
	public void testStartDiagramSpecialCharacters() {
		DesignParser.startDiagram("OMG!<>*$@^@($@+_21");
		assertEquals("digraph OMG!<>*$@^@($@+_21{\nrankdir=BT\n",
				outContent.toString());
	}

	@Test
	public void testStartDiagramNoName() {
		DesignParser.startDiagram("");
		assertEquals("digraph {\nrankdir=BT\n", outContent.toString());
	}

	@Test
	public void testStartDiagramLargeName() {
		// Fun fact, this is the Longest word coined by a major author mentioned
		// in Aristophanes' comedy Assemblywomen (Originally written in Greek).
		DesignParser
				.startDiagram("Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon");
		assertEquals(
				"digraph Lopadotemachoselachogaleokranioleipsanodrimhypotrimmatosilphioparaomelitokatakechymenokichlepikossyphophattoperisteralektryonoptekephalliokigklopeleiolagoiosiraiobaphetraganopterygon{\nrankdir=BT\n",
				outContent.toString());
	}

	@Test
	public void testEndDiagram() {
		DesignParser.endDiagram();
		assertEquals("}\n", outContent.toString());
	}
	
	@Test
	public void testEndDiagramSpace() {
		DesignParser.endDiagram();
			assertTrue(!"} \n".equals(outContent.toString()));
	}
}
