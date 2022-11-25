package com.gradebook.gradebook;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.HashSet;

public class GradeSystem {
	private Controller control;
	private TreeMap<String, User> users; // Key: UserID Value: User
	private TreeMap<String, Course> courses; // Key: course's name Value: Course
	private User currentUser;

	/*
	 * This is the underlying system that take cares of most of the logic in the
	 * system.
	 * The Controller would call this system's commands in order to get work done.
	 * 
	 * ================================================================ THINGS TO
	 * KEEP IN MIND================================================================
	 * 
	 * 1.) A Course only have 1 Professor. This can be defined, or null.
	 * 
	 * 2.) Removal of a Student in a Course will destroy all assignments associated
	 * with them in that Course.
	 * 
	 * 3.) Removal of a Professor in a Course will simply set the Course's Professor
	 * to null.
	 * 
	 * 4.) Removal of an Admin will have no effect on the grades in the system.
	 * 
	 * 5.) Removal of a Student in the system will remove them from all Courses,
	 * past or present.
	 * 
	 * 6.) Removal of a Professor in the system will set all Courses' Professors
	 * they are associated with to null.
	 * 
	 * 7.) Only an Admin can add Users into the system. One is created by default
	 * upon system startup.
	 * 
	 * 8.) Professors and Admins can both add or remove Students from a Course. The
	 * difference is that an Admin is not restricted by the Courses
	 * they are responsible for (a Course's Professor).
	 * 
	 * 9.) Professors can only edit the points earned for an Assignment. Debatable
	 * as to whether they should also be able to edit the total and name.
	 * 
	 * 10.) Every time an assignment is added, the student's grade should be
	 * automatically updated. On top of that, the student's overall GPA should also
	 * be changed.
	 * This also applies when an assignment is edited.
	 * 
	 * 11.) Removal of a Course will cause all Students to be kicked from the
	 * Course.
	 * 
	 * 12.) Admins cannot be removed by themselves if they are the only admin left.
	 * Admins that do remove themselves will be immediately redirected to the login
	 * screen.
	 * 
	 * =============================================================================
	 * ===========================================================================
	 *
	 * ------ Completed ------
	 * - Basic GUI for logging in
	 * - GUI home page for each User type
	 * - Actually logging the user in.
	 * - Preventing non-users from entering and using the system
	 * 
	 *
	 * ------ Work in Progress/To be worked on ------ (X = GUI created, O =
	 * Functioning)
	 * - GUI for each of the specific user options
	 * 
	 * - Admin Options
	 * Adding User to system X
	 * Removing User from system X
	 * 
	 * Adding Course to system X
	 * Removing Course from system
	 * 
	 * Assigning Professor to a Course
	 * Removing Professor from a Course
	 * 
	 * Adding Student to a Course
	 * Removing Student from a Course
	 * 
	 * - Professor Options
	 * Adding Student to a Course
	 * Removing Student from a Course
	 * 
	 * Adding an Assignment (for all Students).
	 * Possible: Editing an Assignment (for one Student)?
	 * Removing an Assignment (for all Students)
	 * 
	 * Viewing each Student in the class, and their grade in said class.
	 * 
	 * - Student Options
	 * Viewing their current and past Courses, and Grades for each. Includes GPA
	 * probs
	 * Printing a transcript of their grades and courses.
	 *
	 * 
	 * ------ Possible future implementations ------
	 * - Making a "Finish Course" option. This would be different from removing a
	 * course, in insert the Course into the pastCourse for each student in that
	 * class.
	 * - Canvas API????
	 * 
	 */

	public GradeSystem(Controller control) {
		this.control = control;
		users = new TreeMap<String, User>();
		courses = new TreeMap<String, Course>();
	}


	// ------------------------------------------------------ LOG IN or LOG OUT
	// PORTION ------------------------------------------------------

	/**
	 * Login attempt method, sets the current user to be one with matching
	 * credentials and returns true.
	 * If the user does not exist, then it is not set, and false is returned
	 * instead.
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean loginAttempt(String id, String password) {
		if (users.containsKey(id) && users.get(id).getPassword().equals(password)) {
			currentUser = users.get(id);
			return true;
		}
		return false;
	}


	/**
	 * Logs out current user. Pretty much a guaranteed success operation.
	 */
	public void logoutAttempt() {
		currentUser = null;
	}


	// ------------------------------------------------------ Admin Options
	// ------------------------------------------------------

	// Generic method. Not sure if it will be used a lot.
	public void addUser(User user) {
		users.put(user.getId(), user);
	}

