# CSSE374Misfits
The UML Project for CSSE374

Description of our tool:
Milestone 1:
The design of our system is as simple as it can be.  It is made of only four classes.  The main class, DesignParser decorates a ClassVisitor using the other three classes.  These classes each have a specific functionality.  The ClassMethodVisitor finds the methods within the given set of code.  The ClassFieldVisitor determines whether the classes and variables are private, public or protected and their types. The ClassDeclarationVisitor finds the interfaces and super classes of the given code.  Together, these four classes work together to read in code and create text that can draw a UML diagram for that code with the use of the proper tool.

Milestone 2:
The design of this system has changed to accommodate use arrows.  To do this, we added an additional class called MyMethodVisitor which can determine which classes are used inside the method that is being visited.  All of the association arrows were made in Milestone 1 and were not touched in this milestone.

Milestone 3:
For the most part, our design for UMLs stayed the same.  We mostly just added new classes to draw sequence diagrams.  The one major change that we made was that we pulled out several static methods from UMLArrows to make a class of global functions that can be used by UMLArrows and SDArrows.

Milestone 4:
Because our code was so well designed, our design did not change in this milestone.

Milestone 5:
We refactored most of our code a lot.  Detection is implemented inside of detectors and adding class information happens in ClassContainer instead of UMLArrows.  UMLArrows still directs toward the current container.  All printing now occurs at the end of the program and happens from the UMLPrinter class.  

Milestone 6:
We stored more data about our classes.  This way, we could access more and more accurately detect which patterns are performed.

Milestone 7:
The design of our core code did not change substantially during this milestone.  The main changes to core code was the addition of stored data about our classes to make implementing future features possible.  We also added GUI code in this milestone.  The GUI code references the core code but the core code does not know about the GUI.

Who did what 
Milestone 1:
Morgan Cook: Wrote code to print the class name in the UML, implemented the ‘use’ and ‘extends’ arrows, wrote code to draw the line between fields and methods, did constant quality assurance and drew the UML diagram 
Donald Sisco: Implemented the arrows for interface, wrote code to deal with a class with no methods, wrote the code for MethodVisitor, added functionality for making all arrows, wrote code to point fields to their proper classes and wrote code to differentiate between white and black diamonds.
Jonathan Soulsby: added arrows to ClassMethodVisitor, wrote a function to deal with troublesome characters, wrote code to handle <>, wrote tests

Milestone 2:
Morgan Cook: created and wrote myMethodVisitor (including visitFieldInsn and visitMethodInsn), updated the UML diagram and documentation
Donald Sisco: took out all primitives listed, fixed duplicating arrows
Jonathan Soulsby: wrote all tests

Milestone 3:
Morgan Cook: Made "WorkerForArrows" class which is used by both UMLArrows and SDArrows, figured out which of the inherited methods of MethodVisitor we need, added ability to add to hashmap from visitors
Donald Sisco: Fixed design flaws in Milestone 1/2, rewrote tests from Milestone 1/2, created Instruction interface and several classes that implement it, wrote MethodInstruction
Jonathan Soulsby: figured out which of the inherited methods of MethodVisitor we need, wrote SDCreatorMain, added varNames Container to SDArrows

Milestone 4:
Morgan Cook: Added color to singleton class boxes, wrote singleton detection, wrote <<Singleton>> on classes that are singleton, updated the UML and documentation
Donald Sisco: Made modifications to the code to allow for singleton detection
Jonathan Soulsby: wrote all tests

Milestone 5:
Morgan Cook: Made detector classes and moved Singleton detector code into its detector class, created ClassContainer and extracted necessary information to this class, added label arrows and component label, updated all documentation
Donald Sisco: refactored / repackaged the code, passed ClassContainers to detectors, implemented code for Decorator Detectors, made ArrowStorage and UMLPrinter, stopped classes from pointing at themselves, added implementation of interface detection, helped write the adapter detection logic
Jonathan Soulsby: Fixed Singleton & UMLArrows tests, added detect methods to the detector classes, wrote adapter detection logic, wrote all tests

Milestone 6:
Morgan Cook: Updated Decorator Detector and Adapter Detector, fixed decorates label to label correct arrow, created and wrote foundation for CompositeDetector, wrote code to find component and composites, updated the UML and README
Donald Sisco: refactored the printing and detecting code, wrote code to find leaves, labeled the classes with their correct labels
Jonathan Soulsby: Updated Decorator Detector and Adapter Detector, updated tests for Milestone 5, wrote tests for Milestone 6

Milestone 7:
Morgan Cook: created Config panel, Configurations, LoadPanel and StartPanel; updated UML and README, wrote code to parse through the config file, made about and instructions windows, loaded configs upon start of program, researched how to execute graphviz from Java
Donald Sisco: implemented the functionality to perform 17/20 dice rolls, helped facilitate the communication between core code and GUI, helped figure out how to execute graphviz from Java, wrote the UMLRenderer
Jonathan Soulsby: created DisplayPanel and JCheckBoxTree; designed GUI structure, wrote DesignLoader, wrote communication between core code and GUI, wrote the progressBar

Instructions for UML (no GUI):
1)	Download the code into Eclipse
2)	Go to MisfitsUMLCreator -> src -> MisfitsPackage
3)	Right click on UMLCreatorMain and select Run As -> Run Configurations
4)	In the Arguments tab of the run configurations pop-up, put the name(s) of the class(es) for which you want to create a UML diagram (if that code is not in the MisfitsUMLCreator package, you will need to add the package via the Classpath tab)
5)	Click run
6)	Copy the text that is printed in the console
7)	Open gvedit.exe
8)	Create a new project in gvedit.exe
9)	Paste the text into that project
10)	Click the layout button (the running man)

Instructions for UML (with GUI):
1) Download the code into Eclipse.
2) Go to MisfitsUMLCreator -> src -> GUI.
3) Right click on GUIMain and select Run As Java Application.
4) From the start panel, click the "Set Configurements" button to adjust your configurations appropriately.  The configurations are as follows:
	Input-Folder: this is the folder that contains the classes in which you want to detector to find patterns.
	Input-Classes: this is any additional classes in which you want to detect patterns.
	Output-Directory: this is where you want the final image to be exported.
	Dot-Path: this is the path to your GraphViz.
	Phases: These are the patterns that you want to detect in the order that you want to detect them.
	Additional: Any thresholds needed by the pattern detectors.
		The system currently does not require any thresholds for pattern detection.		
5) Click the launch button to launch the program.
6) Once the display panel appears, adjust which patterns and classes you want to appear in the image by using the checkboxes along the side and click File -> Update.
7) To export the graph, click File -> Export.
8) To reset the configurations, click File -> Reset.
9) To learn more about the program, click Help -> About.


Instructions for SD:
1)	Download the code into Eclipse
2)	Go to MisfitsUMLCreator -> src -> MisfitsPackage
3)	Right click on SDCreatorMain and select Run As -> Run Configurations
4)	In the Arguments tab of the run configurations pop-up, put the name(s) of the class(es) for which you want to create a sequence diagram (the first class must contain a main function in which the sequence diagram can run).  (If the code is not in the MisfitsUMLCreator package, you will need to add the package via the Classpath tab).
5)	Click run
6)	Copy the text that is printed in the console
7)	Open sdedit-4.2-beta1.exe
8)	Create a new project in sdedit-4.2-beta1.exe
9)	Paste the text into that project
10)	The sequence diagram should automatically appear