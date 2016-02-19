package GUI;


public class UMLRenderer {
	public String inputDirectory;
	public String output;

	public void runGraphViz(String inputFilePath) {
		inputDirectory = Configurations.getInstance().getDotPath();
		output = Configurations.getInstance().getOutputDirectory();
		try {
			String[] com = new String[] {
					//"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
					inputDirectory,
					"-Tpng", "-o", output, inputFilePath };
			ProcessBuilder pb = new ProcessBuilder(com);
			Process p = pb.start();
			p.waitFor();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
