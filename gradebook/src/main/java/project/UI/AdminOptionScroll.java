package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class AdminOptionScroll extends JScrollPane implements ActionListener {

	JComboBox<String> adminOptionsBox;
	JButton adminOptionConfirmBtn;
	JButton adminLogoutBtn;
	JLabel adminWelcomeLabel;
	AutoComplete autoComplete;
	UserInterface frame;

	public AdminOptionScroll(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

	
		String[] adminOptions = { "Add User", "Remove User", "Add Course", "Remove Course", "Set Professor for Course",
				"Remove Professor from Course", "Add Student to Course", "Remove Student from Course",
				"View All Users" , "Generate Save File" , "Load Save File"};
		autoComplete = new AutoComplete(adminOptions);
		this.add(autoComplete);

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
		
		adminWelcomeLabel.setText("Administration");
		adminWelcomeLabel.setBackground(Color.WHITE);
		adminWelcomeLabel.setFont(new Font("Serif", Font.BOLD, 45));
		adminWelcomeLabel.setForeground(Color.BLACK);
		adminWelcomeLabel.setOpaque(true); // to display background of label
		//adminWelcomeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates border for label
		adminWelcomeLabel.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		adminWelcomeLabel.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		//home.setLayout(null); // need a layout manager to adjust sizes
		adminWelcomeLabel.setBounds(0, 0, 1000, 65); // sets x,y position of label w/ dimensions
		this.add(adminWelcomeLabel);	
		ImageIcon image3 = new ImageIcon("images/Administration.jpeg");
		
		JLabel image = new JLabel(image3);
		image.setBounds(0,0,1000,500);
		this.add(image);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adminOptionConfirmBtn) {

			if (((String) autoComplete.getSelectedItem()).equals("Add User")) {
				frame.adminAddUser = new AdminAddUser(frame);
				frame.pageTransition(frame.adminAddUser);

			} else if (((String) autoComplete.getSelectedItem()).equals("Remove User")) {
				frame.adminRemoveUser = new AdminRemoveUser(frame);
				frame.adminRemoveUser.updateAdminRemoveUserScreen();
				frame.pageTransition(frame.adminRemoveUser);

			} else if (((String) autoComplete.getSelectedItem()).equals("Add Course")) {
				frame.adminAddCourse = new AdminAddCourse(frame);
				frame.adminAddCourse.updateAdminAddCourseScreen();
				frame.pageTransition(frame.adminAddCourse);

			} else if (((String) autoComplete.getSelectedItem()).equals("Remove Course")) {
				frame.adminRemoveCourse = new AdminRemoveCourse(frame);
				frame.adminRemoveCourse.updateAdminRemoveCourseScreen();
				frame.pageTransition(frame.adminRemoveCourse);

			} else if (((String) autoComplete.getSelectedItem()).equals("Set Professor for Course")) {
				frame.setProfessorToCourse = new SetProfessorToCourse(frame);
				frame.setProfessorToCourse.courseListBox();
				frame.pageTransition(frame.setProfessorToCourse);
			} else if (((String) autoComplete.getSelectedItem()).equals("Remove Professor from Course")) {

			} else if (((String) autoComplete.getSelectedItem()).equals("Add Student to Course")) {
				frame.addStudentToCourse = new AddStudentToCourse(frame);
				frame.addStudentToCourse.courseListBox();
				frame.pageTransition(frame.addStudentToCourse);
			} else if (((String) autoComplete.getSelectedItem()).equals("Remove Student from Course")) {
				frame.removeStudentFromCourse = new RemoveStudentFromCourse(frame);
				frame.removeStudentFromCourse.courseListBox();
				frame.pageTransition(frame.removeStudentFromCourse);
			} else if (((String) autoComplete.getSelectedItem()).equals("View All Users")) {
				frame.adminViewUsers = new AdminViewUser(frame);
				frame.adminViewUsers.updateViewUsersScreen();
				frame.pageTransition(frame.adminViewUsers);
			} else if (((String) autoComplete.getSelectedItem()).equals("Generate Save File")) {
				String name = frame.control.generateTxtSaveFile();
				JOptionPane.showMessageDialog(this, "Sucessfully generated txt save file: " + name);
			} else if (((String) autoComplete.getSelectedItem()).equals("Load Save File")) {
				frame.adminLoadFile = new AdminLoadFile(frame);
				frame.pageTransition(frame.adminLoadFile);
			}
		} else {
			frame.adminOptionScroll.autoComplete.setSelectedIndex(0);
			frame.loginScroll = new LoginScrollPane(frame);
			frame.pageTransition(frame.loginScroll);
			frame.control.logoutUser();
			// System.out.println("here");
		}
	}
}
