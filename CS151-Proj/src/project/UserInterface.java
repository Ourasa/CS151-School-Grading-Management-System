package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface implements ActionListener {
	private Controller control;
	
	/*
	 * The guy that the user actually sees. Good idea to use Java swing for this.
	 * 
	 * In order to determine what can be shown to the user and what cannot, it will need to know 
	 * what is 
	 * 
	 * The order of how commands work goes as follows:
	 * User requests something through UserInterface. This may call a command from Controller.
	 * Controller may call a command from GradeSystem. GradeSystem takes action. 
	 * 
	 * 
	 * Information is sent from this to the controller, which is then sent to the grade system.
	 * 
	 */
	
	private JFrame frame;
	private JPanel loginPanel;
	private JTextField idField;
	private JPasswordField pwdField;
	private JButton loginButton;
	private JLabel statusLabel;
	
	
	public UserInterface(Controller control) {
		this.control = control;
		frame = new JFrame();
		
		frame.setSize(425, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Pain System");
		
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(100, 100, 200, 25);
		loginPanel.add(idField);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(100, 150, 200, 25);
		loginPanel.add(pwdField);
		
		
		loginButton = new JButton();
		loginButton.addActionListener(this);
		loginButton.setText("Login");
		loginButton.setBounds(150,200, 100, 25);
		loginPanel.add(loginButton);
		
		statusLabel = new JLabel();
		statusLabel.setBounds(150, 250, 100, 25);
		loginPanel.add(statusLabel);
		
		frame.add(loginPanel);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String pwd = "";
			for (int i = 0; i < pwdField.getPassword().length; i++) {
				pwd += pwdField.getPassword()[i];
			}
			
			if (control.loginUser(idField.getText(), pwd)) {
				statusLabel.setText("Well, it worked!");
			} else {
				statusLabel.setText("F for FAILURE");
			}
		}
	}

	
	/*
	 * List of possible commands it will need:
	 * 
	 * - Get the current user
	 * - Log out the current user
	 * 
	 * Admin 
	 * - Add a new Student, Professor, or Admin
	 * - Remove a Student, Professor, or Admin
	 * - Set/Remove a course's professor
	 * - Add/Remove student from class
	 * 
	 * 
	 * Professor 
	 * - Add an assignment
	 * - Remove an assignment
	 * - Edit an assignment
	 * - Add/Remove student from class
	 * 
	 * 
	 * Student 
	 * - View assignments of current courses
	 * - Print Transcript
	 * 
	 */
}
