package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.gradebook.gradebook.Assignment;
import com.gradebook.gradebook.Course;
import com.gradebook.gradebook.Professor;
import com.gradebook.gradebook.Student;

public class AddAssignment extends JScrollPane implements ActionListener {

	UserInterface frame;
	AutoComplete addUserTypeBox;
	JButton confirmButton;
	JButton cancelButton;
	JTextField assignmentName;
	JTextField points;
	JPasswordField addUserPwdField;
	JLabel pageTitle;
	JLabel selectCourseLabel;
	JLabel assignmentNameLabel;
	JLabel pointsWorthLabel;
	JLabel statusLabel;

	public AddAssignment(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		assignmentName = new JTextField();
		assignmentName.setBounds(160, 110, 200, 25);
		this.add(assignmentName);

		assignmentNameLabel = new JLabel("Assignment Name:");
		assignmentNameLabel.setBounds(30, 110, 200, 25);
		this.add(assignmentNameLabel);

		points = new JTextField();
		points.setBounds(160, 140, 30, 25);
		this.add(points);

		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(200, 220, 100, 25);
		confirmButton.addActionListener(this);
		this.add(confirmButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(80, 220, 100, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);

		pageTitle = new JLabel("Add Assignment");
		pageTitle.setHorizontalAlignment(JLabel.CENTER);
		pageTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		pageTitle.setBounds(110, 30, 200, 25);
		this.add(pageTitle);

		selectCourseLabel = new JLabel("Select Course");
		selectCourseLabel.setBounds(30, 80, 100, 25);
		this.add(selectCourseLabel);

		pointsWorthLabel = new JLabel("Total Points:");
		pointsWorthLabel.setBounds(30, 140, 100, 25);
		this.add(pointsWorthLabel);

		statusLabel = new JLabel();
		statusLabel.setBounds(100, 175, 200, 25);
		statusLabel.setForeground(Color.RED);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setText("Points can only be an integer");
		statusLabel.setVisible(false);
		this.add(statusLabel);
		
		ImageIcon image3 = new ImageIcon("images/Professor2.png");
		
		JLabel image = new JLabel(image3);
		image.setBounds(500,20,500,500);
		this.add(image);
		
		JLabel banner = new JLabel();
		banner.setText("Welcome Professor");
		banner.setBackground(Color.GRAY);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		//banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates border for label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		//home.setLayout(null); // need a layout manager to adjust sizes
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		this.add(banner);	
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

		addUserTypeBox = new AutoComplete(coursesBox);
		addUserTypeBox.setBounds(160, 80, 150, 25);
		this.add(addUserTypeBox);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			if (points.getText().matches("[0-9]+")) {
				String course = (String) addUserTypeBox.getSelectedItem();

				Course c = frame.control.getCourse(course);
				c.asgnNameList.add(assignmentName.getText());
				c.assignments.put(assignmentName.getText(),
						new Assignment(assignmentName.getText(), 0, Integer.parseInt(points.getText())));
				for (Student student : c.getStudents()) {
					c.studentBase.get(student)
							.add(new Assignment(assignmentName.getText(), 0, Integer.parseInt(points.getText())));
				}
				JOptionPane.showMessageDialog(this,
						"Assignment: " + assignmentName.getText() + ", was successfully added to " + course);
				points.setText("");
				assignmentName.setText("");
			} else {
				statusLabel.setVisible(true);
			}
		}
		frame.pageTransition(frame.professorOptionScroll);
	}

}
