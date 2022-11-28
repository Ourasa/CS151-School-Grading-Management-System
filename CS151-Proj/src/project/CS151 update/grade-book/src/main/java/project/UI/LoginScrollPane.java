package project.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.gradebook.gradebook.*;


public class LoginScrollPane extends JScrollPane implements ActionListener {
	JPanel loginPanel;
	JTextField loginIdField;
	JPasswordField loginPwdField;
	JButton loginButton;
	JLabel loginIdLabel;
	JLabel loginPwdLabel;
	JLabel loginHeadingLabel;
	JLabel statusLabel;
	JLabel mewLabel;
	 JLabel label = new JLabel();
	 JLabel rebel = new JLabel();
	UserInterface frame;

	public LoginScrollPane(UserInterface in)  {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		loginIdField = new JTextField();
		loginIdField.setBounds(650, 200, 200, 25);
		this.add(loginIdField);

		loginIdLabel = new JLabel("ID: ");
		loginIdLabel.setBounds(550, 200, 100, 25);
		this.add(loginIdLabel);

		loginPwdField = new JPasswordField();
		loginPwdField.setBounds(650, 250, 200, 25);
		this.add(loginPwdField);

		loginPwdLabel = new JLabel("Password: ");
		loginPwdLabel.setBounds(550, 250, 100, 25);
		this.add(loginPwdLabel);

		loginButton = new JButton();
		loginButton.addActionListener(this);
		loginButton.setText("Login");
		loginButton.setBounds(675, 350, 100, 25);
		this.add(loginButton);

		loginHeadingLabel = new JLabel();
		loginHeadingLabel.setText("User Login");
		loginHeadingLabel.setBackground(Color.BLACK);
		loginHeadingLabel.setHorizontalAlignment(JLabel.CENTER);
		loginHeadingLabel.setBounds(675, 125, 150, 25);
		loginHeadingLabel.setFont(new Font("Serif", Font.BOLD, 18));
		loginHeadingLabel.setOpaque(true); // to display background of label
		loginHeadingLabel.setForeground(Color.WHITE);
		this.add(loginHeadingLabel);

		statusLabel = new JLabel();
		statusLabel.setBounds(650, 300, 200, 25);
		statusLabel.setForeground(Color.RED);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setText("Incorrect Username or Password");
		statusLabel.setVisible(false);
		this.add(statusLabel);

		
		label.setText("GradeBook");
		label.setBackground(Color.RED);
		label.setFont(new Font("Serif", Font.BOLD, 50));
		label.setForeground(Color.WHITE);
		label.setOpaque(true); // to display background of label
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates border for label
		label.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		label.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		//home.setLayout(null); // need a layout manager to adjust sizes
		label.setBounds(0, 0, 1000, 100); // sets x,y position of label w/ dimensions
		this.add(label);		
		
		ImageIcon image2 = new ImageIcon("images/gradebookimage2.jpeg");
		
		rebel = new JLabel(image2);
		rebel.setBounds(0,50,500,500);
		this.add(rebel);
		this.add(rebel);
		
	
		
		
		
		
		
		

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

	}

	private void pageTransition(JScrollPane before, JScrollPane after) {
		after.setVisible(true);
		frame.frame.add(after);
		before.setVisible(false);
		frame.frame.remove(before);
		frame.frame.pack();
	}

}
