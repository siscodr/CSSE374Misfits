# CSSE374Misfits
The UML Project for CSSE374

Description of our tool:
Milestone 1:
The design of our system is as simple as it can be.  It is made of only four classes.  The main class, DesignParser decorates a ClassVisitor using the other three classes.  These classes each have a specific functionality.  The ClassMethodVisitor finds the methods within the given set of code.  The ClassFieldVisitor determines whether the classes and variables are private, public or protected and their types. The ClassDeclarationVisitor finds the interfaces and super classes of the given code.  Together, these four classes work together to read in code and create text that can draw a UML diagram for that code with the use of the proper tool.

Milestone 2:
The design of this system has changed to accommodate use arrows.  To do this, we added an additional class called MyMethodVisitor which can determine which classes are used inside the method that is being visited.  All of the association arrows were made in Milestone 1 and were not touched in this milestone.

Milestone 3:
For the most part, our design for UMLs stayed the same.  We mostly just added new classes to draw sequence diagrams.  The one major change that we made was that we pulled out several static methods from UMLArrows to make a class of global functions that can be used by UMLArrows and SDArrows.

Who did what 
Milestone 1:
Morgan Cook: Wrote code to print the class name in the UML, implemented the ‘use’ and ‘extends’ arrows, wrote code to draw the line between fields and methods, did constant quality assurance and drew the UML diagram 
Donald Sisco: Implemented the arrows for interface, wrote code to deal with a class with no methods, wrote the code for MethodVisitor, added functionality for making all arrows, wrote code to point fields to their proper classes and wrote code to differentiate between white and black diamonds.
Jonathon Soulsby: added arrows to ClassMethodVisitor, wrote a function to deal with troublesome characters, wrote code to handle <>, wrote tests

Milestone 2:
Morgan Cook: created and wrote myMethodVisitor (including visitFieldInsn and visitMethodInsn), updated the UML diagram and documentation
Donald Sisco: took out all primitives listed, fixed duplicating arrows
Jonathon Soulsby: wrote all tests

Milestone 3:
Morgan Cook: Made "WorkerForArrows" class which is used by both UMLArrows and SDArrows, figured out which of the inherited methods of MethodVisitor we need, added ability to add to hashmap from visitors
Donald Sisco: Fixed design flaws in Milestone 1/2, rewrote tests from Milestone 1/2, created Instruction interface and several classes that implement it, wrote MethodInstruction
Jonathon Soulsby: figured out which of the inherited methods of MethodVisitor we need, wrote SDCreatorMain, added varNames Container to SDArrows

Instructions for UML:
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