package TestMisfitsPackage;
 

import static org.junit.Assert.assertEquals;

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
	public void testMain() throws Exception {
		
	}
	
	@Test
	public void testMakeUML(){
		
	}

	@Test
	public void testStartDiagram(){
		DesignParser.startDiagram("This is a Cool Test");
		assertEquals("digraph This is a Cool Test{\nrankdir=BT\n", outContent.toString());
	}
	
	@Test
	public void testEndDiagram(){
		
	}
}
