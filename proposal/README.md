**Project Title**: School Grading Management System

**Project Team #5**: Steven Pham, Nir Guberman, Cami Hawkins

**Proposal Contributions:**<br/>
Steven Pham - Made and edited the entire proposal. <br/>

**Presentation Contributions:**<br/>
Steven Pham - Created the slides, wrote some speaker notes, and presented middle portion slides<br/>
Nir Guberman - Presented end portion slides, and came up with a demo improv script.<br/>
Cami Hawkins - Presented beginning portion slides<br/>

**Project Code Contributions:**<br/>
Steven Pham - Created the majority of backend located in, some GUI, and some connections between backend and frontend. Bug fixer.<br/>
Nir Guberman - Created a majority amount of GUI and majority of connections between backend and frontend. Bug Fixer.<br/>
Cami Hawkins - Created some GUI and decorations for GUI. <br/>

**Report Contributions:**<br/>
Steven Pham - Made the report + Diagrams.

**Assumptions**: Upon creation and start of the system, a default admin user will be entered into the system. This user will be 
used in order to add all other users into the system. This admin has the first name "Default", last name "Admin", username "DA-0000", 
and password "Password1". All registered users/individuals within the system will have a first name, last name, password and a unique 
ID number. Two preset save files consisting of admins, professors and student can be loaded in using the default admin's load file option: 
"gradeSystemSaveFile.txt" and "gradeSystemSaveFile (1).txt". This program is to be made almost, if not, solely via Java. 

**Operating environments**: The program will run in a Java Virtual Machine, meaning all operating systems capable of obtaining 
and using a JVM can run this. 

**Intended Usage**: This system is intended to be used by schools. If the school does not already have a save file, the school 
will have to manually input each user into the system using the default admin. Otherwise, they can simply load in a file using the 
default admin. Due to the limitations and low level security of the program, the program is to be ran in a school-operated or controlled 
computer, such as one in the library. 

**Diagrams:**
File Type| File Link  | 
| ---------- | ---------- | 
| Class Diagram | [classDiagram.pdf](https://github.com/Ourasa/CS151-School-Grading-Management-System/blob/main/diagrams/classDiagram.pdf) |
| Use Case Diagram | [useCaseDiagram.pdf](https://github.com/Ourasa/CS151-School-Grading-Management-System/blob/main/diagrams/useCaseDiagram.pdf) |
| State Diagram | [stateDiagram.pdf](https://github.com/Ourasa/CS151-School-Grading-Management-System/blob/main/diagrams/stateDiagram.pdf) | 
| Sequence Diagram | [sequenceDiagram.pdf](https://github.com/Ourasa/CS151-School-Grading-Management-System/blob/main/diagrams/sequenceDiagram.pdf) | 


**Problem/Issue to resolve**: 
Schools need a gradebook(s) in order to keep track of the performance of each student. However, doing this on paper not only 
takes a lot of time, but is prone to error due to human mistakes, especially when calculating the final grades. Students are 
also unable to keep track of their current grades, as direct access to the gradebook may lead them to alter results. 

**Solution**: 
We are developed a system that will replace paper gradebooks. Professors can add assignments and detail how many points each student 
obtained. In this system, all registered users (admins, professors, students) will be able to interact with the gradebook, with certain 
options and restrictions placed depending on the type of user. Users will have to login with their ID and password before being able to interact 
with the system. Those who cannot login are effectively locked out of the program.

We started with the development of smaller components, such as the classes for each type of user, and the courses + assignments. 
These components were then used to complete the main functions of the system in a separate, overarching backend class. Edits were made to the 
components as needed. The main backend functions were developed in parallel with the GUI. This is to ensure that the GUI and backend are able 
to properly communicate with each other, and complete the task. 

**Functionality**:
The system will tackle the issue of paper gradebooks by streamlining the grading process by allowing the computer to take care of a majority of the calculations for updating a grade. Professors will only need to input how many points an assignment is out of, and how many points each student obtained for the assignment. From here, the system will take over and use those inputs to update each student’s grades. 
Furthermore, students will now be able to login and view their grades without having the ability to alter them. Students may also choose to print their transcript. 

**Operations for Users**:
  
  Students: 
  - Can view GPA, and current/past grades of the courses they have taken.
  - Can print a transcript detailing their course history and grades. 


  Professors: 
  - Can add/remove/grade assignments of students in the classes they are responsible for.
  - Can add or remove students from the class. Removal of a student will not delete the student from the system. 
  - Can view their students and their grades in a course they are responsible for.
  - Can complete a course, removing a course from the active courses and moving it to past course for each student. 


  Admins: 
  - Can add or remove students from the class. Removal of a student will not delete the student from the system. 
  - Can add new courses into the system. 
  - Can add new professors and new students into the system.
  - Can assign or remove a professor to/from a course.
  - Can remove professors and students from the system. 
  - Can remove courses from the system. 
  - Can view all Users and their information, including passwords.
  - Can save the state of the system into a txt file.
  - Can load a txt file to restore system to a former state. 


**Steps to run the code**<br/>
1.) Download the project as a zip file. Extract the zip file into a folder.<br/>
2.) Open Eclipse. Select Import and select Existing Maven Project. <br/>
3.) Select extracted folder, then the project inside the list, and click "Finish".<br/>
4.) Go into src/main/java, and into package com.gradebook.gradebook. Double click on Driver, and hit Run. <br/>

Note 1: For ease of use/testing, we created 3 baseline profiles of each user type in the preset files. 
Only the default Admin is present when first starting the program. This is shown on the table below. <br/>

Note 2: To load a preset file to load, navigate to the folder consisting of the zip extract. Enter the file 
until you see the "gradebook" folder. Enter the folder, and you should be able to see "gradeSystemSaveFile.txt"
and "gradeSystemSaveFile (1).txt". 

| User Type | Username | Password | 
|-----------|----------|----------|
| Admin | DA-0000 | Password1 |
| Professor | DP-0000 | Password2 |
| Student | DS-0000 | Password3 |

<br/>

**Snapshots of running program** <br/>
Note: Due to the sheer amount of screens due to the numerous options each user have, only the login and dashboard 
screens will be shown here. 

Login Screen - Shows up immediately upon running program.
![image](https://user-images.githubusercontent.com/111930214/205467703-7e194979-e3f7-4a83-ba0c-71a7babcd660.png)

Admin's Dashboard - Shows up after Admin login
![image](https://user-images.githubusercontent.com/111930214/205468251-d5f47a98-b191-45bd-887e-42610b751b72.png)

Professor's Dashboard - Shows up after Professor login
![image](https://user-images.githubusercontent.com/111930214/205468260-cb6ec7d5-4ba3-4ffc-860c-d77629aa364e.png)

Student's Dashboard - Shows up after Student login
![image](https://user-images.githubusercontent.com/111930214/205468381-1b4a90a3-5091-4a36-abb5-ce36ee098af9.png)
