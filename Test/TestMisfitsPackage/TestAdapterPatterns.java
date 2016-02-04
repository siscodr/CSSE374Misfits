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
import UMLClasses.PatternDetector;
import UMLClasses.SingletonDetector;
import UMLClasses.UMLArrows;

public class TestAdapterPatterns {

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
	public void testLab2SolnForAdapter() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
//		UMLArrows arrows = UMLArrows.getInstance();
//		SingletonDetector detect = new SingletonDetector("red", "purple");
//		Field detector = UMLArrows.class.getDeclaredField("detectors");
//		detector.setAccessible(true);
//		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
//		pattern.add(detect);
//		detector.set(arrows, pattern);
//		
//		String[] testString = new String[1];
//		testString[0]="java.lang.Runtime";
//		DesignParser.makeUML(testString);
//		
		//assertEquals(true, detect.isDetected());
	}

}
