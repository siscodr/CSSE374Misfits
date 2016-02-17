package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConfigPanel {
	private JPanel panel;



	public ConfigPanel() {
		panel = new JPanel();
		addText(panel);
	}

	private static void addText(JPanel panel) {
		JLabel label = new JLabel("config main label");
		label.setText("Here are your configurations!");
		panel.setLayout(null);
		label.setBounds(400, 50, 350, 50);
		panel.add(label);
		JLabel pathLabel = new JLabel("folder label");
		pathLabel.setText("Configuration file path:");
		pathLabel.setBounds(50, 150, 350, 50);
		panel.add(pathLabel);
		JTextArea textArea = new JTextArea("text area");
		textArea.setText("");
		textArea.setBounds(200, 163, 750, 25);
		panel.add(textArea);
		JButton update = new JButton("button");
		update.setText("Update");
		update.setBounds(400, 200, 200, 50);
		panel.add(update);
		JLabel configs = new JLabel("configs label");
		configs.setText("These are the configs!!!");
		configs.setBounds(100, 300, 700, 500);
		panel.add(configs);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
