package project;

import java.util.ArrayList;
import java.util.TreeMap;
//import java.util.HashSet;

public class GradeSystem {
	private Controller control;
	private TreeMap<String, User> users;		//Key: UserID			Value: User
	
	private TreeMap<String, Course> courses;	//Key: course's name		Value: Course
	private User currentUser;
	
	/*
	 * This is the underlying system that take cares of most of the logic in the system. 
	 * The Controller would call this system's commands in order to get work done. 
	 * 
	 * ================================================================ THINGS TO KEEP IN MIND================================================================
	 * 
	 * 1.) A Course only have 1 Professor. This can be defined, or null. 
	 * 
	 * 2.) Removal of a Student in a Course will destroy all assignments associated with them in that Course. 
	 * 
	 * 3.) Removal of a Professor in a Course will simply set the Course's Professor to null.
	 * 
	 * 4.) Removal of an Admin will have no effect on the grades in the system.
	 * 
	 * 5.) Removal of a Student in the system will remove them from all Courses, past or present. 
	 * 
	 * 6.) Removal of a Professor in the system will set all Courses' Professors they are associated with to null.
	 * 
	 * 7.) Only an Admin can add Users into the system. One is created by default upon system startup. 
	 * 
	 * 8.) Professors and Admins can both add or remove Students from a Course. The difference is that an Admin is not restricted by the Courses
	 * 		they are responsible for (a Course's Professor).
	 * 
	 * 9.) Professors can only edit the points earned for an Assignment. Debatable as to whether they should also be able to edit the total and name. 
	 * 
	 * ========================================================================================================================================================
	 */
	
	public GradeSystem(Controller control) {
		this.control = control;
		users = new TreeMap<String, User>();
		courses = new TreeMap<String, Course>();
	}

	
	// ------------------------------------------------------ LOG IN or LOG OUT PORTION ------------------------------------------------------
	
	/**
	 * Login attempt method, sets the current user to be one with matching credentials and returns true.
	 * If the user does not exist, then it is not set, and false is returned instead. 
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
	 *Logs out current user. Pretty much a guaranteed success operation. 
	 */
	public void logoutAttempt() {
		currentUser = null;
	}
	
	// ------------------------------------------------------ Admin-related Options ------------------------------------------------------
	
		//Generic method. Not sure if it will be used a lot.
		public void addUser(User user) {
			users.put(user.getId(), user);
		}
		
		// Adding Users
		public void addNewStudent(String firstName, String lastName, String id, String password) {
			Student student = new Student(firstName, lastName, id, password);
			users.put(student.getId(), student);
		}
		public void addNewProfessor(String firstName, String lastName, String id, String password) {
			Professor professor = new Professor(firstName, lastName, id, password);
			users.put(professor.getId(), professor);
		}
		public void addNewAdmin(String firstName, String lastName, String id, String password) {
			Admin admin = new Admin(firstName, lastName, id, password);
			users.put(admin.getId(), admin);
		}
		
		
		public void addNewStudent(Student student) {
			users.put(student.getId(), student);
		}
		public void addNewProfessor(Professor professor) {
			users.put(professor.getId(), professor);
		}
		public void addNewAdmin(Admin admin) {
			users.put(admin.getId(), admin);
		}
		
		
		// Adding Courses
		public void addCourse(String name) {
			courses.put(name, new Course(name));
		}
		public void addCourse(Course course) {
			courses.put(course.getName(), course);
		}
		
		
		
	
	// ------------------------------------------------------ Professor-related Options ------------------------------------------------------
	
	/**
	 * The way this works is that this method would be called multiple times for each student.
	 * Proc-ed by the controller, which is proc-ed by the user interface.
	 * 
	 * @param course
	 * @param student
	 * @param name 
	 * @param pointsEarned
	 * @param pointsTotal
	 */
	public void addAssignment(Course course, Student student, String name, double pointsEarned, double pointsTotal) {
		Assignment assignment = new Assignment(name, pointsEarned, pointsTotal);
		course.addAssignment(student, assignment);
	}
	
//	public void editAssignment(Course course, Student student, Assignment asgn) {
//		
//	}
		
	
	// ------------------------------------------------------ Student-related Options ------------------------------------------------------
	
	
	
	
	// ------------------------------------------------------ GETTING STUFF FROM THE SYSTEM ------------------------------------------------------
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public ArrayList<Course> getCourses() {
		return ((Professor)currentUser).getCourses();
	}
	
	
}
