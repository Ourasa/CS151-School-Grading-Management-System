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
		frame.actionPerformed(e);
	}

}
