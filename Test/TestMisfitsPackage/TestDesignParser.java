package TestMisfitsPackage;
 

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import MisfitsPackage.DesignParser;

public class TestDesignParser {

	@Test
	public void testMain() throws Exception {

		String expected = "digraph misfit_diagram{"			//For java/lang/Boolean
		+ System.lineSeparator() +
		"rankdir=BT;"
		+ System.lineSeparator() +
		"java_lang_Boolean -> java_io_Serializable [arrowhead=\"onormal\", style=\"dashed\"];"
		+ System.lineSeparator() +
		"java_lang_Boolean -> java_lang_Comparable [arrowhead=\"onormal\", style=\"dashed\"];"
		+ System.lineSeparator() +
		"java_lang_Boolean -> java_lang_Object [arrowhead=\"onormal\"];"
		+ System.lineSeparator() +
		"   java_lang_Boolean ["
		+ System.lineSeparator() +
		"     shape=\"record\""
		+ System.lineSeparator() +
		"     label = \"{java_lang_Boolean|+ TRUE : java_lang_Boolean\\l+ FALSE : java_lang_Boolean\\l+ TYPE : java_lang_Class\\l- value : boolean\\l- serialVersionUID : long\\l|+parseBoolean([java.lang.String]) : boolean\\l +booleanValue([]) : boolean\\l +valueOf([boolean]) : java.lang.Boolean\\l +valueOf([java.lang.String]) : java.lang.Boolean\\l +toString([boolean]) : java.lang.String\\l +toString([]) : java.lang.String\\l +hashCode([]) : int\\l +hashCode([boolean]) : int\\l +equals([java.lang.Object]) : boolean\\l +getBoolean([java.lang.String]) : boolean\\l +compareTo([java.lang.Boolean]) : int\\l +compare([boolean, boolean]) : int\\l +logicalAnd([boolean, boolean]) : boolean\\l +logicalOr([boolean, boolean]) : boolean\\l +logicalXor([boolean, boolean]) : boolean\\l +compareTo([java.lang.Object]) : int\\l }\""
		+ System.lineSeparator() +	
		"];"
		+ System.lineSeparator() +
		"java_lang_Boolean -> java_lang_Object [arrowhead=\"vee\", style=\"dashed\"];"
		+ System.lineSeparator() +
		"java_lang_Boolean -> java_lang_System [arrowhead=\"vee\", style=\"dashed\"];"
		+ System.lineSeparator() +
		"java_lang_Boolean -> java_lang_Boolean [arrowhead=\"diamond\"];"
		+ System.lineSeparator() +
		"java_lang_Class -> java_lang_Boolean [arrowhead=\"diamond\"];"
		+ System.lineSeparator() +
		"}"
		+ System.lineSeparator();
		
		// redirect stdout to save the result to a byte array
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		DesignParser.main(new String[]{"java/lang/Boolean"});
		
		String actual = new String(out.toByteArray());

		assertEquals(expected, actual);
		
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
