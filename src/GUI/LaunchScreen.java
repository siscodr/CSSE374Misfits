package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.scene.text.Font;

public class LaunchScreen {
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
		//f.addKeyListener(new KeyListener());
		f.add(panel);
		f.setSize(1000, 800);
		addWelcomeLabel(panel);
		addConfigButton(panel);
		addLaunchButton(panel);
		f.setVisible(true);
		
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
		launchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//LAUNCH
			}
		});
		panel.add(launchButton);
		
	}

	private static void addConfigButton(JPanel panel) {
		JButton configButton = new JButton("config");
		panel.setLayout(null);
		configButton.setText("Set configurments");
		configButton.setBounds(200, 300, 200, 50);
		configButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Launch configurements page
			}
		});
		panel.add(configButton);
		
	}
	
}
