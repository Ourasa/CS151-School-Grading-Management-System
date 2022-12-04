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

class ProfessorCompleteCourse extends JScrollPane implements ActionListener {

	UserInterface frame;
	ArrayList<Course> courses;
	AutoComplete courseComboBox;

	JButton confirmButton;
	JButton cancelButton;
	JLabel completeCourseTitleLabel;
	JLabel selectCourseNameLabel;

	public ProfessorCompleteCourse(UserInterface in) {
		frame = in;
		courses = frame.control.getAllCourses();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		cancelButton = new JButton("Back");
		cancelButton.setBounds(100, 200, 90, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);

		completeCourseTitleLabel = new JLabel("Complete a Course");
		completeCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		completeCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		completeCourseTitleLabel.setBounds(100, 30, 200, 25);
		this.add(completeCourseTitleLabel);

		confirmButton = new JButton();
		confirmButton.addActionListener(this);
		confirmButton.setBounds(210, 200, 90, 25);
		confirmButton.setText("Confirm");
		this.add(confirmButton);

		selectCourseNameLabel = new JLabel("Course:");
		selectCourseNameLabel.setBounds(100, 110, 100, 25);
		this.add(selectCourseNameLabel);

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

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);

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
			if (courseComboBox.getItemCount() != 0) {
				String course = (String) courseComboBox.getSelectedItem();
				frame.control.completeCourse(course);
				JOptionPane.showMessageDialog(this,
						"Successfully completed Course: " + course);
				frame.pageTransition(frame.professorOptionScroll);
			} else {
				JOptionPane.showMessageDialog(this,
						"Failed: There are no courses to complete.");
			}
			
		} else if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.professorOptionScroll);
		}
	}

}