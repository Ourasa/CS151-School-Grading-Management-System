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

public class ViewStudentsGrades extends JScrollPane implements ActionListener {

	UserInterface frame;
	JButton backButton;

	JLabel courseLabel;
	JComboBox<String> courseListBox;

	JLabel studentName;
	JLabel studentID;
	JLabel grade;

	JLabel viewUsersFNameLabel;
	JLabel viewUsersLNameLabel;
	JLabel viewUsersIdLabel;
	JLabel viewUsersPwdLabel;
	JLabel viewUsersTypeLabel;

	public ViewStudentsGrades(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(600, 500));

		backButton = new JButton("Back");
		backButton.setBounds(30, 30, 80, 25);
		backButton.addActionListener(this);
		this.add(backButton);

		courseLabel = new JLabel("Course:");
		courseLabel.setBounds(120, 30, 50, 25);
		this.add(courseLabel);

		studentName = new JLabel("Student Name");
		studentName.setBounds(30, 60, 150, 25);
		this.add(studentName);

		studentID = new JLabel("Student ID");
		studentID.setBounds(190, 60, 120, 25);
		this.add(studentID);

		grade = new JLabel("Grade");
		grade.setBounds(310, 60, 100, 25);
		this.add(grade);

	}

	public void updateCourseList() {
		ArrayList<Course> courses;

		Professor prof = (Professor) frame.control.getCurrentUser();
		courses = prof.getCourses();

		ArrayList<String> coursesNames = new ArrayList<String>();

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);

		courseListBox = new JComboBox<>(coursesBox);
		courseListBox.setBounds(170, 30, 120, 25);
		courseListBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {//
				updateViewUsersScreen();
			}
		});
		this.add(courseListBox);
	}

	public void updateViewUsersScreen() {

		Course course = frame.control.getCourse((String) courseListBox.getSelectedItem());
		// For each User, we give them a NEW row.
		int i = 0;
		for (Student student : course.studentBase.keySet()) {
			JLabel name = new JLabel(student.getFirstName() + " " + student.getLastName());
			name.setBounds(30, 80 + 20 * i, 140, 25);
			JLabel id = new JLabel(student.getId());
			id.setBounds(190, 80 + 20 * i, 100, 25);
			String gr = "";
			gr += course.getGrade(student);
			JLabel pwd = new JLabel(gr);
			pwd.setBounds(310, 80 + 20 * i, 100, 25);

			this.add(name);
			this.add(id);
			this.add(pwd);
			i++;
			// users
		}
		this.validate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
