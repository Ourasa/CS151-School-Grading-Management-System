package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import project.User;

class AdminAddUser extends JScrollPane implements ActionListener {

	UserInterface frame;
	JScrollPane addUserScroll;
	JPanel addUserPanel;
	JComboBox<String> addUserTypeBox;
	JButton addUserConfirmBtn;
	JButton addUserCancelBtn;
	JTextField addUserFNameField;
	JTextField addUserLNameField;
	JPasswordField addUserPwdField;
	JLabel addUserTitle;
	JLabel addUserTypeLabel;
	JLabel addFNameLabel;
	JLabel addLNameLabel;
	JLabel addPwdLabel;

	public AdminAddUser(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		String[] types = { "Admin", "Professor", "Student" };
		addUserTypeBox = new JComboBox<String>(types);
		addUserTypeBox.setBounds(100, 80, 100, 25);
		this.add(addUserTypeBox);

		addUserFNameField = new JTextField();
		addUserFNameField.setBounds(100, 110, 200, 25);
		this.add(addUserFNameField);

		addUserLNameField = new JTextField();
		addUserLNameField.setBounds(100, 140, 200, 25);
		this.add(addUserLNameField);

		addUserPwdField = new JPasswordField();
		addUserPwdField.setBounds(100, 170, 200, 25);
		this.add(addUserPwdField);

		addUserConfirmBtn = new JButton("Confirm");
		addUserConfirmBtn.setBounds(200, 220, 100, 25);
		addUserConfirmBtn.addActionListener(this);
		this.add(addUserConfirmBtn);

		addUserCancelBtn = new JButton("Cancel");
		addUserCancelBtn.setBounds(80, 220, 100, 25);
		addUserCancelBtn.addActionListener(this);
		this.add(addUserCancelBtn);

		addUserTitle = new JLabel("Add User");
		addUserTitle.setHorizontalAlignment(JLabel.CENTER);
		addUserTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		addUserTitle.setBounds(100, 30, 200, 25);
		this.add(addUserTitle);

		addUserTypeLabel = new JLabel("User Type");
		addUserTypeLabel.setBounds(30, 80, 100, 25);
		this.add(addUserTypeLabel);

		addFNameLabel = new JLabel("First Name:");
		addFNameLabel.setBounds(30, 110, 100, 25);
		this.add(addFNameLabel);

		addLNameLabel = new JLabel("Last Name:");
		addLNameLabel.setBounds(30, 140, 100, 25);
		this.add(addLNameLabel);

		addPwdLabel = new JLabel("Password:");
		addPwdLabel.setBounds(30, 170, 100, 25);
		this.add(addPwdLabel);

		addUserScroll = new JScrollPane();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addUserCancelBtn) {
			addUserTypeBox.setSelectedIndex(0);
			addUserFNameField.setText("");
			addUserLNameField.setText("");
			addUserPwdField.setText("");
			frame.pageTransition(frame.adminOptionScroll);

		} else if (e.getSource() == addUserConfirmBtn) {
			String generatedId = frame.control.addUser((String) addUserTypeBox.getSelectedItem(),
					addUserFNameField.getText(), addUserLNameField.getText(), frame.getPwd(addUserPwdField));
			JOptionPane.showMessageDialog(this, "Success. Username generated is: " + generatedId);

			addUserTypeBox.setSelectedIndex(0);
			addUserFNameField.setText("");
			addUserLNameField.setText("");
			addUserPwdField.setText("");

			frame.pageTransition(frame.adminOptionScroll);
		}

	}
}

class AdminRemoveUser extends JScrollPane implements ActionListener {

	UserInterface frame;
	JComboBox<String> removeUserListBox;
	JButton removeUserConfirmBtn;
	JButton removeUserCancelBtn;
	JLabel removeUserLabel;
	JLabel removeUserDeniedLabel;

	public AdminRemoveUser(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		removeUserConfirmBtn = new JButton("Confirm");
		removeUserConfirmBtn.setBounds(210, 200, 90, 25);
		removeUserConfirmBtn.addActionListener(this);
		this.add(removeUserConfirmBtn);

		removeUserCancelBtn = new JButton("Cancel");
		removeUserCancelBtn.setBounds(100, 200, 90, 25);
		removeUserCancelBtn.addActionListener(this);
		this.add(removeUserCancelBtn);

		removeUserLabel = new JLabel("Remove User");
		removeUserLabel.setHorizontalAlignment(JLabel.CENTER);
		removeUserLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		removeUserLabel.setBounds(100, 30, 200, 25);
		this.add(removeUserLabel);

		removeUserDeniedLabel = new JLabel("Denied: Must have minimum 1 Admin in System.");
		removeUserDeniedLabel.setBounds(100, 150, 200, 25);
		removeUserDeniedLabel.setForeground(Color.RED);
		removeUserDeniedLabel.setVisible(false);
		this.add(removeUserDeniedLabel);
	}

	public void updateAdminRemoveUserScreen() {
		ArrayList<User> users = frame.control.getUserList();
		String[] userIds = new String[users.size() + 1];

		userIds[0] = "Select a User";

		for (int i = 0; i < users.size(); i++) {
			userIds[i + 1] = users.get(i).getId();
		}

		removeUserListBox = new JComboBox<String>(userIds);
		removeUserListBox.setBounds(100, 100, 200, 25);
		add(removeUserListBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Admin cancels removing a user

		if (e.getSource() == removeUserCancelBtn) {
			frame.pageTransition(frame.adminOptionScroll);

		} else if (e.getSource() == removeUserConfirmBtn) {

			if (removeUserListBox.getSelectedIndex() == 0) {
				removeUserDeniedLabel.setText("Denied: Please Select a User"); // failure due to no selection being
																				// done.
				removeUserDeniedLabel.setVisible(true);

			} else if (!frame.control.removeUser((String) removeUserListBox.getSelectedItem())) { // method returns
																									// false on failure
																									// due to only
																									// having 1 Admin.
				removeUserDeniedLabel.setText("<html>Denied: Must have minimum 1 Admin in System.</html>");
				removeUserDeniedLabel.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(this, "Success. User removed: " + removeUserListBox.getSelectedItem());
				removeUserDeniedLabel.setVisible(false);
				removeUserListBox.setSelectedIndex(0);
				frame.pageTransition(frame.adminOptionScroll);
			}

		}
	}

}
