package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConfigPanel {
	private JPanel panel;
	public static JLabel configs = new JLabel("configs");
	static int count = 0;
	static int timeSettingConfig = 0;

	public ConfigPanel() {
		panel = new JPanel();
		addText(panel);
	}

	private static void addText(JPanel panel) {
		java.nio.file.Path file = Paths.get(Configurations.getInstance().configFile);
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
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textAreaText = textArea.getText();
				java.nio.file.Path file2 = Paths.get(textAreaText);
				Configurations.getInstance().setConfigFile(textAreaText);
				setConfigText(file2, panel);
			}
		});
		setConfigText(file, panel);
		panel.add(update);
		JButton done = new JButton("button");
		done.setText("Done");
		done.setBounds(720, 640, 200, 50);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIMain.runStartPanel();
			}
		});
		panel.add(done);
	}

	public static void setConfigText(java.nio.file.Path file, JPanel panel) {
		configs.setText("");
		String toPrint = "<html>";
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				toPrint += line + "<br>";
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		toPrint += "</html>";
		configs.setText(toPrint);
		configs.setBounds(50, 200, 900, 500);
		panel.add(configs);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
