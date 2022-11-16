package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import project.Admin;
import project.Professor;
import project.Student;
import project.User;

public class LoginScrollPane extends JScrollPane implements ActionListener {
	JPanel loginPanel;
	JTextField loginIdField;
	JPasswordField loginPwdField;
	JButton loginButton;
	JLabel loginIdLabel;
	JLabel loginPwdLabel;
	JLabel loginHeadingLabel;
	JLabel statusLabel;
	UserInterface frame;

	public LoginScrollPane(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		loginIdField = new JTextField();
		loginIdField.setBounds(125, 100, 200, 25);
		this.add(loginIdField);

		loginIdLabel = new JLabel("ID: ");
		loginIdLabel.setBounds(100, 100, 100, 25);
		this.add(loginIdLabel);

		loginPwdField = new JPasswordField();
		loginPwdField.setBounds(125, 140, 200, 25);
		this.add(loginPwdField);

		loginPwdLabel = new JLabel("Password: ");
		loginPwdLabel.setBounds(55, 140, 100, 25);
		this.add(loginPwdLabel);

		loginButton = new JButton();
		loginButton.addActionListener(this);
		loginButton.setText("Login");
		loginButton.setBounds(150, 210, 100, 25);
		this.add(loginButton);

		loginHeadingLabel = new JLabel();
		loginHeadingLabel.setText("Gradebook Login");
		loginHeadingLabel.setHorizontalAlignment(JLabel.CENTER);
		loginHeadingLabel.setBounds(100, 50, 200, 25);
		loginHeadingLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		this.add(loginHeadingLabel);

		statusLabel = new JLabel();
		statusLabel.setBounds(100, 175, 200, 25);
		statusLabel.setForeground(Color.RED);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setText("Incorrect Username or Password");
		statusLabel.setVisible(false);
		this.add(statusLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String pwd = "";
			for (int i = 0; i < loginPwdField.getPassword().length; i++) {
				pwd += loginPwdField.getPassword()[i];
			}

			// Successful Login
			if (frame.control.loginUser(loginIdField.getText(), pwd)) {
				User user = frame.control.getCurrentUser();
				String fName = user.getFirstName();
				String lName = user.getLastName();

				if (user instanceof Admin) {
					frame.adminOptionScroll.adminWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(frame.loginScroll, frame.adminOptionScroll);

				} else if (user instanceof Professor) {
					frame.professorOptionScroll.profWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(frame.loginScroll, frame.professorOptionScroll);

				} else if (user instanceof Student) {
					frame.studentOptionScroll.studentWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(frame.loginScroll, frame.studentOptionScroll);
				}

				frame.loginScroll.statusLabel.setVisible(false);
				frame.loginScroll.loginIdField.setText("");
				frame.loginScroll.loginPwdField.setText("");

				frame.frame.pack();

				// Failed Login
			} else {
				frame.loginScroll.statusLabel.setVisible(true);
			}
		}

		// Admin Logout

		// Professor Logout

		// Student Logout

		else {
			frame.actionPerformed(e);
		}
	}

	private void pageTransition(JScrollPane before, JScrollPane after) {
		after.setVisible(true);
		frame.frame.add(after);
		before.setVisible(false);
		frame.frame.remove(before);
		frame.frame.pack();
	}

}
