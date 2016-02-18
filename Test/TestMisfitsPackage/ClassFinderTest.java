package TestMisfitsPackage;

import javafx.scene.shape.Path;

import org.junit.Test;

import MisfitsPackage.ClassFinder;

public class ClassFinderTest {

	@Test
	public void test() {
		String[] classes = ClassFinder
				.getClasses("C:/Users/siscodr/Documents/GitHub/CSSE374Misfits/bin");
		for (int i = 0; i < classes.length; i++) {
			if (!classes[i].contains("$")) {
				System.out.println(classes[i].split(".class")[0]);
			}
		}
	}

}
