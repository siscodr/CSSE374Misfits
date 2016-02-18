package TestMisfitsPackage;

import org.junit.Test;

public class yolo {

	@Test
	public void test() {
		try {
			String[] com = new String[]{"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe","-Tpng", "-o", "morgansCoolGraph.png", "drawableGraph.gv"};
			//String temp = "\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe\" -Tpng -o morgansCoolGraph.png drawableGraph.gv";
			ProcessBuilder pb = new ProcessBuilder(com);
			//pb.redirectOutput(new File("morgansCoolGraph.png"));
			Process p = pb.start();
			p.waitFor();
			System.out.println(p.exitValue());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// "C:\Program Files (x86)\Graphviz2.38\bin\dot" -Tpng drawableGraph.gv
		// > graph.png
	}
}
