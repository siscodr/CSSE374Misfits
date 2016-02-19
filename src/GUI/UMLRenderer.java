package GUI;


public class UMLRenderer {
	public String inputDirectory;
	public String output;

	public void runGraphViz(String inputFilePath, String displayImg) {
		inputDirectory = Configurations.getInstance().getDotPath();
		output = displayImg;
		try {
			String[] com = new String[] {
					//"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
					"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
					"-Tpng", "-o", "docs\\Display.png", "drawableGraph.gv" };
			ProcessBuilder pb = new ProcessBuilder(com);
			Process p = pb.start();
			p.waitFor();
			System.out.println(p.exitValue());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
