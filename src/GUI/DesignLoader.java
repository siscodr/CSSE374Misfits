package GUI;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import MisfitsPackage.DesignParser;
import MisfitsPackage.WorkerForArrows;
import UMLClasses.PatternDetector;
import UMLClasses.UMLArrows;

public class DesignLoader {
	
	public void runDesign(LoadPanel panel) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
		
		loadPatterns();
		
		
		ArrayList<String> classes = Configurations.getInstance().classString;
		String[] toWhitelist = new String[classes.size()];
		for (int i = 0; i < classes.size(); i++) {
			toWhitelist[i] = classes.get(i);
		}
		WorkerForArrows.addWhitelist(toWhitelist);
		for (String className : classes) {
			UMLArrows.getInstance().resetUMLArrows(className);
			try {
				DesignParser.makeReader(className);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		UMLArrows.getInstance().resetUMLArrows("");
		//DONE MAKING CLASSES
		UMLArrows.getInstance().detect();
	}

	private void loadPatterns() throws ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		//TODO: parse inputfolder + inputclasses

		for(String pattern : Configurations.getInstance().patternString){
			Class myClass = Class.forName("UMLClasses." + pattern);
			//
			Class[] types = {Object.class};
			Constructor constructor = myClass.getConstructor(types);
			//
			Object[] parameters = {Configurations.getInstance().getThreshold(pattern)};
			Object instanceOfMyClass = constructor.newInstance(parameters);
			
			UMLArrows.getInstance().addDetector((PatternDetector)instanceOfMyClass);
		}
	}
}
