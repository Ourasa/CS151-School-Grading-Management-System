package project.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

import com.gradebook.gradebook.Course;
import com.gradebook.gradebook.Professor;

public class ViewStudentsGrades extends JScrollPane implements ActionListener{
	
	UserInterface frame;
	JButton backButton;
	
	JLabel courseLabel;
	JComboBox<String> courseListBox;
	
	JLabel studentName;
	JLabel studentID;
	JLabel grade;
	
	public ViewStudentsGrades(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(600, 500));
		
		backButton = new JButton("Back");
		backButton.setBounds(30,30,80,25);
		backButton.addActionListener(this);
		this.add(backButton);
		
		courseLabel = new JLabel("Course:");
		courseLabel.setBounds(120,30,50,25);
		this.add(courseLabel);
		
		studentName = new JLabel("Student Name");
		studentName.setBounds(30,60,150,25);
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
//		updatetList((String) courseListBox.getSelectedItem());
//		courseListBox.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				points.setText("");
//
//				checkSelectedIndex();
//				updatetList(e.getItem().toString());
//			}
//		});
		this.add(courseListBox);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
