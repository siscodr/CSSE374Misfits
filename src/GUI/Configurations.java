package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JPanel;

import MisfitsPackage.ClassFinder;

public class Configurations {
	private static Configurations ourConfigs; 
	public String inputFolder;
	public String inputClasses;
	public String outputDirectory;
	public String dotPath;
	public String phases;
	public String configFile;
	public ArrayList<String> patternDelegations;
	public ArrayList<String> classString;
	public ArrayList<String> patternString;
	public HashMap<String, Object> thresholds;
	static int count = 0;

	private Configurations() {
		configFile = "docs\\ConfigFile";
		patternDelegations = new ArrayList<String>();
		classString = new ArrayList<String>();
		patternString = new ArrayList<String>();
		thresholds = new HashMap<String, Object>();
	}

	public static Configurations getInstance() {
		if(ourConfigs == null){
			ourConfigs = new Configurations();
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
	
	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
		loadConfig(configFile);
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
	
	public void setThreshold() {
		for(int i = 0; i < patternDelegations.size(); i+=2){
			thresholds.put(patternDelegations.get(i), patternDelegations.get(i + 1));
		}
	}

	public Object getThreshold(String pattern) {
		if(!thresholds.containsKey(pattern)){
			return null;
		}
		return thresholds.get(pattern);
	}

	private void doParsing(String toPrint) {
		String[] strings = new String[2];
		if(count == 0){
			strings = toPrint.split(": ");
			Configurations.getInstance().setInputFolder(strings[1]);
		} else if(count == 1) {
			strings = toPrint.split(": ");
			Configurations.getInstance().setInputClasses(strings[1]);
		} else if(count == 2) {
			strings = toPrint.split(": ");
			Configurations.getInstance().setOutputDirectory(strings[1]);
		} else if(count == 3) {
			strings = toPrint.split(": ");
			Configurations.getInstance().setDotPath(strings[1]);
		} else if(count == 4) {
			strings = toPrint.split(": ");
			Configurations.getInstance().setPhases(strings[1]);
		} else{
			strings = toPrint.split(": ");
			Configurations.getInstance().patternDelegations.add(strings[0]);
			Configurations.getInstance().patternDelegations.add(strings[1]);
		}
		count++;
	}
	
	public void loadConfig(String inputPath){
		count = 0;
		java.nio.file.Path file = Paths.get(inputPath);
		int tempCount= 0;
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {	
		    	if(tempCount %2 == 0){
		    		doParsing(line);
		    	}
		    	tempCount++;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		setClasses();
		parsePatterns();
		setThreshold();
	}
	

}
