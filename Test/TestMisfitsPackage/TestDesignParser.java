package TestMisfitsPackage;
 

import org.junit.Assert;
import org.junit.Test;

import MisfitsPackage.DesignParser;

public class TestDesignParser {

	@Test
	public void testMain() {
		assert(false);//TODO
	}
	@Test
	public void testStripFunction() {

		String testString;
		testString="java.dotted.dots";
		
		Assert.assertEquals("java_dotted_dots",DesignParser.stripFunction(testString));
		
		testString="java/slashity/slash";
		Assert.assertEquals("java_slashity_slash",DesignParser.stripFunction(testString));
		
		testString="[java[brackets]inBrackets]";
		Assert.assertEquals("javabracketsinBrackets",DesignParser.stripFunction(testString));
		
		testString="[java.dotAndSlashes/inBrackets]";
		Assert.assertEquals("java_dotAndSlashes_inBrackets",DesignParser.stripFunction(testString));
	}
	
	@Test
	public void testGetFirstMethod() {
		DesignParser.setFirstMethod(false);
		Assert.assertEquals(false, DesignParser.getFirstMethod());
		
		DesignParser.setFirstMethod(true);
		Assert.assertEquals(true, DesignParser.getFirstMethod());
		}
	
	@Test
	public void testSetFirstMethod() {
		DesignParser.setFirstMethod(false);
		Assert.assertEquals(false, DesignParser.getFirstMethod());
		
		DesignParser.setFirstMethod(true);
		Assert.assertEquals(true, DesignParser.getFirstMethod());
	}

}
