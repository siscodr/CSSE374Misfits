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

import ClassStorage.ClassContainer;
import Detectors.AdapterDetector;
import Detectors.CompositeDetector;
import Detectors.PatternDetector;
import MisfitsPackage.DesignParser;
import UMLClasses.UMLArrows;

public class TestCompositePatterns {

//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//
//	@Before
//	public void setUpStreams() {
//		System.setOut(new PrintStream(outContent));
//		System.setErr(new PrintStream(errContent));
//	}
//
//	@After
//	public void cleanUpStreams() {
//		System.setOut(null);
//		System.setErr(null);
//	}
//	
	@Test
	public void testContainerForComposite() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		CompositeDetector detect = new CompositeDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		String[] testString = new String[3];
		testString[0]="java.awt.Component";
		testString[1]="javax.swing.JComponent";
		testString[2]="java.awt.Container";
		DesignParser.makeUML(testString);
		
		assertEquals(true, detect.isDetected());
	}
	
	@Test
	public void testJComponentForCompositeLeaf() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		UMLArrows arrows = UMLArrows.getInstance();
		CompositeDetector detect = new CompositeDetector("red", "purple");
		Field detector = UMLArrows.class.getDeclaredField("detectors");
		detector.setAccessible(true);
		Field classesField = UMLArrows.class.getDeclaredField("classes");
		classesField.setAccessible(true);
		
		ArrayList<PatternDetector> pattern =new ArrayList<PatternDetector>();
		pattern.add(detect);
		detector.set(arrows, pattern);
		
		//Field classesField = UMLArrows.class.getDeclaredField("classes");
		//classesField.setAccessible(true);
		
		String[] testString = new String[3];
		testString[0]="java.awt.Component";
		testString[1]="java.awt.Container";
		testString[2]="javax.swing.JComponent";
		DesignParser.makeUML(testString);
		
		ArrayList<ClassContainer> classes = (ArrayList<ClassContainer>) classesField.get(arrows);
		for(ClassContainer cls : classes){
			if(cls.getClassName().equals("javax_swing_JComponent")){
				assertTrue(cls.getLabel().contains("Leaf"));
			}
		}
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
