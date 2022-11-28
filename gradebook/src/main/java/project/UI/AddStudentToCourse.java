package project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.gradebook.gradebook.*;

class AddStudentToCourse extends JScrollPane implements ActionListener {

	UserInterface frame;
	ArrayList<Course> courses;
	AutoComplete courseComboBox;
	AutoComplete studentListComboBox;

	JButton confirmButton;

	JButton addStudentToCourseButton;
	JButton cancelButton;
	JTextField addCourseNameField;
	JLabel addCourseTitleLabel;
	JLabel selectCourseNameLabel;
	JLabel selectStudentLabel;

	public AddStudentToCourse(UserInterface in) {
		frame = in;
		courses = frame.control.getAllCourses();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		cancelButton = new JButton("Back");
		cancelButton.setBounds(100, 200, 90, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);

		addCourseTitleLabel = new JLabel("Add Student to Course");
		addCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		addCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		addCourseTitleLabel.setBounds(100, 30, 200, 25);
		this.add(addCourseTitleLabel);

		confirmButton = new JButton();
		confirmButton.addActionListener(this);
		confirmButton.setBounds(210, 200, 90, 25);
		confirmButton.setText("Confirm");
		this.add(confirmButton);

		selectCourseNameLabel = new JLabel("Course:");
		selectCourseNameLabel.setBounds(100, 110, 100, 25);
		this.add(selectCourseNameLabel);

		selectStudentLabel = new JLabel("Student:");
		selectStudentLabel.setBounds(100, 70, 100, 25);
		this.add(selectStudentLabel);

	}

	public void courseListBox() {
		ArrayList<Course> courses;

		if (frame.control.getCurrentUser() instanceof Professor) {
			Professor prof = (Professor) frame.control.getCurrentUser();
			courses = prof.getCourses();
		} else {
			courses = frame.control.getAllCourses();
		}

		ArrayList<String> coursesNames = new ArrayList<String>();

		ArrayList<User> students = frame.control.getUserList();
		ArrayList<String> studentNames = new ArrayList<String>();

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i) instanceof Student) {
				studentNames.add(students.get(i).getId());
			}
		}

		String[] studentBox = new String[studentNames.size()];
		studentBox = studentNames.toArray(studentBox);

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);

		studentListComboBox = new AutoComplete(studentBox);
		studentListComboBox.setBounds(150, 70, 150, 25);
		this.add(studentListComboBox);

		courseComboBox = new AutoComplete(coursesBox);
		courseComboBox.setBounds(150, 110, 150, 25);
		this.add(courseComboBox);

		ImageIcon image3 = new ImageIcon("images/Professor2.png");

		JLabel image = new JLabel(image3);
		image.setBounds(500, 20, 500, 500);
		this.add(image);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			String course = (String) courseComboBox.getSelectedItem();
			String studentId = (String) studentListComboBox.getSelectedItem();
			// User student = frame.control.system.getUser((String)
			// studentListComboBox.getSelectedItem());
			if (frame.control.addStudentToCourse(course, studentId)) {
				JOptionPane.showMessageDialog(this,
						"Successfully added student: " + studentId + ", to course " + course);
			} else {
				JOptionPane.showMessageDialog(this, "Student is already enrolled in the course.");
			}
		}
		if (e.getSource() == cancelButton) {
			if (frame.control.getCurrentUser() instanceof Professor) {
				frame.pageTransition(frame.professorOptionScroll);
			} else {
				frame.pageTransition(frame.adminOptionScroll);
			}
		}
	}

}