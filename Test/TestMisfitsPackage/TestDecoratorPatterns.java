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

import Detectors.DecoratorDetector;
import Detectors.PatternDetector;
import MisfitsPackage.DesignParser;
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
	public void testCheckClassAdapterForDecorator() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		DecoratorDetector detect = new DecoratorDetector("blue", "green");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		String[] testString = new String[2];
		testString[0]="jdk.internal.org.objectweb.asm.ClassVisitor";
		testString[1]="jdk.internal.org.objectweb.asm.util.CheckClassAdapter";
		DesignParser.makeUML(testString);
		
		assertEquals(true, detect.isDetected());
	}
	
	@Test
	public void testInputStreamForDecorator() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		DecoratorDetector detect = new DecoratorDetector("blue", "green");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		String[] testString = new String[3];
		testString[0]="java.io.InputStreamReader";
		testString[1]="sun.nio.cs.StreamDecoder";
		testString[2]="java.io.InputStream";
		DesignParser.makeUML(testString);
		
		
		assertEquals(false, detect.isDetected());
	}
	
}