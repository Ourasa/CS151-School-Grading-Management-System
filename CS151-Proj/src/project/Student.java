package project;

import java.util.HashMap;

public class Student extends User {
	
	private double gpa;
	private HashMap<Course, Character> pastCourses;
	
	public Student(String firstName, String lastName, String id, String password) {
		super(firstName, lastName, id, password);
	}

}