	// ----------- Adds a User into the system. Generates an ID for them, which is
	// returned (for GUI purposes).-----------
	public String addUser(String type, String firstName, String lastName, String password) {
		String id = idGenerator(firstName.charAt(0), lastName.charAt(0));
		if (type.equals("Admin")) {
			users.put(id, new Admin(firstName, lastName, id, password));
		} else if (type.equals("Professor")) {
			users.put(id, new Professor(firstName, lastName, id, password));
		} else if (type.equals("Student")) {
			users.put(id, new Student(firstName, lastName, id, password));
		}
		return id;
	}
  
  //Exception AddUser Case: loading in a file
	public void addUser(String type, String firstName, String lastName, String id, String password) {
		if (type.equals("Admin")) {
			users.put(id, new Admin(firstName, lastName, id, password));
		} else if (type.equals("Professor")) {
			users.put(id, new Professor(firstName, lastName, id, password));
		} else if (type.equals("Student")) {
			users.put(id, new Student(firstName, lastName, id, password));
		}
	}
  
	// ----------- Removing Users from the system -----------
	// Returns true if completed successfully, return false if failure. 
	public boolean removeUser(String id) {
		if (users.get(id) instanceof Student) {					//Student - Remove them from each course they are a part of, also deleting their assignments. 	
			ArrayList<Course> courses = new ArrayList<Course>( ((Student)users.get(id)).getCurCourses().keySet());		//Current courses
			for (int i = 0; i < courses.size(); i++) {
				courses.get(i).removeStudent((Student)users.get(id));
			}
			
			//No longer needed
//			courses = new ArrayList<Course>( ((Student)users.get(id)).getPastCourses().keySet());		//Removes past courses
//			for (int i = 0; i < courses.size(); i++) {
//				courses.get(i).removeStudent((Student)users.get(id));
//			}
			
		} else if (users.get(id) instanceof Professor) {		//Professor - Remove association with each course they are a part of. 
			ArrayList<Course> courses = ( (Professor)users.get(id) ).getCourses();
			for (int i = 0; i < courses.size(); i++) {
				( (Professor)users.get(id) ).removeCourse(courses.get(i));
			} 
			
		} else { //Admin(s) do not have any particular ties with Students or Professors. HOWEVER, there must at least be one. This is the only means in which removal can fail.
			ArrayList<Admin> admins = getAdminList();
			
			if (admins.size() <= 1) {
				return false;
			}
			
		}
		users.remove(id);		//After necessary operations done, the user is permanently removed from the system. 
		return true;
	}  

	// ----------- Adding Courses to the system -----------
	public void addCourse(String name) { // Occurs when N/a is selected
		courses.put(name, new Course(name));
	}

	public void addCourse(String courseName, String professorID) { // Occurs when a Professor is selected
		courses.put(courseName, new Course(courseName, (Professor) users.get(professorID)));
	}

	public void addCourse(Course course) { // Used in Driver to add in some dummy courses.
		courses.put(course.getName(), course);
	}

	// ----------- Removing an (active) Course from the system -----------
	public void removeCourse(String name) {
		Course course = courses.get(name);
		courses.remove(name);
		course.getProfessor().removeCourse(course);
		ArrayList<Student> students = course.getStudents();

		// For each student, removes the course from their active courses. Also updates
		// their GPA.
		for (int i = 0; i < students.size(); i++) {
			TreeMap<Course, Character> curCourses = students.get(i).getCurCourses();
			curCourses.remove(course);
			students.get(i).updateGPA();
		}
	}

	// ----------- Set Professor for a Course -----------

	public void setProfessorForCourse(String courseName, String profId) {
		Course course = courses.get(courseName);
		Professor prof = (Professor) users.get(profId);

		if (course.getProfessor() == null) { // Case 1: No professor assigned to course yet.
			course.setProfessor(prof);
			prof.addCourse(course);
		} else { // Case 2: Another professor was present.
			course.getProfessor().removeCourse(course);
			course.setProfessor(prof);
		}
	}

	// ----------- Remove Professor from Course -----------
	public void removeProfessorFromCourse(String courseName) {
		Course course = courses.get(courseName);
		course.getProfessor().removeCourse(course);
		course.setProfessor(null);
	}

	// ----------- Add Student to Course -----------
	public boolean addStudentToCourse(String courseName, String studentId) {
		Student student = ((Student) users.get(studentId));
		Course course = courses.get(courseName);

		if (student.getCurCourses().containsKey(course)) { // Student is already in the course. Returns false,
															// indicating failure.
			return false;
		}
		course.addNewStudent(student);
		student.addCurCourse(course);
		student.updateGPA();
		return true;
	}

