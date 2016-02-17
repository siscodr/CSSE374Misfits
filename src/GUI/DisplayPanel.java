package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

public class DisplayPanel {

	private JPanel panel;
	private BufferedImage img;
	
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
		JToolBar topPane = addToolBar();
		JSplitPane bottomPane = addLeftRightPane();

		JSplitPane topBottomPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPane , bottomPane);
		topBottomPane.setOneTouchExpandable(true);
		topBottomPane.setDividerLocation(30);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(300, 30);
		topPane.setMinimumSize(minimumSize);
		bottomPane.setMinimumSize(minimumSize);
		
		topBottomPane.setBounds(0, 0, 985, 760);
		
		panel.setLayout(null);
		panel.add(topBottomPane);
	}

	private JToolBar addToolBar() {
		JToolBar toolBar = new JToolBar();
		JButton fileButton = new JButton("File");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//TODO
			}
		});
		
		JButton helpButton = new JButton("Help");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//TODO
			}
		});
		
		toolBar.add(fileButton);
		toolBar.add(helpButton);
		toolBar.setFloatable(false);
		
		return toolBar;
	}

	private JSplitPane addLeftRightPane() {
		JScrollPane listScrollPane = addLeftPanel();
		JScrollPane pictureScrollPane = addRightPane();

		JSplitPane leftRightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane , pictureScrollPane);
		leftRightPane.setOneTouchExpandable(true);
		leftRightPane.setDividerLocation(300);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		listScrollPane.setMinimumSize(minimumSize);
		pictureScrollPane.setMinimumSize(minimumSize);
		
		leftRightPane.setBounds(0, 0, 1000, 800);
		panel.setLayout(null);
		return leftRightPane;
	}

	private JScrollPane addLeftPanel() {
		
		JScrollPane listScrollPane = new JScrollPane(checkBoxTree());
		return listScrollPane;
	}
	
	private JScrollPane addRightPane() {
		//JViewport port = new JViewport();
		JScrollPane rightPane=null;
		try {
		    //img = ImageIO.read(new File("UpdatedDocs/Milestone6/MisfitsUMLM6.jpg"));
			img = ImageIO.read(new File("docs/MisfitsUML.jpg"));
			JPanel pic=new Pic();
			pic.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
			rightPane = new JScrollPane(pic);
			
		} catch (IOException e) {
		}

		return rightPane;
		
	}
	private class Pic extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(img, 0, 0, null);          
	    }
	}
	
	private JTree checkBoxTree(){
		Object[] objects = new Object[3];
		objects[0]="Decorator";
		objects[1]="Adapter";
		objects[2]="Singeton";
		
		JTree tree = new JTree(objects);
		
		return tree;
		
	}
}
