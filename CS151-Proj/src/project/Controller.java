package project;

import java.util.ArrayList;

public class Controller {
	private UserInterface ui;
	private GradeSystem system;
	
	/*
	 * Inspired by MVC.
	 * Idea is that the UserInterface and GradeSystem interact through this class medium.
	 * 
	 * Predictably, UserInterface is often the one the initiates contact, since that is what the user is interacting with directly. 
	 */
	
	public Controller() {
		system = new GradeSystem(this);
		ui = new UserInterface(this);
	}

	// LOGGING IN USER
	// If such a user does not exists, returns false;
	public boolean loginUser(String id, String password) {
		return system.loginAttempt(id, password);
	}
	
	//LOGGING OUT USER
	public void logoutUser() {
		system.logoutAttempt();
	}
	
	// ADDING A USER - MAINLY FOR DRIVER USE
	public void addUser(User user) {
		system.addUser(user);
	}
	
	// ----------------------------- ADMIN OPTIONS -----------------------------
	
	// ADDING A USER. Returns the randomly generated ID String made for the User.
	public String addUser(String type, String firstName, String lastName, String password) {
		return system.addUser(type, firstName, lastName, password);	
	}
	
	//REMOVING A USER. Returns whether operation was a success or not. Failure may occur for Admin removal attempt, with only 1 Admin left. 
	public boolean removeUser(String id) {
		return system.removeUser(id);
	}
	
	// ADD A COURSE
	public void addCourse(Course course) {
		system.addCourse(course);
	}	
	public void addCourse(String courseName, String professorID) {
		system.addCourse(courseName, professorID);
	}
	
	//REMOVE A COURSE
	public void removeCourse(String name) {
		system.removeCourse(name);
	}
	
	//SET COURSE PROFESSOR
	public void setProfessorForCourse(String courseName, String professorId) {
		system.setProfessorForCourse(courseName, professorId);
	}
	
	// REMOVE COURSE PROFESSOR
	public void removeProfessorFromCourse(String courseName) {
		system.removeProfessorFromCourse(courseName);
	}
	
	// GET CURRENT USER
	public User getCurrentUser() {
		return system.getCurrentUser();
	}
	
	// GET USER LIST
	public ArrayList<User> getUserList() {
		return system.getUserList();
	}
	
	// GET ALL COURSES
	public ArrayList<Course> getAllCourses() {
		return system.getAllCourses();
	}
	
}
