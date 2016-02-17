package MisfitsPackage;

import java.io.File;
import java.util.ArrayList;

public class ClassFinder {

	public static String[] getClasses(String path) {

		File root = new File(path);
		File[] list = root.listFiles();
		ArrayList<String> classes = new ArrayList<String>();

		if (list == null)
			return null;

		for (File f : list) {
			if (f.isDirectory()) {
				classes.addAll(recursiveStep(f.getAbsolutePath()));
			} else {
				classes.add(f.getPath());
			}
		}
		return (String[]) classes.toArray();
	}

	private static ArrayList<String> recursiveStep(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		ArrayList<String> classes = new ArrayList<String>();

		if (list == null)
			return classes;

		for (File f : list) {
			if (f.isDirectory()) {
				classes.addAll(recursiveStep(f.getAbsolutePath()));
			} else {
				classes.add(f.getPath());
			}
		}
		return classes;
	}
}
