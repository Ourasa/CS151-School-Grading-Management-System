package com.gradebook.gradebook;

import java.util.TreeMap;
import java.util.Map;

public class Student extends User {
	
	private double gpa;
	private TreeMap<Course, Character> curCourses;		// Key: Course object		Value: Grade for course
	private TreeMap<String, Character> pastCourses;		// Key: Course name			Value: Grade for course

	
	public Student(String firstName, String lastName, String id, String password) {
		super(firstName, lastName, id, password);
		curCourses = new TreeMap<Course, Character>();
		pastCourses = new TreeMap<String, Character>();
		updateGPA();
	}
	
	public Student(String firstName, String lastName, String id, String password, TreeMap<Course, Character> curCourses, TreeMap<String, Character> pastCourses) {
		super(firstName, lastName, id, password);
		this.curCourses  = curCourses;
		this.pastCourses = pastCourses;
		updateGPA();
	}
	
	public Student() {
		super();
	}

	//Updates the student's grade for the specified Course
	public void updateGrade(Course course) {
		char grade = course.getGrade(this);
		curCourses.put(course, grade);
	}
	
	//No clue if this works yet. We will see later on, for sure. 
	public void updateGPA() {
		double sum = 0;
		double coursesCount = 0;
		
		//Iterates through past courses
		for (Map.Entry<String, Character> set : pastCourses.entrySet()) {		
			if (set.getValue() == 'A') {
				sum += 4;
			} else if (set.getValue() == 'B') {
				sum += 3;
			} else if (set.getValue() == 'C') {
				sum += 2;
			} else if (set.getValue() == 'D') {
				sum += 1;
			} else if (set.getValue() == 'F') {
				sum += 0;
			} else if (set.getValue() == 'N') { // 'N' is a "grade" that specifies no assignments were done. Course does not count towards GPA.
				coursesCount -= 1;
			}
			
			coursesCount += 1;
		}
		
		//Iterates through current courses
		for (Map.Entry<Course, Character> set : curCourses.entrySet()) {
			if (set.getValue() == 'A') {
				sum += 4;
			} else if (set.getValue() == 'B') {
				sum += 3;
			} else if (set.getValue() == 'C') {
				sum += 2;
			} else if (set.getValue() == 'D') {
				sum += 1;
			} else if (set.getValue() == 'F') {
				sum += 0;
			} else if (set.getValue() == 'N') { // 'N' is a "grade" that specifies no assignments were done. Course does not count towards GPA.
				coursesCount -= 1;
			}
			
			coursesCount += 1;
		}
		
		if (coursesCount == 0) {
			gpa = 0;
		} else {
			gpa = sum/coursesCount;
		}
	}
	
	public double getGPA() {
		updateGPA();
		return this.gpa;
	}
	
	public void addCurCourse(Course course) {
		curCourses.put(course, 'N');
		updateGrade(course);
		updateGPA();
	}
	
	public void addPastCourse(String courseName, Character grade) {
		pastCourses.put(courseName, grade);

	}
	
	public TreeMap<Course, Character> getCurCourses() {
		return curCourses;
	}
	
	
	public TreeMap<String, Character> getPastCourses() {
		return pastCourses;
	}
	
	public Character getGrade(Course course) {
		return curCourses.get(course);
	}
}
