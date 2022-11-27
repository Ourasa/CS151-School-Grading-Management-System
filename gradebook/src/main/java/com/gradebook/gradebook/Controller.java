package com.gradebook.gradebook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import project.UI.*;

public class Controller {
	private UserInterface ui;
	public GradeSystem system;

	/*
	 * Inspired by MVC.
	 * Idea is that the UserInterface and GradeSystem interact through this class
	 * medium.
	 * 
	 * Predictably, UserInterface is often the one the initiates contact, since that
	 * is what the user is interacting with directly.
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

	// ADDING A USER - Returns the randomly generated ID String made for the User.
	public String addUser(String type, String firstName, String lastName, String password) {
		return system.addUser(type, firstName, lastName, password);
	}

	// REMOVING A USER - Returns boolean indication if operation is successful.
	// Failure may occur for Admin removal attempt, with only 1 Admin left.
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

	// REMOVE A COURSE
	public void removeCourse(String name) {
		system.removeCourse(name);
	}

	// SET COURSE PROFESSOR
	public void setProfessorForCourse(String courseName, String professorId) {
		system.setProfessorForCourse(courseName, professorId);
	}

	// REMOVE COURSE PROFESSOR
	public void removeProfessorFromCourse(String courseName) {
		system.removeProfessorFromCourse(courseName);
	}

	// ADD STUDENT TO COURSE - Returns boolean indicating if operation is success.
	// Failure may occur if student is already in that course.
	public boolean addStudentToCourse(String courseName, String studentId) {
		return system.addStudentToCourse(courseName, studentId);
	}

	// REMOVE STUDENT FROM COURSE
	public void removeStudentFromCourse(String courseName, String studentId) {
		system.removeStudentFromCourse(courseName, studentId);
	}

	// Special: VIEW ALL USERS - Handled by GUI.

	// Special: VIEW ALL COURSES - Likely to be handled by GUI.
	
	//GENERATES A TXT DETAILING ALL STUDENTS IN THEIR COURSES + GRADES
	public void generateClassRosterTxt() {
		system.generateClassRosterTxt();
	}
	
	//GENERATES A TXT DETAILING ALL STUDENTS IN THEIR COURSES + GRADES (specific prof)
	public void generateClassRosterTxt(String profId) {
		system.generateClassRosterTxt(profId);
	}
	
	// ----------------------------- PROFESSOR OPTIONS -----------------------------

	// ADD STUDENT TO COURSE - Use addStudentToCourse from Admin.

	// REMOVE STUDENT FROM COURSE - Use removeStudentFromCourse from Admin.

	// ADD ASSIGNEMNT
	public void addAssignment(String courseName, String studentID, Assignment in) {
		system.addAssignment(courseName, studentID, in);
	}

	// EDIT ASSIGNMENT - For now, only edits the points earned. Not sure just
	// exactly how much we want to edit.
	public void editAssignment(String courseName, String studentId, String asgnName, double newPointsEarned) {
		system.editAssignment(courseName, studentId, asgnName, newPointsEarned);
	}

	// REMOVE ASSIGNMENT
	public void removeAssignment(String courseName, String asgnName) {
		system.removeAssignment(courseName, asgnName);
	}

  //COMPLETE COURSE
	public void completeCourse(String courseName) {
		system.completeCourse(courseName);
	}

	// VIEW STUDENTS + GRADES - Handled by the GUI after using getters from the
	// backend??

	// ----------------------------- STUDENT OPTIONS -----------------------------

	// Prints a txt file containing courses and past grades. WARNING: EXPERIMENTAL
	public void printTranscript() {
		system.printTranscript();
	}

	// ----------------------------- MISC. OPTIONS -----------------------------

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
	
	public Course getCourse(String in) {
		return system.getCourse(in);
	}
	
	// Saves the information of the gradebook into a txt file. 
	public void generateTxtSaveFile() {
		system.generateTxtSaveFile();
	}
	
	//Reads and loads a gradebook. To be completed
	public void loadTxtFile(String fileName) {
		this.system = new GradeSystem(this);
		File file = new File(fileName);
		system.loadTxtFile(file);
	}
	
	public void setSystem(GradeSystem system) {
		this.system = system;
	}
  
}
