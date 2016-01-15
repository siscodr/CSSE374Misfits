package ShuffleMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsShuffleMain {
	public static void main(String[] args) throws IOException {
		List<Integer> toShuffle = new ArrayList<Integer>(Arrays.asList(1, 65, 2, 8943, 2));
		Collections.shuffle(toShuffle);// Runs the program
	}
}
