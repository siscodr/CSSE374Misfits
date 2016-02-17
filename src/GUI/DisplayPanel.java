package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;

public class DisplayPanel {

	public JPanel panel;

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public DisplayPanel() {
		panel=new JPanel();
		addTopBottomPane();
	}

	private void addTopBottomPane() {
		JPanel topPane = addTopPanel();
		JSplitPane bottomPane = addLeftRightPane();

		JSplitPane topBottomPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPane , bottomPane);
		//topBottomPane.setOneTouchExpandable(true);
		topBottomPane.setDividerLocation(30);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(300, 30);
		topPane.setMinimumSize(minimumSize);
		bottomPane.setMinimumSize(minimumSize);
		
		topBottomPane.setBounds(0, 0, 1000, 800);
		
		panel.setLayout(null);
		panel.add(topBottomPane);
	}

	private JPanel addTopPanel() {
		JPanel topPanel = new JPanel();
		JLabel label = new JLabel("loading");
		label.setText("Top Panel");
		label.setBounds(0, 0, 350, 50);
		topPanel.add(label);
		return topPanel;
		
	}

	private JSplitPane addLeftRightPane() {
		JScrollPane listScrollPane = addLeftPanel();
		JScrollPane pictureScrollPane = addRightPanel();

		JSplitPane leftRightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane , pictureScrollPane);
		//leftRightPane.setOneTouchExpandable(true);
		leftRightPane.setDividerLocation(300);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		listScrollPane.setMinimumSize(minimumSize);
		pictureScrollPane.setMinimumSize(minimumSize);
		
		leftRightPane.setBounds(0, 0, 1000, 800);
		panel.setLayout(null);
		//topBottomPane.add(leftRightPane, JSplitPane.BOTTOM);
		//panel.add(leftRightPane);
		return leftRightPane;
	}

	private JScrollPane addLeftPanel() {
		JScrollPane listScrollPane = new JScrollPane();
		
		JLabel label = new JLabel("left");
		label.setText("Left Pane");
		label.setBounds(0, 0, 300, 50);
		
		JViewport port = new JViewport();
		port.setView(label);
		listScrollPane.setViewport(port);
		return listScrollPane;
		
	}
	
	private JScrollPane addRightPanel() {
		JScrollPane listScrollPane = new JScrollPane();
		
		JLabel label = new JLabel("right");
		label.setText("Right Pane");
		label.setBounds(0, 0, 300, 50);
		
		JViewport port = new JViewport();
		port.setView(label);
		listScrollPane.setViewport(port);
		return listScrollPane;
		
	}

}
