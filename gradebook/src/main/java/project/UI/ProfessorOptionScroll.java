package project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProfessorOptionScroll extends JScrollPane implements ActionListener {
	JComboBox<String> courseBox;
	JComboBox<String> profOptionsBox;

	JButton profOptionConfirmBtn;
	JButton profLogoutBtn;
	JLabel profWelcomeLabel;
	UserInterface frame;

	public ProfessorOptionScroll(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		String[] profOptions = { "Add Student to Course", "Remove Student from Course", "Add an Assignment",
				"Edit an Assignment", "Remove an Assignment", "View Students + Grades" };
		profOptionsBox = new JComboBox<String>(profOptions);
		profOptionsBox.setBounds(100, 110, 200, 25);
		this.add(profOptionsBox);

		profOptionConfirmBtn = new JButton();
		profOptionConfirmBtn.addActionListener(this);
		profOptionConfirmBtn.setBounds(210, 200, 90, 25);
		profOptionConfirmBtn.setText("Confirm");
		this.add(profOptionConfirmBtn);

		profLogoutBtn = new JButton();
		profLogoutBtn.addActionListener(this);
		profLogoutBtn.setBounds(100, 200, 90, 25);
		profLogoutBtn.setText("Logout");
		this.add(profLogoutBtn);

		profWelcomeLabel = new JLabel();
		profWelcomeLabel.setText("Welcome, ----");
		profWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		profWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		profWelcomeLabel.setBounds(90, 50, 225, 25);
		this.add(profWelcomeLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == profOptionConfirmBtn) {

			if (((String) profOptionsBox.getSelectedItem()).equals("Add Student to Course")) {
				frame.addStudentToCourse = new AddStudentToCourse(frame);
				frame.addStudentToCourse.courseListBox();
				frame.pageTransition(frame.addStudentToCourse);

			} else if (((String) profOptionsBox.getSelectedItem()).equals("Remove Student from Course")) {
				frame.removeStudentFromCourse = new RemoveStudentFromCourse(frame);
				frame.removeStudentFromCourse.courseListBox();
				frame.pageTransition(frame.removeStudentFromCourse);

			} else if (((String) profOptionsBox.getSelectedItem()).equals("Add an Assignment")) {
				frame.addAssignment = new AddAssignment(frame);
				frame.addAssignment.updateCourseList();
				frame.pageTransition(frame.addAssignment);
			} else if (((String) profOptionsBox.getSelectedItem()).equals("Edit an Assignment")) {
				frame.editAssignment = new EditAssignment(frame);
				frame.editAssignment.updateCourseList();
				frame.pageTransition(frame.editAssignment);

			} else if (((String) profOptionsBox.getSelectedItem()).equals("Remove an Assignment")) {

			} else if (((String) profOptionsBox.getSelectedItem()).equals("View Students + Grades")) {

			}
			// Professor cancels adding a student
		} else if (e.getSource() == frame.professorOptionScroll.profLogoutBtn) {
			frame.professorOptionScroll.profOptionsBox.setSelectedIndex(0);
			frame.loginScroll = new LoginScrollPane(frame);
			frame.pageTransition(frame.loginScroll);
			frame.control.logoutUser();

		}
	}

}
