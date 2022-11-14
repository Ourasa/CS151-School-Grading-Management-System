package project;

public class Driver {
	
	public static void main(String[] args) {
		User defaultAdmin = new Admin("Default", "Admin", "DA-0000", "Password1");
		User defaultProfessor = new Professor("Default", "Professor", "DP-0000", "Password2");
		User defaultStudent = new Student("Default", "Student", "DS-0000", "Password3");
		
		Controller con = new Controller();
		con.addUser(defaultAdmin);
		con.addUser(defaultProfessor);
		con.addUser(defaultStudent);
		
		Course course1 = new Course("ENGL-01");
		Course course2 = new Course("CS 151-06");
		Course course3 = new Course("MUSC10A-90");
		
		con.addCourse(course1);
		con.addCourse(course2);
		con.addCourse(course3);
	
	}

}
