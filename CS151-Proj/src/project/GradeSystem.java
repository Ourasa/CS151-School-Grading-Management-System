package project;

import java.util.TreeMap;
import java.util.HashSet;

public class GradeSystem {
	private Controller control;
	private TreeMap<String, User> users;		//Key: UserID			Value: User
	
	private TreeMap<String, Course> courses;	//Key: course's name		Value: Course
	private User currentUser;
	
	/*
	 * This is the underlying system that take cares of most of the logic in the system. 
	 * 
	 * The Controller would call this system's commands in order to get work done. 
	 * 
	 */
	
	public GradeSystem(Controller control) {
		this.control = control;
		users = new TreeMap<String, User>();
		courses = new TreeMap<String, Course>();
	}

	
	// ------------------------------------------------------ADDING STUFF TO THE SYSTEM ------------------------------------------------------
	
	//Generic method. Not sure if it will be used a lot.
	public void addUser(User user) {
		users.put(user.getId(), user);
	}
	
	// Users
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
	
	
	// Courses
	public void addCourse(String name) {
		courses.put(name, new Course(name));
	}
	public void addCourse(Course course) {
		courses.put(course.getName(), course);
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
	 * Pretty much a guaranteed success operation. 
	 */
	public void logoutAttempt() {
		currentUser = null;
	}
	
	
	
	// ------------------------------------------------------ GETTING STUFF FROM THE SYSTEM ------------------------------------------------------
	
	
	
	public User getCurrentUser() {
		return currentUser;
	}
}
