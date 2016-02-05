package TestMisfitsPackage;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MisfitsPackage.DesignParser;
import UMLClasses.AdapterDetector;
import UMLClasses.CompositeDetector;
import UMLClasses.PatternDetector;
import UMLClasses.UMLArrows;

public class TestCompositePatterns {

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
	public void testForComposite() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		CompositeDetector detect = new CompositeDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		String[] testString = new String[2];
		testString[0]="java.awt.Component";
		testString[1]="java.awt.Container";
		DesignParser.makeUML(testString);
		
		assertEquals(true, detect.isDetected());
	}
	
	@Test
	public void testInputStreamReaderForComposite() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		CompositeDetector detect = new CompositeDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
	
		String[] testString = new String[4];
		testString[0]="sun.nio.cs.StreamDecoder";
		testString[1]="java.io.InputStream";
		testString[2]="java.io.Reader";
		testString[3]="java.io.InputStreamReader";
		DesignParser.makeUML(testString);
		
		assertEquals(false, detect.isDetected());
	}

}
