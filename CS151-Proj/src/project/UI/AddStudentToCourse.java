package project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import project.Course;
import project.Professor;
import project.Student;
import project.User;

class AddStudentToCourse extends JScrollPane implements ActionListener{
	
	UserInterface frame;
	ArrayList<Course> courses; 
	JComboBox<String> courseComboBox;
	JComboBox<String> studentListComboBox;
	
	JButton adminOptionConfirmBtn;

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
		this.setPreferredSize(new Dimension(400, 275));
		
		courseComboBox = new JComboBox<>();
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(100, 200, 90, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
		
		addCourseTitleLabel = new JLabel("Add Student to Course");
		addCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		addCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		addCourseTitleLabel.setBounds(100, 30, 200, 25);
		this.add(addCourseTitleLabel);
		
		adminOptionConfirmBtn = new JButton();
		adminOptionConfirmBtn.addActionListener(this);
		adminOptionConfirmBtn.setBounds(210, 200, 90, 25);
		adminOptionConfirmBtn.setText("Confirm");
		this.add(adminOptionConfirmBtn);
		
		selectCourseNameLabel = new JLabel("Course:");
		selectCourseNameLabel.setBounds(100, 110, 100, 25);
		this.add(selectCourseNameLabel);
		
		selectStudentLabel = new JLabel("Student:");
		selectStudentLabel.setBounds(100, 70, 100, 25);
		this.add(selectStudentLabel);


	}
	
	public void courseListBox() {
		ArrayList<Course> courses = frame.control.getAllCourses();
		ArrayList<String> coursesNames = new ArrayList<String>();
		
		ArrayList<User> students = frame.control.getUserList();
		ArrayList<String> studentNames = new ArrayList<String>();

		coursesNames.add("Select a Course");

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i) instanceof Student) {
				studentNames.add(students.get(i).getId());
			}
		}
		
		String[] studentBox = new String[studentNames.size()];
		studentBox = studentNames.toArray(studentBox);

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);
		
		studentListComboBox = new JComboBox<>(studentBox);
		studentListComboBox.setBounds(150, 70, 150, 25);
		this.add(studentListComboBox);

		courseComboBox = new JComboBox<>(coursesBox);
		courseComboBox.setBounds(150, 110, 150, 25);
		this.add(courseComboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton) {
			frame.pageTransition(frame.adminOptionScroll);
		}
		
	}

}