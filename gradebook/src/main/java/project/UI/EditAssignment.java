package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

public class EditAssignment extends JScrollPane implements ActionListener {

	UserInterface frame;
	AutoComplete courseListBox;
	AutoComplete assignmentListBox;
	JButton confirmButton;
	JButton confirmCourseButton;

	JButton cancelButton;
	JTextField assignmentName;
	JTextField points;
	JTextField newName;
	JPasswordField addUserPwdField;
	JLabel pageTitle;
	JLabel selectCourseLabel;
	JLabel changeAssignmentNameLabel;
	JLabel assignmentNameLabel;
	JLabel pointsWorthLabel;
	JLabel statusLabel;
	JLabel instruction;

	public EditAssignment(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		pageTitle = new JLabel("Edit Assignment");
		pageTitle.setHorizontalAlignment(JLabel.CENTER);
		pageTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		pageTitle.setBounds(110, 30, 200, 25);
		this.add(pageTitle);

		selectCourseLabel = new JLabel("Course:");
		selectCourseLabel.setBounds(30, 60, 50, 25);
		this.add(selectCourseLabel);

		confirmCourseButton = new JButton("Confirm");
		confirmCourseButton.setBounds(220, 60, 80, 25);
		confirmCourseButton.addActionListener(this);
		this.add(confirmCourseButton);

		assignmentNameLabel = new JLabel("Assignment:");
		assignmentNameLabel.setBounds(30, 90, 80, 25);
		this.add(assignmentNameLabel);

		assignmentListBox = new AutoComplete(new String[0]);
		assignmentListBox.setBounds(120, 90, 100, 25);
		this.add(assignmentListBox);

		changeAssignmentNameLabel = new JLabel("Edit name:");
		changeAssignmentNameLabel.setBounds(30, 120, 70, 25);
		this.add(changeAssignmentNameLabel);

		newName = new JTextField("");
		newName.setBounds(120, 120, 100, 25);
		this.add(newName);

		pointsWorthLabel = new JLabel("Edit points:");
		pointsWorthLabel.setBounds(30, 150, 90, 25);
		this.add(pointsWorthLabel);

		points = new JTextField("");
		points.setBounds(120, 150, 30, 25);
		this.add(points);

		instruction = new JLabel("**If no changes needed, leave blank**");
		instruction.setBounds(30, 180, 250, 25);
		this.add(instruction);

		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(200, 220, 100, 25);
		confirmButton.addActionListener(this);
		this.add(confirmButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(80, 220, 100, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);

		statusLabel = new JLabel();
		statusLabel.setBounds(160, 150, 200, 25);
		statusLabel.setForeground(Color.RED);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setText("Please use an integer");
		statusLabel.setVisible(false);
		this.add(statusLabel);

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

		courseListBox = new AutoComplete(coursesBox);
		courseListBox.setBounds(120, 60, 100, 25);
		this.add(courseListBox);

	}

	public void updateAssignmentList() {
		Course course = frame.control.getCourse((String) courseListBox.getSelectedItem());
		ArrayList<String> assignmentNames = new ArrayList<String>(course.getAsgnNameList());

		String[] assignmentBox = new String[assignmentNames.size()];
		assignmentBox = assignmentNames.toArray(assignmentBox);

		this.remove(assignmentListBox);
		assignmentListBox = new AutoComplete(assignmentBox);
		assignmentListBox.setBounds(120, 90, 100, 25);
		this.add(assignmentListBox);
		this.validate();
		this.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmCourseButton) {
			updateAssignmentList();
			return;
		}
		if (e.getSource() == confirmButton) {

			if (points.getText().isEmpty() || points.getText().matches("[0-9]+")) {
				String course = (String) courseListBox.getSelectedItem();
				Course c = frame.control.getCourse(course);

				if (newName.getText() != "") {
					c.asgnNameList.remove(assignmentListBox.getSelectedItem());
					c.asgnNameList.add(newName.getText());
				}

				for (Student student : c.getStudents()) {
					for (Assignment assignment : c.studentBase.get(student)) {
						if (assignment.getName() == assignmentListBox.getSelectedItem()) {
							if (newName.getText() != "") {
								assignment.setName(newName.getText());
							}
							if (!points.getText().isEmpty()) {
								assignment.setPointsTotal(Integer.parseInt(points.getText()));
							}
						}
					}
				}
				JOptionPane.showMessageDialog(this, "Assignment was successfully edited");

				updateAssignmentList();
				frame.editAssignment = new EditAssignment(frame);
				frame.editAssignment.updateCourseList();
				frame.pageTransition(frame.editAssignment);
			} else {
				statusLabel.setVisible(true);
			}

		}
		if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.professorOptionScroll);
		}
	}

}
