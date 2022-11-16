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
		for (Map.Entry<Course, Character> set : pastCourses.entrySet()) {
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
			} else if (set.getValue() == 'N') { // 'N' is a "grade" that specifies no assignments were done. Course does not count towards gpa.
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
			} else if (set.getValue() == 'N') { // 'N' is a "grade" that specifies no assignments were done. Course does not count towards gpa.
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
		return this.gpa;
	}
	
	
	public void addCurCourse(Course course) {
		curCourses.put(course, 'N');
	}
	
	
	public TreeMap<Course, Character> getCurCourses() {
		return curCourses;
	}
	
	
	public TreeMap<Course, Character> getPastCourses() {
		return pastCourses;
	}
	
}
