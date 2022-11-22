package project.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AdminOptionScroll extends JScrollPane implements ActionListener {

	JComboBox<String> adminOptionsBox;
	JButton adminOptionConfirmBtn;
	JButton adminLogoutBtn;
	JLabel adminWelcomeLabel;
	UserInterface frame;

	public AdminOptionScroll(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		String[] adminOptions = { "Add User", "Remove User", "Add Course", "Remove Course", "Set Professor for Course",
				"Remove Professor from Course", "Add Student to Course", "Remove Student from Course",
				"View All Users" };
		adminOptionsBox = new JComboBox<String>(adminOptions);
		adminOptionsBox.setBounds(100, 120, 200, 25);
		this.add(adminOptionsBox);

		adminOptionConfirmBtn = new JButton();
		adminOptionConfirmBtn.addActionListener(this);
		adminOptionConfirmBtn.setBounds(210, 200, 90, 25);
		adminOptionConfirmBtn.setText("Confirm");
		this.add(adminOptionConfirmBtn);

		adminLogoutBtn = new JButton();
		adminLogoutBtn.addActionListener(this);
		adminLogoutBtn.setBounds(100, 200, 90, 25);
		adminLogoutBtn.setText("Logout");
		this.add(adminLogoutBtn);

		adminWelcomeLabel = new JLabel();
		adminWelcomeLabel.setText("Welcome, ----");
		adminWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		adminWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		adminWelcomeLabel.setBounds(90, 50, 225, 25);
		this.add(adminWelcomeLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adminOptionConfirmBtn) {

			if (((String) adminOptionsBox.getSelectedItem()).equals("Add User")) {
				frame.pageTransition(frame.adminAddUser);

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Remove User")) {
				frame.adminRemoveUser.updateAdminRemoveUserScreen();
				frame.pageTransition(frame.adminRemoveUser);

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Add Course")) {
				frame.adminAddCourse.updateAdminAddCourseScreen();
				frame.pageTransition(frame.adminAddCourse);

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Remove Course")) {
				frame.adminRemoveCourse.updateAdminRemoveCourseScreen();
				frame.pageTransition(frame.adminRemoveCourse);

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Set Professor for Course")) {

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Remove Professor from Course")) {

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Add Student to Course")) {
				frame.addStudentToCourse.courseListBox();
				frame.pageTransition(frame.addStudentToCourse);

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("Remove Student from Course")) {

			} else if (((String) adminOptionsBox.getSelectedItem()).equals("View All Users")) {
				frame.adminViewUsers.updateViewUsersScreen();
				frame.pageTransition(frame.adminViewUsers);
			}
		} else {
			frame.adminOptionScroll.adminOptionsBox.setSelectedIndex(0);
			frame.loginScroll= new LoginScrollPane(frame);
			frame.pageTransition(frame.loginScroll);
			frame.control.logoutUser();
			System.out.println("here");

		}
	}
}
