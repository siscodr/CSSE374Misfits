package TestMisfitsPackage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandLineGraphvizConceptTest {

	@Test
	public void test() {
		try {
			String[] com = new String[] {
					"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
					"-Tpng", "-o", "test.png", "drawableGraph.gv" };
			ProcessBuilder pb = new ProcessBuilder(com);
			Process p = pb.start();
			p.waitFor();
			assertEquals(0,p.exitValue());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
