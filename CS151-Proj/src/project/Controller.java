package project;

import java.util.ArrayList;

import project.UI.UserInterface;

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

	// Uses information to return a User in the database.
	// If such a user does not exists, returns null;
	public boolean loginUser(String id, String password) {
		return system.loginAttempt(id, password);
	}
	
	public void logoutUser() {
		system.logoutAttempt();
	}
	
	public void addUser(User user) {
		system.addUser(user);
	}
	
	public User getCurrentUser() {
		return system.getCurrentUser();
	}
	
	public ArrayList<User> getUserList() {
		return system.getUserList();
	}
	
	public ArrayList<Course> getAllCourses() {
		return system.getAllCourses();
	}
	
	
	public void addNewStudent(String firstName, String lastName, String id, String password) {
		system.addNewStudent(firstName, lastName, id, password);
	}
	public void addNewProfessor(String firstName, String lastName, String id, String password) {
		system.addNewProfessor(firstName, lastName, id, password);
	}
	public void addNewAdmin(String firstName, String lastName, String id, String password) {
		system.addNewAdmin(firstName, lastName, id, password);
	}
	
	
	public void addCourse(Course course) {
		system.addCourse(course);
	}
	public void addCourse(String courseName, String professorID) {
		system.addCourse(courseName, professorID);
	}
}
