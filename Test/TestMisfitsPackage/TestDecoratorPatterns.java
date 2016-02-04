package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MisfitsPackage.DesignParser;
import UMLClasses.DecoratorDetector;
import UMLClasses.PatternDetector;
import UMLClasses.UMLArrows;


public class TestDecoratorPatterns {
	
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
	public void testMouseAdapterForDecorator() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		DecoratorDetector detect = new DecoratorDetector("blue", "green");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		String[] testString = new String[1];
		testString[0]="java.awt.event.MouseAdapter";
		DesignParser.makeUML(testString);
		
		assertEquals(false, detect.isDetected());
	}
	
	@Test
	public void testForDecorator() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		DecoratorDetector detect = new DecoratorDetector("blue", "green");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		String[] testString = new String[1];
		testString[0]="java.awt.event.MouseAdapter";
		DesignParser.makeUML(testString);
		
		
		assertEquals(false, detect.isDetected());
	}
	
}