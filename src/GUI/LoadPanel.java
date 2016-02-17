package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class LoadPanel {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		JFrame f = new JFrame("Launching GUI!");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		f.add(panel);
		f.setSize(1000, 800);
		addLoadingLabel(panel);
		addProgressBar(panel);
		f.setVisible(true);
	}

	private static void addProgressBar(JPanel panel) {
		JProgressBar progBar = new JProgressBar();
		panel.setLayout(null);
		progBar.setBounds(250, 450, 500, 100);
		panel.add(progBar);
		
	}

	private static void addLoadingLabel(JPanel panel) {
		JLabel label = new JLabel("loading");
		label.setText("Your display is generating.  This will just take a minute.");
		panel.setLayout(null);
		label.setBounds(300, 200, 350, 50);
		panel.add(label);
	}
}
