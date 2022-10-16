**Project Title**: School Grading Management System

**Project Team #5**: Steven Pham, Nir Guberman, Cami Hawkins

**Proposal made by**: Steven Pham

**Problem/Issue to resolve**: 
Schools need a gradebook(s) in order to keep track of the performance of each student. However, doing this on paper not only takes a lot of time, but is prone to error due to human mistakes, especially when calculating the final grades. Students are also unable to keep track of their current grades, as direct access to the gradebook may lead them to alter results. 

**Assumptions**: Upon creation and start of the system, a default admin user will be entered into the system. This user will be used in order to add all other users into the system. All registered users/individuals within the system will have a name, and a unique ID number. This program is to be made almost solely via Java.

**Operating environments**: The program will run in a Java Virtual Machine, meaning all operating systems capable of obtaining and using a JVM can run this. 

**Intended Usage**: This system is intended to be used by schools. 

**High-level Description of solution**: 
We are developing a system that will replace paper gradebooks. Professors can add assignments and detail how many points each student obtained. In this system, all registered users (admins, professors, students) will be able to interact with the gradebook, with certain restrictions placed depending on the type of user. Users will have to login with their name and ID before being able to interact with the system. 

The plan is to start with the development of smaller components, such as the classes for each type of user, and the courses + assignments. These components will be used to complete the main functions of the system in a separate, overarching class. Edits can be made to the components as needed. 

After the main functions are completed, ideally, a graphical user interface (GUI) would be made to accommodate and work alongside it. This may be achieved through the use of canvas API. 


**Functionality**:
The system will tackle the issue of paper gradebooks by streamlining the grading process by allowing the computer to take care of a majority of the calculations for updating a grade. Professors will only need to input how many points an assignment is out of, and how many points each student obtained for the assignment. From here, the system will take over and use those inputs to update each student’s grades. 
Furthermore, students will now be able to login and view their grades without having the ability to alter them. Students may also choose to print their transcript. 

**Operations for Users**:
  
  Students: 
  - Can view current and past grades of the courses they have taken.
  - Can print a transcript detailing their course history and grades. 


  Professors: 
  - Can add/remove assignments of students in the classes they are responsible for.
  - Can add or remove students from the class. Removal of a student will not delete the student from the system. 


  Admins: 
  - Can do everything a professor can do, without the restriction of only performing actions in the class they are in.  
  - Can add new professors and students into the system. This can be done either manually, or by loading in a file (.txt preferably). 
  - Can remove professors and students from the system. 
