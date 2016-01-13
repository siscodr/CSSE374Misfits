package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MisfitsPackage.UMLArrows;

public class UMLArrowsTest {

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
	public void testgetInstance(){
		assertEquals("UMLArrows",(UMLArrows.getInstance().getClass().getSimpleName()));
	}

	@Test
	public void testgetSameInstance(){
		UMLArrows arrows = UMLArrows.getInstance();
		//== used to test if it is the same Instance.
		assertTrue(arrows == UMLArrows.getInstance());
	}
}
