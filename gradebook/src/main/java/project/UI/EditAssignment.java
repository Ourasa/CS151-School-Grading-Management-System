package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.gradebook.gradebook.Assignment;
import com.gradebook.gradebook.Course;
import com.gradebook.gradebook.Professor;
import com.gradebook.gradebook.Student;

@SuppressWarnings("serial")
public class EditAssignment extends JScrollPane implements ActionListener {

	UserInterface frame;
	JComboBox courseListBox;
	AutoComplete assignmentListBox;

	JButton save1;
	JButton save2;

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
		points.setBounds(120, 150, 50, 25);
		this.add(points);

		save1 = new JButton("Save");
		save1.setBounds(230, 120, 100, 25);
		save1.addActionListener(this);
		this.add(save1);

		save2 = new JButton("Save");
		save2.setBounds(180, 150, 100, 25);
		save2.addActionListener(this);
		this.add(save2);

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

		courseListBox = new JComboBox<>(coursesBox);
		courseListBox.setBounds(120, 60, 120, 25);
		updateAssignmentList((String) courseListBox.getSelectedItem());
		courseListBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				updateAssignmentList(e.getItem().toString());
			}
		});
		this.add(courseListBox);

	}

	public void updateAssignmentList(String cours) {
		Course course = frame.control.getCourse(cours);
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

		String course = (String) courseListBox.getSelectedItem();
		Course c = frame.control.getCourse(course);

		if (e.getSource() == save1 && !newName.getText().isEmpty()) {
			String newAssignmentName = newName.getText();
			String oldAssignmentName = (String) assignmentListBox.getSelectedItem();

			for (Student student : c.getStudents()) {
				for (Assignment assignment : c.studentBase.get(student)) {
					if (assignment.getName().equals(oldAssignmentName)) {
						assignment.setName(newAssignmentName);
					}
				}
			}

			c.asgnNameList.remove(oldAssignmentName);
			c.asgnNameList.add(newAssignmentName);

			Assignment temp = c.assignments.get(oldAssignmentName);
			temp.setName(newAssignmentName);
			c.assignments.remove(oldAssignmentName);
			c.assignments.put(newAssignmentName, temp);

			newName.setText("");
			points.setText("");

			frame.editAssignment = new EditAssignment(frame);
			frame.editAssignment.updateCourseList();
			frame.pageTransition(frame.editAssignment);

		}
		if (e.getSource() == save2 && !points.getText().isEmpty()) {
			c.assignments.get((String) assignmentListBox.getSelectedItem())
					.setPointsTotal(Integer.parseInt(points.getText()));

			for (Student student : c.getStudents()) {
				for (Assignment assignment : c.studentBase.get(student)) {
					if (assignment.getName() == assignmentListBox.getSelectedItem()) {
						assignment.setPointsTotal(Integer.parseInt(points.getText()));
					}
				}
			}
			points.setText("");
			frame.editAssignment = new EditAssignment(frame);
			frame.editAssignment.updateCourseList();
			frame.pageTransition(frame.editAssignment);
		}
		if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.professorOptionScroll);
		}
	}

}
