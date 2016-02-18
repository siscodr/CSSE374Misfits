package TestMisfitsPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import MisfitsPackage.ClassFinder;

public class ClassFinderTest {

	@Test
	public void test() {
		String[] classes = ClassFinder
				.getClasses("C:/Users/siscodr/Documents/GitHub/CSSE374Misfits/bin");
		assertTrue(classes != null);
	}

}
