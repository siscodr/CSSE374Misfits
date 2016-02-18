package TestMisfitsPackage;

import java.io.IOException;

import org.junit.Test;

public class yolo {

	@Test
	public void test() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime
				.exec("C:/Program Files (x86)/Graphviz2.38/bin/gvedit.exe C:/Users/siscodr/Documents/GitHub/CSSE374Misfits/drawableGraph.gv");
	}

}
