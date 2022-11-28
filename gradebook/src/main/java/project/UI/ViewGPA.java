package project.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

import com.gradebook.gradebook.Admin;
import com.gradebook.gradebook.Course;
import com.gradebook.gradebook.Professor;
import com.gradebook.gradebook.Student;
import com.gradebook.gradebook.User;

public class ViewGPA extends JScrollPane implements ActionListener {

	UserInterface frame;
	JButton backButton;

	JLabel overallGPA;
	JLabel gpa;

	JLabel courseName;
	JLabel professor;
	JLabel grade;

	JLabel viewUsersFNameLabel;
	JLabel viewUsersLNameLabel;
	JLabel viewUsersIdLabel;
	JLabel viewUsersPwdLabel;
	JLabel viewUsersTypeLabel;

	public ViewGPA(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 200));
		addComponents();

	}

	public void addComponents() {
		backButton = new JButton("Back");
		backButton.setBounds(30, 30, 80, 25);
		backButton.addActionListener(this);
		this.add(backButton);

		gpa = new JLabel();
		gpa.setBounds(200, 30, 140, 25);
		this.add(gpa);

		overallGPA = new JLabel("Overall GPA:");
		overallGPA.setBounds(120, 30, 140, 25);
		this.add(overallGPA);

		courseName = new JLabel("Course Name:");
		courseName.setBounds(30, 60, 150, 25);
		this.add(courseName);

		professor = new JLabel("Professor:");
		professor.setBounds(160, 60, 120, 25);
		this.add(professor);

		grade = new JLabel("Grade:");
		grade.setBounds(310, 60, 100, 25);
		this.add(grade);
	}

	public void updateViewUsersScreen() {
		// For each User, we give them a NEW row.
		Student currentStudent = (Student) frame.control.getCurrentUser();
		int i = 0;
		for (Course course : currentStudent.getCurCourses().keySet()) {
			JLabel name = new JLabel(course.getName());
			name.setBounds(30, 80 + 20 * i, 140, 25);
			JLabel prof = new JLabel(course.getProfessor().getFirstName() + " " + course.getProfessor().getLastName());
			prof.setBounds(160, 80 + 20 * i, 140, 25);
			currentStudent.updateGrade(course);
			String gr = "";
			gr += currentStudent.getCurCourses().get(course);
			JLabel pwd = new JLabel(gr);
			pwd.setBounds(315, 80 + 20 * i, 100, 25);

			this.add(name);
			this.add(prof);
			this.add(pwd);

			i++;
		}
		for (String course : currentStudent.getPastCourses().keySet()) {
			JLabel name = new JLabel(course);
			name.setBounds(30, 80 + 20 * i, 140, 25);
			String gr = "";
			gr += currentStudent.getPastCourses().get(course);
			JLabel pwd = new JLabel(gr);
			pwd.setBounds(315, 80 + 20 * i, 100, 25);

			this.add(name);
			this.add(pwd);

			i++;
		}
		Double g = currentStudent.getGPA();
		gpa.setText(g.toString());
		this.validate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.pageTransition(frame.studentOptionScroll);

	}

}
