package GUI;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIMain {

	private static JFrame frame;
	private static JPanel panel;

	public static void main(String[] args) {
		createFrame();
		runStartPanel();
	}

	private static void createFrame() {
		frame = new JFrame("Pattern Detector");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setVisible(true);
	}

	public static void runStartPanel() {
		if(panel!=null){
			frame.getContentPane().remove(panel);
		}
		StartPanel panelLoader = new StartPanel();
		panel = panelLoader.getPanel();
		frame.getContentPane().add(panel);
		frame.invalidate();
		frame.validate();
	}

	public static void runLoadPanel() {
		frame.getContentPane().remove(panel);
		LoadPanel panelLoader = new LoadPanel();
		panel = panelLoader.getPanel();
		frame.getContentPane().add(panel);
		System.out.println("added loadpanel");
		frame.invalidate();
		frame.validate();
	}

}
