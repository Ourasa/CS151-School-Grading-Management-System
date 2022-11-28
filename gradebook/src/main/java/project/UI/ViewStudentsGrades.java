package project.UI;

import java.awt.Dimension;
import java.awt.Color;
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

public class ViewStudentsGrades extends JScrollPane implements ActionListener {

	UserInterface frame;
	JPanel panel;
	JButton backButton;

	JLabel courseLabel;
	JComboBox<String> courseListBox;
	ArrayList<JComponent> tempComponents;

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
		//this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));
		tempComponents = new ArrayList<>();
		addComponents();

	}
	
	public void addComponents() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1000, 500));
		
		ImageIcon image3 = new ImageIcon("images/Professor2.png");

		JLabel image = new JLabel(image3);
		image.setBounds(500, 20, 500, 500);
		panel.add(image);

		JLabel banner = new JLabel();
		banner.setText("Welcome Professor");
		banner.setBackground(Color.GRAY);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		panel.add(banner);
		
		backButton = new JButton("Back");
		backButton.setBounds(30, 30, 80, 25);
		backButton.addActionListener(this);
		panel.add(backButton);

		courseLabel = new JLabel("Course:");
		courseLabel.setBounds(120, 30, 50, 25);
		panel.add(courseLabel);

		studentName = new JLabel("Student Name");
		studentName.setBounds(30, 60, 150, 25);
		panel.add(studentName);

		studentID = new JLabel("Student ID");
		studentID.setBounds(190, 60, 120, 25);
		panel.add(studentID);

		grade = new JLabel("Grade");
		grade.setBounds(310, 60, 100, 25);
		panel.add(grade);
				
		this.setViewportView(panel);
	}

	public void updateCourseList(int index) {
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
				updateViewUsersScreen(courseListBox.getSelectedIndex());
			}
		});
		panel.add(courseListBox);
	}

	public void updateViewUsersScreen(int selectedIndex) {
		for(JComponent component: this.tempComponents) {
			panel.remove(component);
		}
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

			panel.add(name);
			panel.add(id);
			panel.add(pwd);
			this.tempComponents.add(name);
			this.tempComponents.add(id);
			this.tempComponents.add(pwd);
			i++;
		}
		this.validate();
		this.repaint();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			frame.pageTransition(frame.professorOptionScroll);
		}
	}

}
