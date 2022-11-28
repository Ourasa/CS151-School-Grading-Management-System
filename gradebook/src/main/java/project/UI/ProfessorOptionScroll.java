package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
		this.setPreferredSize(new Dimension(1000, 500));

		String[] profOptions = { "Add Student to Course", "Remove Student from Course", "Add an Assignment",
				"Edit an Assignment", "Grade an Assignment", "View Students + Grades" };
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

		ImageIcon image3 = new ImageIcon("images/Professor2.png");

		JLabel image = new JLabel(image3);
		image.setBounds(500, 20, 500, 500);
		this.add(image);

		JLabel banner = new JLabel();
		banner.setText("Welcome Professor");
		banner.setBackground(Color.GRAY);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		// banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates
		// border for label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		// home.setLayout(null); // need a layout manager to adjust sizes
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		this.add(banner);
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

			} else if (((String) profOptionsBox.getSelectedItem()).equals("Grade an Assignment")) {
				frame.gradeAssignment = new GradeAssignment(frame);
				frame.gradeAssignment.updateCourseList();
				frame.pageTransition(frame.gradeAssignment);
			} else if (((String) profOptionsBox.getSelectedItem()).equals("View Students + Grades")) {
				frame.viewGrades = new ViewStudentsGrades(frame);
				frame.viewGrades.updateCourseList(0);
				frame.viewGrades.updateViewUsersScreen(0);
				frame.pageTransition(frame.viewGrades);
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
