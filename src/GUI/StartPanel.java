package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel {

	private JPanel panel;

	public StartPanel() {
		panel = new JPanel();
		addWelcomeLabel(panel);
		addConfigButton(panel);
		addLaunchButton(panel);
	}

	private static void addWelcomeLabel(JPanel panel) {
		JLabel label = new JLabel("welcome");
		label.setText("Welcome to the Team Misfits GUI generator!");
		panel.setLayout(null);
		label.setBounds(325, 100, 800, 50);
		panel.add(label);
	}

	private static void addLaunchButton(JPanel panel) {
		JButton launchButton = new JButton("launch");
		panel.setLayout(null);
		launchButton.setText("Launch");
		launchButton.setBounds(500, 300, 200, 50);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIMain.runLoadPanel();
			}
		});
		panel.add(launchButton);

	}

	private static void addConfigButton(JPanel panel) {
		JButton configButton = new JButton("config");
		panel.setLayout(null);
		configButton.setText("Set configurments");
		configButton.setBounds(200, 300, 200, 50);
		configButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Launch configurements page
			}
		});
		panel.add(configButton);

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
