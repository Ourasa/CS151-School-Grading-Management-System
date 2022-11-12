package project;

import java.util.TreeMap;
import java.util.Map;

public class Student extends User {
	
	private double gpa;
	private TreeMap<Course, Character> curCourses;
	private TreeMap<Course, Character> pastCourses;
	
	public Student(String firstName, String lastName, String id, String password) {
		super(firstName, lastName, id, password);
		curCourses = new TreeMap<Course, Character>();
		pastCourses = new TreeMap<Course, Character>();
		updateGPA();
	}
	
	public Student(String firstName, String lastName, String id, String password, TreeMap<Course, Character> curCourses, TreeMap<Course, Character> pastCourses) {
		super(firstName, lastName, id, password);

		this.curCourses  = curCourses;
		this.pastCourses = pastCourses;
		updateGPA();
	}
	
	public void updateGPA() {
		double sum = 0;
		double coursesCount = 0;
		
		//Iterates through past courses
		for (Map.Entry<Course, Character> set : pastCourses.entrySet()) {
			sum += set.getValue();
			coursesCount += 1;
		}
		
		//Iterates through current courses
		for (Map.Entry<Course, Character> set : curCourses.entrySet()) {
			sum += set.getValue();
			coursesCount += 1;
		}
		
		gpa = sum/coursesCount;
	}
}
