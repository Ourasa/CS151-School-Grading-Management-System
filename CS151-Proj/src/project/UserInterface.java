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
	 * The order of how commands work goes as follows:
	 * User requests something through UserInterface. This may call a command from Controller.
	 * Controller may call a command from GradeSystem. GradeSystem takes action.  
	 * 
	 * In order to determine what can be shown to the user and what cannot, it will need to know 
	 * what instance the current user is. Thankfully, since only one user can be logged in at any moment,
	 * this class only needs to request to see which one it is without any arguments.
	 * 
	 * ============================================================================================================================== 
	 * IMPORTANT:
	 *  
	 * The biggest catch  is that the only way this class can specify an object for GradeSystem is through Strings.
	 * This is because the goal is to only have a Controller variable in this class. This is why GradeSystem utilizes
	 * TreeMaps, with a String associated with the actual object. 
	 * ============================================================================================================================== 
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
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Professor - Pick an option page
	private JScrollPane profOptionScroll;
	private JPanel profOptionPanel;
	private JComboBox<String> profOptionsBox;
	private JButton profOptionConfirmBtn;
	private JButton profLogoutBtn;
	private JLabel profWelcomeLabel;
	
	//Professor - Add student screen from course
	
	//Professor - Remove student screen from course
	
	//Professor - Add an assignment for all students in a course.
	
	//Professor - Edit an assignment for all students in a course.
	
	//Professor - Remove an assignment for all students in a course.
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Student - Pick an option page
	
	private JScrollPane studentOptionScroll;
	private JPanel studentOptionPanel;
	private JComboBox<String> studentOptionsBox;
	private JButton studentOptionConfirmBtn;
	private JButton studentLogoutBtn;
	private JLabel studentWelcomeLabel;
	
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
		setupProfessorOptionScreen();
		setupStudentOptionScreen();
		
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
		adminOptionsBox.setBounds(100, 120, 200, 25);
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
		profOptionPanel = new JPanel();
		profOptionPanel.setLayout(null);
		profOptionPanel.setPreferredSize(new Dimension(400, 275));
		profOptionScroll = new JScrollPane(profOptionPanel);
		
		String[] profOptions = {"Add Student to Course", "Remove Student from Course", "Add an Assignment", "Edit an Assignment", "Remove an Assignment", "View Students + Grades"};
		profOptionsBox = new JComboBox<String>(profOptions);
		profOptionsBox.setBounds(100, 120, 200, 25);
		profOptionPanel.add(profOptionsBox);
		
		profOptionConfirmBtn = new JButton();
		profOptionConfirmBtn.addActionListener(this);
		profOptionConfirmBtn.setBounds(210, 200, 90, 25);
		profOptionConfirmBtn.setText("Confirm");
		profOptionPanel.add(profOptionConfirmBtn);
		
		profLogoutBtn = new JButton();
		profLogoutBtn.addActionListener(this);
		profLogoutBtn.setBounds(100, 200, 90, 25);
		profLogoutBtn.setText("Logout");
		profOptionPanel.add(profLogoutBtn);
		
		
		profWelcomeLabel = new JLabel();
		profWelcomeLabel.setText("Welcome, ----");
		profWelcomeLabel.setHorizontalAlignment(JLabel.HORIZONTAL);
		profWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		profWelcomeLabel.setBounds(90, 50, 225, 25);
		profOptionPanel.add(profWelcomeLabel);
	}


	public void setupStudentOptionScreen() {
		studentOptionPanel = new JPanel();
		studentOptionPanel.setLayout(null);
		studentOptionPanel.setPreferredSize(new Dimension(400, 275));
		studentOptionScroll = new JScrollPane(studentOptionPanel);
		
		String[] studentOptions = {"View Courses and GPA", "Print Transcript"};
		studentOptionsBox = new JComboBox<String>(studentOptions);
		studentOptionsBox.setBounds(100, 120, 200, 25);
		studentOptionPanel.add(studentOptionsBox);
		
		studentOptionConfirmBtn = new JButton();
		studentOptionConfirmBtn.addActionListener(this);
		studentOptionConfirmBtn.setBounds(210, 200, 90, 25);
		studentOptionConfirmBtn.setText("Confirm");
		studentOptionPanel.add(studentOptionConfirmBtn);
		
		studentLogoutBtn = new JButton();
		studentLogoutBtn.addActionListener(this);
		studentLogoutBtn.setBounds(100, 200, 90, 25);
		studentLogoutBtn.setText("Logout");
		studentOptionPanel.add(studentLogoutBtn);
		
		
		studentWelcomeLabel = new JLabel();
		studentWelcomeLabel.setText("Welcome, ----");
		studentWelcomeLabel.setHorizontalAlignment(JLabel.HORIZONTAL);
		studentWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		studentWelcomeLabel.setBounds(90, 50, 225, 25);
		studentOptionPanel.add(studentWelcomeLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Login Attempt
		if (e.getSource() == loginButton) {
			String pwd = "";
			for (int i = 0; i < loginPwdField.getPassword().length; i++) {
				pwd += loginPwdField.getPassword()[i];
			}
			
			//Successful Login
			if (control.loginUser(loginIdField.getText(), pwd)) {
				User user = control.getCurrentUser();
				String fName = user.getFirstName();
				String lName = user.getLastName();
				
				if (user instanceof Admin) {
					adminWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					adminOptionScroll.setVisible(true);
					frame.add(adminOptionScroll);
					
				} else if (user instanceof Professor) {
					profWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					profOptionScroll.setVisible(true);
					frame.add(profOptionScroll);
				
				} else if (user instanceof Student) {
					studentWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					studentOptionScroll.setVisible(true);
					frame.add(studentOptionScroll);
				}
				
				statusLabel.setVisible(false);
				loginIdField.setText("");
				loginPwdField.setText("");
				loginScroll.setVisible(false);
				
				frame.remove(loginScroll);
				frame.pack();
				
			//Failed Login
			} else {
				statusLabel.setVisible(true);
			}
			
		// Admin Logout
		} else if (e.getSource() == adminLogoutBtn) {
			loginScroll.setVisible(true);
			adminOptionScroll.setVisible(false);
			frame.add(loginScroll);
			frame.remove(adminOptionScroll);
			control.logoutUser();
		// Professor Logout	
		} else if (e.getSource() == profLogoutBtn) {
			loginScroll.setVisible(true);
			profOptionScroll.setVisible(false);
			frame.add(loginScroll);
			frame.remove(profOptionScroll);
			control.logoutUser();
		// Student Logout		
		} else if (e.getSource() == studentLogoutBtn){
			loginScroll.setVisible(true);
			studentOptionScroll.setVisible(false);
			frame.add(loginScroll);
			frame.remove(studentOptionScroll);
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
