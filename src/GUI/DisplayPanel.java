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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ClassStorage.PatternStorage;
import UMLClasses.UMLArrows;

public class DisplayPanel {

	//TODO Setter for this
	public String imgLocation;
	
	private JPanel panel;
	private BufferedImage img;
	private String selectedClasses;
	
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
				try {
					ImageIO.write(img, "jpg", new File(Configurations.getInstance().outputDirectory + ".jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		fileMenu.add(exportItem);
		
		JMenuItem updateItem = new JMenuItem("Update");
		updateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO remove comments when integrated
				//UMLRenderer rend = new UMLRenderer();
				//rend.runGraphViz(UML Input);
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
		java.nio.file.Path file = Paths.get("docs\\About");
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
		java.nio.file.Path file = Paths.get("docs\\Instructions");
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
//		Object[] objects = new Object[4];
//		objects[0]="Decorator";
//		objects[1]="Adapter";
//		objects[2]="Singleton";
//		objects[3]="Other";
		//Test
		UMLArrows.getInstance().addPattern(new PatternStorage("Decorator","DecoratorClass.java",new ArrayList<String>()));
		UMLArrows.getInstance().addPattern(new PatternStorage("Singleton","SingletonClass.java",new ArrayList<String>()));
		//UMLArrows.getInstance().addPattern(new PatternStorage("Decorator","DecoratorClass.java",new ArrayList<String>()));
		
		 ArrayList<PatternStorage> patterns = UMLArrows.getInstance().getPatterns();
		final JCheckBoxTree tree = new JCheckBoxTree();
		DefaultMutableTreeNode rootNode= new DefaultMutableTreeNode("All");
		for(PatternStorage pattern : patterns){
			//if(rootNode.getChildCount()==0){
				DefaultMutableTreeNode newPatternNode = new DefaultMutableTreeNode(pattern.getPattern());
				newPatternNode.add(new DefaultMutableTreeNode(pattern.getHeadClass()));
				rootNode.add(newPatternNode);
			//}else{
			//for(int i = 0; i<rootNode.getChildCount();i++){
				//if(rootNode.getChildAt(i).toString().equals(pattern.getPattern())){
				//	((DefaultMutableTreeNode)rootNode.getChildAt(i)).add(new DefaultMutableTreeNode(pattern.getHeadClass()));
				//}else{
//					DefaultMutableTreeNode newPatternNode = new DefaultMutableTreeNode(pattern.getPattern());
//					newPatternNode.add(new DefaultMutableTreeNode(pattern.getHeadClass()));
//					rootNode.add(newPatternNode);
				//}
			//}
			//}
		}
		DefaultTreeModel model = new DefaultTreeModel(rootNode);
		
		
		tree.setModel(model);
		
		
		
		//tree.removeSelectionRow(0);
		tree.addCheckChangeEventListener(new JCheckBoxTree.CheckChangeEventListener() {
			public void checkStateChanged(JCheckBoxTree.CheckChangeEvent event) {
				TreePath[] paths = tree.getCheckedPaths();
				String classString = "";
				for (TreePath tp : paths) {
					if(tp.getPath().length==3){
						//for (Object pathPart : tp.getPath()) {
						classString= classString + " " +tp.getPath()[2];
							//classString= classString + " " +pathPart;
						//}
					}
					//TODO: format classString better
					System.out.println(classString);
					selectedClasses = classString;
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
