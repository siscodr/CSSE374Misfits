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
import UMLClasses.DecoratorDetector;
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
	public void testInputStreamReaderForAdapter() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		AdapterDetector detect = new AdapterDetector("red", "purple");
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
		
		assertEquals(true, detect.isDetected());
	}
	
	@Test
	public void testOutputStreamReaderForAdapter() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		AdapterDetector detect = new AdapterDetector("blue", "green");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		String[] testString = new String[4];
		testString[0]="java.io.OutputStream";
		testString[1]="sun.nio.cs.StreamEncoder";
		testString[2]="java.io.Writer";
		testString[3]="java.io.OutputStreamWriter";
		DesignParser.makeUML(testString);
		
		assertEquals(true, detect.isDetected());
	}
	
	@Test
	public void testCheckClassAdapterForAdapter() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		AdapterDetector detect = new AdapterDetector("blue", "green");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		String[] testString = new String[2];
		testString[0]="jdk.internal.org.objectweb.asm.ClassVisitor";
		testString[1]="jdk.internal.org.objectweb.asm.util.CheckClassAdapter";
		DesignParser.makeUML(testString);
		
		assertEquals(false, detect.isDetected());
	}
	
}
