package project;

import java.util.ArrayList;
import java.util.TreeMap;

public class Course {
	private String name;
	private Professor professor;
	private TreeMap<Student, ArrayList<Assignment>> studentBase;
	
	public Course(String name, Professor professor, TreeMap<Student, ArrayList<Assignment>> studentBase) {
		this.name = name;
		this.professor = professor;
		this.studentBase = studentBase;
	}
	
	public Course(String name) {
		this.name = name;
		this.professor = null;
		this.studentBase = new TreeMap<Student, ArrayList<Assignment>>();
	}
	
	public void addNewStudent(Student student) {
		studentBase.put(student, new ArrayList<Assignment>());
	}
	
	public void addOldStudent(Student student, ArrayList<Assignment> asgn) {
		studentBase.put(student, asgn);
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
}