	// ----------- Remove Student from Course -----------
	public void removeStudentFromCourse(String courseName, String studentId) {
		Student student = ((Student) users.get(studentId));
		Course course = courses.get(courseName);

		student.getCurCourses().remove(course);
		course.removeStudent(student);

	}

	// ------------------------------------------------------ Professor Options
	// ------------------------------------------------------

	// Adds an assignment. Called multiple times by the GUI.
	public void addAssignment(String courseName, String studentId, String name, double pointsEarned,
			double pointsTotal) {
		Course course = courses.get(courseName);
		Student student = (Student) users.get(studentId);
		Assignment assignment = new Assignment(name, pointsEarned, pointsTotal);

		course.addAssignment(student, assignment);
		student.updateGrade(course);
		student.updateGPA();
	}

	// Edits an assignment points earned - Still uncertain as to what should be
	// edit-able or not.
	public void editAssignment(String courseName, String studentId, String asgnName, double newPointsEarned) {
		Course course = courses.get(courseName);
		Student student = (Student) users.get(studentId);
		ArrayList<Assignment> asgn = course.getStudentAssignments(student);

		for (int i = 0; i < asgn.size(); i++) {
			if (asgn.get(i).getName().equals(asgnName)) {
				asgn.get(i).setPointsEarned(newPointsEarned);
				break;
			}
		}

		student.updateGrade(course);
		student.updateGPA();
	}

	// Removes an assignment for all of the Students in a given Course.
	public void removeAssignment(String courseName, String asgnName) {
		Course course = courses.get(courseName);
		course.removeAssignmentAllStudents(asgnName);
	}
	
  // Completes a course, removing it from database, and adding it as history for students
	public void completeCourse(String courseName) {
		Course course = courses.get(courseName);
		
		ArrayList<Student> students = course.getStudents();
		
		for (int i = 0; i < students.size(); i++) {
			students.get(i).addPastCourse(course.getName(), course.getGrade(students.get(i)));
			students.get(i).getCurCourses().remove(course);
		}
		
		if (course.getProfessor() != null) {
			course.getProfessor().removeCourse(course);
		}
		
		courses.remove(course.getName());
	}
	
	
	// ------------------------------------------------------ Student-related
	// Options ------------------------------------------------------
  
	//Print transcripts in txt format. Experimental.

	public void printTranscript() {
		try {
			String fileName = currentUser.getId() + "Transcript.txt";
			File file = new File(fileName);


			int counter = 1;
			while (!file.createNewFile()) { // If a file is already generated, then it makes another one.
				fileName = currentUser.getId() + "Transcript (" + counter + ").txt";
				file = new File(fileName);
				counter++;
			}

			//Option is only accessible by a student.
			Student student = (Student)currentUser;
			FileWriter writer = new FileWriter(file);
			writeStudentTranscript(file, writer, student);
			writer.close();	
			//file.setReadOnly();	

		} catch (IOException e) {
			System.out.println("Error occurred");
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------ GETTING STUFF FROM THE
	// SYSTEM ------------------------------------------------------

	public User getCurrentUser() {
		return currentUser;
	}
	
	
	public ArrayList<User> getUserList() {
		return new ArrayList<User>(users.values());
	}
	
	public ArrayList<Course> getAllCourses() {
		return new ArrayList<Course>(courses.values());
	}
	
	public ArrayList<Course> getProfCourses() {
		return ((Professor) currentUser).getCourses();
	}
  
  public Course getCourse(String in) {
		return courses.get(in);
	}

	public User getUser(String in) {
		return users.get(in);
	}
	
	public ArrayList<Admin> getAdminList() {
		ArrayList<User> userList =  new ArrayList<User>(users.values());
		ArrayList<Admin> admins = new ArrayList<Admin>();
		
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Admin) {
				admins.add((Admin) userList.get(i));
			}
		}
		return admins;
	}

	public ArrayList<Professor> getProfessorList() {
		ArrayList<User> userList =  new ArrayList<User>(users.values());
		ArrayList<Professor> professors = new ArrayList<Professor>();
		
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Professor) {
				professors.add((Professor) userList.get(i));

			}
		}
		return professors;
	}

	
	public ArrayList<Student> getStudentList() {
		ArrayList<User> userList =  new ArrayList<User>(users.values());
		ArrayList<Student> students = new ArrayList<Student>();
		
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Student) {
				students.add((Student) userList.get(i));
			}
		}
		return students;
	}	
	
//	public ArrayList<Student> getCourseStudents(String courseName) {
//		
//	}
	
