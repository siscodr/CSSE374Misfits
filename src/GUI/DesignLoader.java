package GUI;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import MisfitsPackage.DesignParser;
import MisfitsPackage.WorkerForArrows;
import UMLClasses.PatternDetector;
import UMLClasses.UMLArrows;

public class DesignLoader {
	
	public void runDesign(LoadPanel panel) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
		
		loadDecorators();
		
		
		String[] classes = MisfitsPackage.ClassFinder.getClasses(Configurations.getInstance().inputFolder);
		WorkerForArrows.addWhitelist(classes);
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

	private void loadDecorators() throws ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		//TODO: parse inputfolder + inputclasses

		for(String pattern : Configurations.getInstance().classString){
			Class myClass = Class.forName(pattern);
			//
			Class[] types = {Object.class};
			Constructor constructor = myClass.getConstructor(types);
			//
			Object[] parameters = {Object.class};
			Object instanceOfMyClass = constructor.newInstance(parameters);
			
			UMLArrows.getInstance().addDetector((PatternDetector)instanceOfMyClass);
		}
	}
}
