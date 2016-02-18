package ClassStorage;

import java.util.ArrayList;

public class PatternStorage {
	private String pattern;
	private String headClass;
	private ArrayList<String> listofClasses;
	
	public PatternStorage(String pattern, String headClass,
			ArrayList<String> listofClasses) {
		this.pattern = pattern;
		this.headClass = headClass;
		this.listofClasses = listofClasses;
	}
	public String getPattern() {
		return pattern;
	}

	public String getHeadClass() {
		return headClass;
	}

	public ArrayList<String> getListofClasses() {
		return listofClasses;
	}
}
