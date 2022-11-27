package com.gradebook.gradebook;
import javax.swing.UIManager;

public class Driver {
	
	public static void main(String[] args) {
		User defaultAdmin = new Admin("Default", "Admin", "DA-0000", "Password1");
		User defaultProfessor = new Professor("Default", "Professor", "DP-0000", "Password2");
		User defaultStudent1 = new Student("Default", "Student1", "DS-0000", "Password3");
		User defaultStudent2  = new Student("Default", "Student2", "DS-0001", "Password4");
		
		Controller con = new Controller();
		
//		con.addUser(defaultAdmin);
//		con.addUser(defaultProfessor);
		con.system.users.put(defaultProfessor.getId(),defaultProfessor);
//		con.addUser(defaultStudent1);
//		con.addUser(defaultStudent2);
//		
//		Course course1 = new Course("ENGL-01");
//		Course course2 = new Course("CS 151-06");
//		Course course3 = new Course("MUSC10A-90");
//		
//		con.addCourse(course1);
//		con.addCourse(course2);
//		con.addCourse(course3);
//		
////		Testing the file writer. 
//		con.setProfessorForCourse("ENGL-01", "DP-0000");
//		con.setProfessorForCourse("CS 151-06", "DP-0000");
//		con.setProfessorForCourse("MUSC10A-90", "DP-0000");
//		
//		con.addStudentToCourse("ENGL-01", "DS-0000");
//		con.addStudentToCourse("MUSC10A-90", "DS-0000");
//		con.addStudentToCourse("CS 151-06", "DS-0000");
//		
//		con.addStudentToCourse("MUSC10A-90", "DS-0001");
//		con.addStudentToCourse("CS 151-06", "DS-0001");
//		
//		con.addAssignment("ENGL-01", "DS-0000", "English HW", 1, 1);
//		
//		con.addAssignment("CS 151-06", "DS-0000", "CS HW", 3, 5);
//		con.addAssignment("CS 151-06", "DS-0001", "CS HW", 4, 5);
//		
//		con.loginUser("DS-0000", "Password3");		
//		con.completeCourse("ENGL-01");
//		con.printTranscript();
//		
//		con.loginUser("DS-0001", "Password4");
//		con.printTranscript();
		
//		
		con.loadTxtFile("gradeSystemSaveFile.txt");
//		con.generateTxtSaveFile();
//		System.out.println(con.get);
		
		con.generateClassRosterTxt(defaultProfessor.getId());

	}

}
