package com.gradebook.gradebook;

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Course implements Comparable<Course> {
	private String name; // Course name should be similar to our system, like "CS 151-06"
	private Professor professor;
	public TreeMap<Student, ArrayList<Assignment>> studentBase;
	private TreeSet<String> asgnNameList; // This is needed because students may have different assignments.

	public Course(String name, Professor professor, TreeMap<Student, ArrayList<Assignment>> studentBase) {
		this.name = name;
		this.professor = professor;
		professor.addCourse(this);
		this.studentBase = studentBase;
		this.asgnNameList = new TreeSet<String>();
	}

	public Course(String name, Professor professor) {
		this.name = name;
		this.professor = professor;
		professor.addCourse(this);
		this.studentBase = new TreeMap<Student, ArrayList<Assignment>>();
		this.asgnNameList = new TreeSet<String>();
	}

	public Course(String name) {
		this.name = name;
		this.professor = null;
		this.studentBase = new TreeMap<Student, ArrayList<Assignment>>();
		this.asgnNameList = new TreeSet<String>();
	}

	/**
	 * This method is called multiple times by GradeSystem. This is so each student
	 * gets the Assignment.
	 */
	public void addAssignment(Student student, Assignment asgn) {
		studentBase.get(student).add(asgn);
		asgnNameList.add(asgn.getName());
	}

	/**
	 * If an assignment is removed, I presume that it will be for each student...
	 * However, for the sake of ease of use later on, this remove an assignment
	 * for a the designated student and assignment name.
	 * 
	 * If assignment is not found, nothing is removed.
	 */
	public void removeAssignment(Student student, String asgnName) {
		ArrayList<Assignment> asgn = studentBase.get(student);

		for (int i = 0; i < asgn.size(); i++) {
			if (asgn.get(i).getName().equals(asgnName)) {
				asgn.remove(i);
				break;
			}
		}
	}

	/**
	 * Removes an assignment given the name for ALL students in the class.
	 * Then, removes that assignment from the set recording the names of all
	 * assignments added in so far
	 */
	public void removeAssignmentAllStudents(String asgnName) {
		ArrayList<Student> students = new ArrayList<Student>(studentBase.keySet());
		for (int i = 0; i < students.size(); i++) {
			removeAssignment(students.get(i), asgnName);
		}
		asgnNameList.remove(asgnName);
	}

	public boolean addNewStudent(Student student) {
		if (!studentBase.containsKey(student)) {
			studentBase.put(student, new ArrayList<Assignment>());
			return false;
		} else {
			return true;
		}
	}

	public void addOldStudent(Student student, ArrayList<Assignment> asgn) {
		studentBase.put(student, asgn);
	}

	public boolean removeStudent(Student student) {
		if (!studentBase.containsKey(student)) {
			return false;
		} else {
			studentBase.remove(student);
			return true;
		}
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

		if (pointsTotal == 0) { // 'N' is a "grade" that specifies no assignments were done. Course does not
								// count towards gpa.
			return 'N';
		} else if (pointsEarned / pointsTotal < 0.6) {
			return 'F';
		} else if (pointsEarned / pointsTotal < 0.7) {
			return 'D';
		} else if (pointsEarned / pointsTotal < 0.8) {
			return 'C';
		} else if (pointsEarned / pointsTotal < 0.9) {
			return 'B';
		} else { // Anything above 90%
			return 'A';
		}
	}

	public ArrayList<Student> getStudents() {
		return new ArrayList<Student>(studentBase.keySet());
	}

	public ArrayList<Assignment> getStudentAssignments(Student student) {
		return studentBase.get(student);
	}

	public ArrayList<String> getAsgnNameList() {
		return new ArrayList<String>(asgnNameList);
	}

	@Override
	public int compareTo(Course other) {
		return this.name.compareTo(other.name);
	}

}
