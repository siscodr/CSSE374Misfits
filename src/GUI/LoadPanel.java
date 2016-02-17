package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadPanel {

	private JPanel panel;



	public LoadPanel() {
		panel = new JPanel();
		addLoadingLabel(panel);
		addProgressBar(panel);
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
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
