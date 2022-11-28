package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	JPanel panel;
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
		//this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));
		addComponents();

	}

	public void addComponents() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1000, 500));
		
		backButton = new JButton("Back");
		backButton.setBounds(30, 30, 80, 25);
		backButton.addActionListener(this);
		panel.add(backButton);

		gpa = new JLabel();
		gpa.setBounds(200, 30, 140, 25);
		panel.add(gpa);

		overallGPA = new JLabel("Overall GPA:");
		overallGPA.setBounds(120, 30, 140, 25);
		panel.add(overallGPA);

		courseName = new JLabel("Course Name:");
		courseName.setBounds(30, 60, 150, 25);
		panel.add(courseName);

		professor = new JLabel("Professor:");
		professor.setBounds(160, 60, 120, 25);
		panel.add(professor);

		grade = new JLabel("Grade:");
		grade.setBounds(310, 60, 100, 25);
		panel.add(grade);
		
		this.setViewportView(panel);
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

			panel.add(name);
			panel.add(prof);
			panel.add(pwd);

			i++;
		}
		for (String course : currentStudent.getPastCourses().keySet()) {
			JLabel name = new JLabel(course);
			name.setBounds(30, 80 + 20 * i, 140, 25);
			String gr = "";
			gr += currentStudent.getPastCourses().get(course);
			JLabel pwd = new JLabel(gr);
			pwd.setBounds(315, 80 + 20 * i, 100, 25);

			panel.add(name);
			panel.add(pwd);

			i++;
		}
		Double g = currentStudent.getGPA();
		gpa.setText(g.toString());
		panel.validate();
		panel.repaint();
		
		JLabel banner = new JLabel();
		banner.setText("Welcome Student");
		banner.setBackground(Color.GREEN);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		panel.add(banner);

		ImageIcon image3 = new ImageIcon("images/student.jpeg");

		JLabel image = new JLabel(image3);
		image.setBounds(500, 20, 500, 500);
		panel.add(image);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.pageTransition(frame.studentOptionScroll);

	}

}
