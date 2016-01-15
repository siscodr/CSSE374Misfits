package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

import MisfitsPackage.UMLArrows;
import MisfitsPackage.WorkerForArrows;

public class TestWorkerForArrows {

	@Test
	public void testgetWhiteList() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>();
		whitelist.set(worker, whitelistv1);
		// Checking for same instance using ==
		assertTrue(WorkerForArrows.getWhitelist() == whitelistv1);
		ArrayList<String> whitelistv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(WorkerForArrows.getWhitelist() != whitelistv2);
	}

	@Test
	public void testgetWhitelistValues() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList("String", "cóol", "Understood"));
		whitelist.set(worker, whitelistv1);
		// Checking for same instance using ==
		assertEquals(whitelistv1, WorkerForArrows.getWhitelist());
		ArrayList<String> whitelistv2 = new ArrayList<String>();
		// Checking for different instance using !=
		assertTrue(!WorkerForArrows.getWhitelist().equals(whitelistv2));
	}

	@Test
	public void testaddWhiteListEmpty()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>();
		whitelist.set(worker, whitelistv1);
		String[] classesToAdd = { "" };
		WorkerForArrows.addWhitelist(classesToAdd);
		assertEquals(new ArrayList<String>(Arrays.asList("")), whitelist.get(worker));
	}

	@Test
	public void testaddWhiteListStrings()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>();
		whitelist.set(worker, whitelistv1);
		String[] classesToAdd = { "hello", "IReallyWantToBeWhiteListed", "foo" };
		WorkerForArrows.addWhitelist(classesToAdd);
		assertEquals(new ArrayList<String>(Arrays.asList("hello", "IReallyWantToBeWhiteListed", "foo")),
				whitelist.get(worker));
	}

	@Test
	public void testunwantedTypesInWhitelist() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList("String"));
		whitelist.set(worker, whitelistv1);
		Method unwantedType = WorkerForArrows.class.getDeclaredMethod("unwantedTypes", String.class);
		unwantedType.setAccessible(true);
		assertEquals(true, unwantedType.invoke(new WorkerForArrows(), "String"));
	}

	@Test
	public void testunwantedTypesSubstringInWhiteList() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList("String"));
		whitelist.set(worker, whitelistv1);
		Method unwantedType = WorkerForArrows.class.getDeclaredMethod("unwantedTypes", String.class);
		unwantedType.setAccessible(true);
		assertEquals(false, unwantedType.invoke(new WorkerForArrows(), "String2"));
	}

	@Test
	public void testunwantedTypesNull() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList("String"));
		whitelist.set(worker, whitelistv1);
		Method unwantedType = WorkerForArrows.class.getDeclaredMethod("unwantedTypes", String.class);
		unwantedType.setAccessible(true);
		assertEquals(false, unwantedType.invoke(new WorkerForArrows(), "null"));
	}

	@Test
	public void testunwantedTypesEmptyString() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		WorkerForArrows worker = new WorkerForArrows();
		Field whitelist = WorkerForArrows.class.getDeclaredField("whitelist");
		whitelist.setAccessible(true);
		ArrayList<String> whitelistv1 = new ArrayList<String>(Arrays.asList("String"));
		whitelist.set(worker, whitelistv1);
		Method unwantedType = WorkerForArrows.class.getDeclaredMethod("unwantedTypes", String.class);
		unwantedType.setAccessible(true);
		assertEquals(false, unwantedType.invoke(new WorkerForArrows(), ""));
	}

	@Test
	public void testgetTypesFromDescPrimatives() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method getTypesFromDesc = WorkerForArrows.class.getDeclaredMethod("getTypesFromDesc", String.class);
		getTypesFromDesc.setAccessible(true);
		String desc = "(ID)Ljava/lang/Object";
		List<String> returns = new ArrayList<String>(Arrays.asList("int", "double"));
		assertEquals(returns, getTypesFromDesc.invoke(new WorkerForArrows(), desc));
	}

	@Test
	public void testgetTypesFromDescClasses() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method getTypesFromDesc = WorkerForArrows.class.getDeclaredMethod("getTypesFromDesc", String.class);
		getTypesFromDesc.setAccessible(true);
		String desc = "(LTestMisfitsPackage/UMLArrowsTest;Ljava/lang/Thread;)Ljava/lang/Object";
		List<String> returns = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage.UMLArrowsTest", "java.lang.Thread"));
		assertEquals(returns, getTypesFromDesc.invoke(new WorkerForArrows(), desc));
	}

	@Test
	public void testgetTypesFromDescCandP() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method getTypesFromDesc = WorkerForArrows.class.getDeclaredMethod("getTypesFromDesc", String.class);
		getTypesFromDesc.setAccessible(true);
		String desc = "(LTestMisfitsPackage/UMLArrowsTest;ILjava/lang/Thread;DB)Ljava/lang/Object";
		List<String> returns = new ArrayList<String>(
				Arrays.asList("TestMisfitsPackage.UMLArrowsTest", "int", "java.lang.Thread", "double", "byte"));
		assertEquals(returns, getTypesFromDesc.invoke(new WorkerForArrows(), desc));
	}
	
	@Test
	public void testmakeSymbolPublic(){
		assertEquals("+", WorkerForArrows.makeSymbol(Opcodes.ACC_PUBLIC));
	}
	
	@Test
	public void testmakeSymbolPrivate(){
		assertEquals("-", WorkerForArrows.makeSymbol(Opcodes.ACC_PRIVATE));
	}
	
	@Test
	public void testmakeSymbolProtected(){
		assertEquals("#", WorkerForArrows.makeSymbol(Opcodes.ACC_PROTECTED));
	}
	
	@Test
	public void testmakeSymbolNone(){
		assertEquals("", WorkerForArrows.makeSymbol(0));
	}
	
}
