package project;

import java.util.ArrayList;

public class Professor extends User {
	private ArrayList<Course> courses;
	
	public Professor(String firstName, String lastName, String id, String password) {
		super(firstName, lastName, id, password);
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

}
