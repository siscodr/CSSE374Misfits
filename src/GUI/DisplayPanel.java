package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.TreePath;

public class DisplayPanel {

	//TODO Setter for this
	public String imgLocation;
	
	private JPanel panel;
	private BufferedImage img;

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public DisplayPanel() {
		panel = new JPanel();
		addTopBottomPane();
	}

	private void addTopBottomPane() {
		JMenuBar topPane = addMenuBar();
		JSplitPane bottomPane = addLeftRightPane();

		JSplitPane topBottomPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				topPane, bottomPane);
		topBottomPane.setOneTouchExpandable(true);
		topBottomPane.setDividerLocation(30);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(300, 30);
		topPane.setMinimumSize(minimumSize);
		bottomPane.setMinimumSize(minimumSize);

		topBottomPane.setBounds(0, 0, 985, 760);

		panel.setLayout(null);
		panel.add(topBottomPane);
	}

	private JMenuBar addMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");

		JMenuItem resetItem = new JMenuItem("Reset");
		resetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIMain.runStartPanel();
			}
		});
		fileMenu.add(resetItem);

		JMenuItem exportItem = new JMenuItem("Export");
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				System.out.println("I'm Exporting!");
			}
		});
		fileMenu.add(exportItem);
		
		JMenuItem updateItem = new JMenuItem("Update");
		updateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				System.out.println("I'm Updating The Image!");
			}
		});
		fileMenu.add(updateItem);
		
		JMenuItem instructionItem = new JMenuItem("Instruction");
		instructionItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performInstructions();
			}
		});
		helpMenu.add(instructionItem);

		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performAbout();
			}
		});
		helpMenu.add(aboutItem);

		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		return menuBar;
	}
	
	public void performAbout() {
		JFrame frame = new JFrame("About");
		frame.setSize(700, 500);
		frame.setVisible(true);
		java.nio.file.Path file = Paths.get("C:\\Users\\cookmn\\Documents\\GitHub\\CSSE374Misfits\\docs\\About");
		JLabel label = new JLabel("About label");
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
		label.setText(toPrint);
		frame.add(label);
		frame.pack();
	}
	
	public void performInstructions() {
		JFrame frame = new JFrame("Instructions");
		frame.setSize(700, 500);
		frame.setVisible(true);
		java.nio.file.Path file = Paths.get("C:\\Users\\cookmn\\Documents\\GitHub\\CSSE374Misfits\\docs\\Instructions");
		JLabel label = new JLabel("Instructions label");
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
		label.setText(toPrint);
		frame.add(label);
		frame.pack();
	}

	private JSplitPane addLeftRightPane() {
		JScrollPane listScrollPane = addLeftPanel();
		JScrollPane pictureScrollPane = addRightPane();

		JSplitPane leftRightPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				listScrollPane, pictureScrollPane);
		leftRightPane.setOneTouchExpandable(true);
		leftRightPane.setDividerLocation(300);

		// Provide minimum sizes for the two components in the split pane
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
		// JViewport port = new JViewport();
		JScrollPane rightPane = null;
		try {
			// img = ImageIO.read(new
			// File("UpdatedDocs/Milestone6/MisfitsUMLM6.jpg"));
			img = ImageIO.read(new File("docs/MisfitsUML.jpg"));
			JPanel pic = new Pic();
			pic.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
			rightPane = new JScrollPane(pic);

		} catch (IOException e) {
		}

		return rightPane;

	}

	private JCheckBoxTree checkBoxTree() {
		// Object[] objects = new Object[3];

		final JCheckBoxTree tree = new JCheckBoxTree();
		//tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
		//tree.removeSelectionRow(0);
		tree.addCheckChangeEventListener(new JCheckBoxTree.CheckChangeEventListener() {
			public void checkStateChanged(JCheckBoxTree.CheckChangeEvent event) {
				System.out.println("event");
				TreePath[] paths = tree.getCheckedPaths();
				for (TreePath tp : paths) {
					for (Object pathPart : tp.getPath()) {
						System.out.print(pathPart + ",");
					}
					System.out.println();
				}
			}
		});
		return tree;
	}

	private class Pic extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, null);
		}
	}
}
