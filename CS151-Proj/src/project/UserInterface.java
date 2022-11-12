package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	 * 
	 * Information about the scrollable!
	 * - To make something scrollable, use JScrollPane.
	 * 
	 * - JScrollPane will automatically make something have scroll bars as need, but
	 * 		ONLY IF the "preferred size" of the panel, container, etc. is smaller than what is shown.
	 * 		This means you will need to use (container name).setPreferredSize(Dimension)
	 * 
	 * - Also, in the case a page is made to be scrollable, you only need to add the JScrollPane into the Frame.
	 */

	private JFrame frame;
	
	//Login screen components
	private JScrollPane loginScroll;
	private JPanel loginPanel;
	private JTextField loginIdField;
	private JPasswordField loginPwdField;
	private JButton loginButton;
	private JLabel loginIdLabel;
	private JLabel loginPwdLabel;
	private JLabel loginHeadingLabel;
	private JLabel statusLabel;
	
	//Admin - Pick an option page : Either buttons, or a drop down box and a confirm button.
	private JScrollPane adminOptionScroll;
	private JPanel adminOptionPanel;
	private JComboBox<String> adminOptionsBox;
	private JButton adminOptionConfirmBtn;
	private JButton adminLogoutBtn;
	private JLabel adminWelcomeLabel;
	
	//Admin - Add a user to system : Should have text fields to fill in the information of the new user
	
	//Admin - Remove a user from system : Probably a drop down box containing IDs? Then a confirm button to complete removal... I think
	
	//Admin - Add a course into system: TextFields, a drop down box for Professor, and confirm button. 
	
	//Admin - Remove a course from system: A drop down box, and a confirm button. 
	
	//Admin - Assign Professor to a Course : Likely 2 drop down boxes, one for courses, and another for Professor. Then a confirm button.
	
	//Admin - Remove Professor from Course : One drop down box, and a confirm button. (Only 1 professor is in a Course)
	
	//Admin - Add Student to a Course : 2 drop down boxes, one for courses, one for student. Then a confirm button.
	
	//Admin - Remove Student from a Course: 2 drop down boxes, one for the course, and one for its current student. Then a confirm button. 
	
	
	
	//Professor - Pick an option page
	
	//Professor - Add student screen from course
	
	//Professor - Remove student screen from course
	
	//Professor - Add an assignment for all students in a course.
	
	//Professor - Edit an assignment for all students in a course.
	
	//Professor - Remove an assignment for all students in a course.
	
	
	
	//Student - Pick an option page
	
	//Student - View all Courses + Grades
	
	//Student - View all Assignments (of a current course)
	
	//Student - Print transcript
	
	public UserInterface(Controller control) {
		this.control = control;
		frame = new JFrame();
		frame.setSize(400, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		frame.setTitle("Pain Grading System");
		
		
		setupLoginScreen();
		setupAdminOptionScreen();
		
		
		frame.setVisible(true);
	}
	
	public void setupLoginScreen() {
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setPreferredSize(new Dimension(400, 275));
		loginScroll = new JScrollPane(loginPanel);
		
		loginIdField = new JTextField();
		loginIdField.setBounds(125, 100, 200, 25);
		loginPanel.add(loginIdField);
		
		loginIdLabel = new JLabel("ID: ");
		loginIdLabel.setBounds(100, 100, 100, 25);
		loginPanel.add(loginIdLabel);
		
		loginPwdField = new JPasswordField();
		loginPwdField.setBounds(125, 140, 200, 25);
		loginPanel.add(loginPwdField);
		
		loginPwdLabel = new JLabel("Password: ");
		loginPwdLabel.setBounds(55, 140, 100, 25);
		loginPanel.add(loginPwdLabel);
		
		loginButton = new JButton();
		loginButton.addActionListener(this);
		loginButton.setText("Login");
		loginButton.setBounds(150, 210, 100, 25);
		loginPanel.add(loginButton);
		
		loginHeadingLabel = new JLabel();
		loginHeadingLabel.setText("Gradebook Login");
		loginHeadingLabel.setHorizontalAlignment(JLabel.CENTER);
		loginHeadingLabel.setBounds(100, 50, 200, 25);
		loginHeadingLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		loginPanel.add(loginHeadingLabel);
		
		statusLabel = new JLabel();
		statusLabel.setBounds(100, 175, 200, 25);
		statusLabel.setForeground(Color.RED);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setText("Incorrect Username or Password");
		statusLabel.setVisible(false);
		loginPanel.add(statusLabel);
		
		frame.add(loginScroll);
		frame.pack();
	}
	
	
	public void setupAdminOptionScreen() {
		adminOptionPanel = new JPanel();
		adminOptionPanel.setLayout(null);
		adminOptionPanel.setPreferredSize(new Dimension(400, 275));
		adminOptionScroll = new JScrollPane(adminOptionPanel);
		
		String[] adminOptions = {"Add User", "Remove User", "Add Course", "Remove Course" , "Set Professor for Course", "Remove Professor from Course", "Add Student to Course", "Remove Student from Course"};
		adminOptionsBox = new JComboBox<String>(adminOptions);
		adminOptionsBox.setBounds(100, 140, 200, 25);
		adminOptionPanel.add(adminOptionsBox);
		
		adminOptionConfirmBtn = new JButton();
		adminOptionConfirmBtn.addActionListener(this);
		adminOptionConfirmBtn.setBounds(210, 200, 90, 25);
		adminOptionConfirmBtn.setText("Confirm");
		adminOptionPanel.add(adminOptionConfirmBtn);
		
		adminLogoutBtn = new JButton();
		adminLogoutBtn.addActionListener(this);
		adminLogoutBtn.setBounds(100, 200, 90, 25);
		adminLogoutBtn.setText("Logout");
		adminOptionPanel.add(adminLogoutBtn);
		
		
		adminWelcomeLabel = new JLabel();
		adminWelcomeLabel.setText("Welcome, ----");
		adminWelcomeLabel.setHorizontalAlignment(JLabel.HORIZONTAL);
		adminWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		adminWelcomeLabel.setBounds(90, 50, 225, 25);
		adminOptionPanel.add(adminWelcomeLabel);
		
		
		//frame.add(adminOptionScroll);
		//frame.pack();
	}
	

	public void setupProfessorOptionScreen() {
		
	}


	public void setupStudentOptionScreen() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String pwd = "";
			for (int i = 0; i < loginPwdField.getPassword().length; i++) {
				pwd += loginPwdField.getPassword()[i];
			}
			
			if (control.loginUser(loginIdField.getText(), pwd)) {
				User user = control.getCurrentUser();
				String fName = user.getFirstName();
				String lName = user.getLastName();
				adminWelcomeLabel.setText("Welcome, " + fName + " " + lName);
				
				statusLabel.setVisible(false);
				loginIdField.setText("");
				loginPwdField.setText("");
				
				adminOptionScroll.setVisible(true);
				loginScroll.setVisible(false);
				
				frame.add(adminOptionScroll);
				frame.remove(loginScroll);
				frame.pack();
				//statusLabel.setText("Well, it worked!");
			} else {
				statusLabel.setVisible(true);
			}
			
		} else if (e.getSource() == adminLogoutBtn) {
			loginScroll.setVisible(true);
			adminOptionScroll.setVisible(false);
			frame.add(loginScroll);
			frame.remove(adminOptionScroll);
			control.logoutUser();
			
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
