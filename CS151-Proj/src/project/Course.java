package project;

import java.util.ArrayList;
import java.util.TreeMap;

public class Course {
	private String name;					//Course name should be similar to our system, like "CS 151-06"
	private Professor professor;
	private TreeMap<Student, ArrayList<Assignment>> studentBase;
	
	public Course(String name, Professor professor, TreeMap<Student, ArrayList<Assignment>> studentBase) {
		this.name = name;
		this.professor = professor;
		this.studentBase = studentBase;
	}
	
	public Course(String name, Professor professor) {
		this.name = name;
		this.professor = professor;
		this.studentBase = new TreeMap<Student, ArrayList<Assignment>>();
	}
	
	public Course(String name) {
		this.name = name;
		this.professor = null;
		this.studentBase = new TreeMap<Student, ArrayList<Assignment>>();
	}
	
	
	/**
	 * This method is called multiple times by GradeSystem.  
	 */
	public void addAssignment(Student student, Assignment asgn) {
		studentBase.get(student).add(asgn);
	}
	
	
//	/**
//	 * 
//	 * @param student
//	 */
//	public void editAssignment(Student student, Assignment asgn, double newPointsEarned) {
//		
//	}
	

	
	public void addNewStudent(Student student) {
		studentBase.put(student, new ArrayList<Assignment>());
	}
	
	public void addOldStudent(Student student, ArrayList<Assignment> asgn) {
		studentBase.put(student, asgn);
	}
	
	
	public void removeStudent(Student student) {
		studentBase.remove(student);
	}
	
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
	public String getName() {
		return this.name;
	}
	public Professor getProfessor() {
		return this.professor;
	}
	
	
	public char getGrade(Student student) {
		double pointsEarned = 0;
		double pointsTotal = 0;
		
		for (int i = 0; i < studentBase.get(student).size(); i++) {
			pointsEarned += studentBase.get(student).get(i).getPointsEarned();
			pointsTotal += studentBase.get(student).get(i).getPointsTotal();
		}
		
		
		if (pointsTotal == 0 || pointsEarned/pointsTotal < 0.6) {
			return 'F';
		} else if (pointsEarned/pointsTotal < 0.7) {
			return 'D';
		} else if (pointsEarned/pointsTotal < 0.8) {
			return 'C';
		} else if (pointsEarned/pointsTotal < 0.9) {
			return 'B';
		} else {  //Anything above 90%
			return 'A';
		}
	}
	
	
	public ArrayList<Student> getStudents() {
		return new ArrayList<Student>(studentBase.keySet());
	}
}