//	public ArrayList<Course> getAllCourses() {
//		ArrayList<Course> list = new ArrayList<Course>();
//		
//		for (Map.Entry<String, Course> set : courses.entrySet()) {
//			list.add(set.getValue());
//		}
//		
//		return list;
//	}
	
	public void writeStudentTranscript(File file, FileWriter writer, Student student) throws IOException {
		writer.write("Student Name: " + student.getFirstName() + " " + student.getLastName());
		writer.write("\n\nID: " + student.getId());
		writer.write("\n\nGPA: " + student.getGPA());
		
		writer.write("\n\n=======| CURRENT COURSES |==========");
		TreeMap<Course, Character> curCourses = student.getCurCourses();
		for (Map.Entry<Course, Character> set : curCourses.entrySet()) {
			writer.write("\n" + String.format("%-14s", set.getKey().getName()) + "\t\t" + set.getValue());
		}
		
		writer.write("\n\n=======|  PAST COURSES  |==========");
		TreeMap<String, Character> pastCourses = student.getPastCourses();
		for (Map.Entry<String, Character> set : pastCourses.entrySet()) {
			writer.write("\n" + String.format("%-14s", set.getKey()) + "\t\t" + set.getValue());
		}
		
	}
	
	public String idNumGenerator() {
		int[] nums = new int[4];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int)(Math.random() * 10);
		}
		return "" + nums[0] + "" + nums[1] + "" + nums[2] + "" + nums[3];
	}
	
	public String idGenerator(char a, char b) {	
		String id = "" + a + "" + b + "-"  + idNumGenerator();
		while (users.containsKey(id)) {
			id = "" + a + "" + b + "-"  + idNumGenerator();
		}
		return id;
		
	}

	// ------------------------------------------------------ LOADING FILE INTO THE
	// SYSTEM ------------------------------------------------------
  
	public void generateTxtSaveFile() {
		try {
			String fileName = "gradeSystemSaveFile.txt";
			File file = new File(fileName);
			
			//File generation and naming process
			int counter = 1;
			while (!file.createNewFile()) {	//If a file is already generated, then it makes another one. 
				fileName = "gradeSystemSaveFile (" + counter + ").txt";
				file = new File(fileName);
				counter++;
			}
		
			FileWriter writer = new FileWriter(file);
			
			//Writing in the base User information
			ArrayList<User> users = getUserList();
			
			writer.write("StartUsers\n");
			for (int i = 0; i < users.size(); i++) {
				User curUser = users.get(i);
				
				//Write the role of the User
				if (curUser instanceof Admin) {
					writer.write("Admin,");
				} else if (curUser instanceof Professor) {
					writer.write("Professor,");
				} else if (curUser instanceof Student){
					writer.write("Student,");	
				}
				//Write the generic information of each User
				writer.write(curUser.getFirstName() + "," + curUser.getLastName() + "," + curUser.getId() + "," + curUser.getPassword() + "\n");
			}
			writer.write("EndUsers\n");
			
			
			//Writing ALL of Courses information, including students, professors, assignments. 
			ArrayList<Course> allCourses = getAllCourses();
			for (int i = 0; i < allCourses.size(); i++) {
				Course course = allCourses.get(i);
				
				if (course.getProfessor() != null) {
					writer.write("StartCourse\n" 
							+ course.getName() + "," + course.getProfessor().getId() + "\n");	//Course Name and Professor ID
				} else {
					writer.write("StartCourse\n" 
							+ course.getName() + ",null\n");
				}
				
				
//				ArrayList<String> asgnNames = course.getAsgnNameList();
//				
//				for (int j = 0; j < asgnNames.size(); j++) {
//					writer.write(asgnNames.get(j) + ",");
//				}
//				writer.write("\n");
				
				ArrayList<Student> students = course.getStudents();	//Get students of that course
				
				for (int j = 0; j < students.size(); j++) {	
					Student student = students.get(j);
					writer.write("StartCourseStudent\n");
					writer.write(student.getId() + "\n");			//For each student, put their ID first.
					ArrayList<Assignment> assignments = course.getStudentAssignments(student);
					
					//For each assignment, write Assignment name, ptsEarned, ptsTotal
					for (int k = 0; k < assignments.size(); k++) {
						Assignment asgn = assignments.get(k);
						writer.write(asgn.getName() + "," + asgn.getPointsEarned() + "," + asgn.getPointsTotal() + "\n");
					}
					writer.write("EndCourseStudent\n");
				}
				writer.write("EndCourse\n");
			}
			
			writer.write("EndCourseAdditions\n");
			
			//Writing info for each Professor's Courses
			
			//Writing info for each Student's past Courses
			ArrayList<Student> students = getStudentList();
			for (int i = 0; i < students.size(); i++) {
				writer.write("StartPastCourseStudent\n");
				Student student = students.get(i);		
				TreeMap<String, Character> pastCourses = student.getPastCourses();
				writer.write(student.getId() + "\n");
				for (Map.Entry<String, Character> set : pastCourses.entrySet()) {
					writer.write(set.getKey() + "," + set.getValue() + "\n");
				}	
					
				writer.write("EndPastCourseStudent\n");
			}
			
			writer.write("EndFile");
			writer.close();		
		} catch (IOException e) {
			System.out.println("Error during Txt file generation.");
			e.printStackTrace();
		}
	}
	
	public void loadTxtFile(File file) {
		try {
			Scanner scan = new Scanner(file);
			
			if (!scan.nextLine().equals("StartUsers")) {	//First check to ensure format is remotely proper
				scan.close();
				throw new IOException();
			}
			
			String curLine = scan.nextLine();
			String[] lineComp = curLine.split(",");
			
			
			//Adding Users into this system...
			while (!lineComp[0].equals("EndUsers")) {
				addUser(lineComp[0], lineComp[1], lineComp[2], lineComp[3], lineComp[4]);
				curLine = scan.nextLine();
				lineComp = curLine.split(",");
			}
			
			//At this point, lineComp[0] = "EndUsers"
			//System.out.println(lineComp[0]);
			
			curLine = scan.nextLine();		//Should have startCourse. If not, loop below not entered.
			lineComp = curLine.split(",");
			
			while (!(lineComp[0].equals("EndCourseAdditions"))) {
				
				
				curLine = scan.nextLine();		
				lineComp = curLine.split(",");	//Obtains the course name and professor

				Course course;
				
				if (lineComp[1].equals("null")) {	//Adds the course w/ prof into database
					course = new Course(lineComp[0]);
					addCourse(course);
				} else {
					Professor prof = (Professor)users.get(lineComp[1]);
					course = new Course(lineComp[0], prof);
					addCourse(course);
				}
				
				curLine = scan.nextLine();		
				//System.out.println(curLine);	//Should contains StartCourseStudent, if there is a student
						
				while (!lineComp[0].equals("EndCourse")) { //Adds student, then their assignments
					curLine = scan.nextLine();		
					lineComp = curLine.split(",");
					
					Student student = (Student)users.get(lineComp[0]);
					course.addNewStudent(student);
					
					curLine = scan.nextLine();		
					lineComp = curLine.split(",");
					
					while (!lineComp[0].equals("EndCourseStudent")) {
						course.addAssignment(student, new Assignment(lineComp[0], Double.parseDouble(lineComp[1]), Double.parseDouble(lineComp[2])));
						curLine = scan.nextLine();		
						lineComp = curLine.split(",");
					}
					
					//At this point, lineComp[0].equals("EndCourseStudent")
					//System.out.println(curLine);
					
					student.updateGrade(course);
					student.updateGPA();
					
					curLine = scan.nextLine();		
					lineComp = curLine.split(",");
				}
				
				//At this point, lineComp[0].equals("EndCourse")
				//System.out.println(curLine);
				curLine = scan.nextLine();		
				lineComp = curLine.split(",");
			}
			
			
			
			//At this point, lineComp[0].equals("EndCourseAdditions")
			
			curLine = scan.nextLine();		
			lineComp = curLine.split(",");	//If it contains StartPastCourseStudent, load the stuff in. 			
			
			while (!lineComp[0].equals("EndFile")) {
				curLine = scan.nextLine();		
				lineComp = curLine.split(",");
				Student student = (Student)users.get(lineComp[0]);		

				curLine = scan.nextLine();		
				lineComp = curLine.split(",");	//Get First assignment
				
				while (!lineComp[0].equals("EndPastCourseStudent")) {
					student.addPastCourse(lineComp[0], lineComp[1].charAt(0));
					curLine = scan.nextLine();		
					lineComp = curLine.split(",");	//Get past course name and grade
				}
				
				//At this point, lineComp[0].equals("EndPastCourseStudent")
				//System.out.println(curLine);

				student.updateGPA();
				
				curLine = scan.nextLine();		
				lineComp = curLine.split(",");	//Either contains StartPastCourseStudent or EndFile
			}
			
			scan.close(); //End of the file reached! 
			
		} catch (Exception e) {
			System.out.println("Unable to process file. Likely incorrect file format.");
			e.printStackTrace();
			control.setSystem(new GradeSystem(control)); //Clear out this system. 
		}
		
		
	}
	
}
