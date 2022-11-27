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
public class GradeAssignment extends JScrollPane implements ActionListener {

	UserInterface frame;
	JComboBox courseListBox;
	AutoComplete assignmentListBox;
	AutoComplete studentListBox;

	JButton confirmButton;
	JButton next;
	JButton back;

	JButton cancelButton;
	JTextField assignmentName;
	JTextField points;
	JPasswordField addUserPwdField;
	JLabel pageTitle;
	JLabel selectCourseLabel;
	JLabel changeAssignmentNameLabel;
	JLabel assignmentNameLabel;
	JLabel pointsWorthLabel;
	JLabel statusLabel;
	JLabel totalPoints;

	public GradeAssignment(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		pageTitle = new JLabel("Grade Assignment");
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

		changeAssignmentNameLabel = new JLabel("Student:");
		changeAssignmentNameLabel.setBounds(30, 120, 70, 25);
		this.add(changeAssignmentNameLabel);

		studentListBox = new AutoComplete(new String[0]);
		studentListBox.setBounds(120, 120, 100, 25);
		this.add(studentListBox);

		next = new JButton("Next");
		next.setBounds(230, 120, 100, 25);
		next.addActionListener(this);
		this.add(next);

		back = new JButton("Back");
		back.setBounds(310, 120, 100, 25);
		back.addActionListener(this);
		back.setVisible(false);
		this.add(back);

		pointsWorthLabel = new JLabel("Grade:");
		pointsWorthLabel.setBounds(30, 150, 90, 25);
		this.add(pointsWorthLabel);

		points = new JTextField("");
		points.setBounds(120, 150, 30, 25);
		this.add(points);

		totalPoints = new JLabel("/");
		totalPoints.setBounds(150, 150, 100, 25);
		this.add(totalPoints);

		confirmButton = new JButton("Save");
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

		courseListBox = new JComboBox<>(coursesBox);
		courseListBox.setBounds(120, 60, 120, 25);
		updatetList((String) courseListBox.getSelectedItem());
		courseListBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				points.setText("");

				checkSelectedIndex();
				updatetList(e.getItem().toString());
			}
		});
		this.add(courseListBox);

	}

	public void updatetList(String cours) {
		final Course course = frame.control.getCourse(cours);
		ArrayList<String> assignmentNames = new ArrayList<String>(course.getAsgnNameList());
		ArrayList<String> studentNames = new ArrayList<String>();

		for (Student student : course.studentBase.keySet()) {
			studentNames.add(student.getId());
		}

		String[] assignmentBox = new String[assignmentNames.size()];
		assignmentBox = assignmentNames.toArray(assignmentBox);

		String[] studentBox = new String[studentNames.size()];
		studentBox = studentNames.toArray(studentBox);

		this.remove(studentListBox);
		studentListBox = new AutoComplete(studentBox);
		studentListBox.setBounds(120, 120, 100, 25);
		studentListBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				points.setText("");

				checkSelectedIndex();
			}
		});
		this.add(studentListBox);

		this.remove(assignmentListBox);
		assignmentListBox = new AutoComplete(assignmentBox);
		assignmentListBox.setBounds(120, 90, 100, 25);
		assignmentListBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				points.setText("");

				totalPoints.setText("/" + course.getAsgnPtsTotal((String) assignmentListBox.getSelectedItem()));
			}
		});
		this.add(assignmentListBox);

		totalPoints.setText("/" + course.getAsgnPtsTotal((String) assignmentListBox.getSelectedItem()));
		points.setText("");

		this.validate();
		this.repaint();

	}

	public void checkSelectedIndex() {
		if (studentListBox.getSelectedIndex() + 1 == studentListBox.options.length) {
			next.setVisible(false);
		}
		if (studentListBox.getSelectedIndex() > 0) {
			back.setVisible(true);
		}
		if (studentListBox.getSelectedIndex() == 0) {
			back.setVisible(false);
		}
		if (studentListBox.getSelectedIndex() + 1 < studentListBox.options.length) {
			next.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			points.setText("");

			studentListBox.setSelectedIndex(studentListBox.getSelectedIndex() + 1);
			checkSelectedIndex();
		}
		if (e.getSource() == back) {
			points.setText("");

			studentListBox.setSelectedIndex(studentListBox.getSelectedIndex() - 1);
			checkSelectedIndex();
		}
		if (e.getSource() == confirmButton) {
			String studentID = studentListBox.getSelectedItem().toString();

			Course course = frame.control.getCourse((String) courseListBox.getSelectedItem());
			Student stud = new Student();
			for (Student student : course.studentBase.keySet()) {
				if (student.getId() == studentID) {
					stud = student;
				}
			}
			for (Assignment assignment : course.studentBase.get(stud)) {

				if (assignment.getName().equals((String) assignmentListBox.getSelectedItem())) {
					assignment.setPointsEarned(Double.parseDouble(points.getText()));
				}
			}
			points.setText("");

		}
		if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.professorOptionScroll);
		}
	}

}
