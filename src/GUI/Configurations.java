package GUI;

import java.util.ArrayList;
import java.util.Arrays;

import MisfitsPackage.ClassFinder;

public class Configurations {
	private static ArrayList<String> myArray = new ArrayList<String>();
	private static Configurations ourConfigs; 
	public String inputFolder;
	public String inputClasses;
	public String outputDirectory;
	public String dotPath;
	public String phases;
	public ArrayList<String> patternDelegations;
	public ArrayList<String> classString;
	public ArrayList<String> patternString;

	private Configurations(String iFolder, String iClasses, String oDirectory, String dPath, String pha, ArrayList<String> arrayList) {
		inputFolder = iFolder;
		inputClasses = iClasses;
		outputDirectory = oDirectory;
		dotPath = dPath;
		phases = pha;
		patternDelegations = arrayList;
	}

	public static Configurations getInstance() {
		if(ourConfigs == null){
			ourConfigs = new Configurations("c:\\User1\\Documents\\Lab2-1\\bin", "java.io.Reader,java.io.BufferedReader,java.lang.Runtime,org.asm.ClassVisitor", "C:\\Users\\cookmn\\Documents\\GitHub\\CSSE374Misfits\\docs\\Image", "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe", "Loader, Decorator-Detector, Singleton-Detector", myArray);
		}
		return ourConfigs;
	}
	
	public void setClasses() {
		String[] classes = ClassFinder.getClasses(inputFolder);
		ArrayList<String> classesToAdd = new ArrayList<String>();
		classesToAdd.addAll(Arrays.asList(inputClasses.split(" ")));
		if(classes != null) {
			classesToAdd.addAll(Arrays.asList(classes));
		}
		classString=classesToAdd;
	}
	
	public void parsePatterns() {
		patternString = new ArrayList<String>();
		patternString.addAll(Arrays.asList(phases.split(" ")));
	}

	public String getInputFolder() {
		return inputFolder;
	}

	public void setInputFolder(String inputFolder) {
		this.inputFolder = inputFolder;
	}

	public String getInputClasses() {
		return inputClasses;
	}

	public void setInputClasses(String inputClasses) {
		this.inputClasses = inputClasses;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public String getDotPath() {
		return dotPath;
	}

	public void setDotPath(String dotPath) {
		this.dotPath = dotPath;
	}

	public String getPhases() {
		return phases;
	}

	public void setPhases(String phases) {
		this.phases = phases;
	}

	public ArrayList<String> getPatternDelegations() {
		return patternDelegations;
	}

	public void setPatternDelegations(ArrayList<String> patternDelegations) {
		this.patternDelegations = patternDelegations;
	}


}
