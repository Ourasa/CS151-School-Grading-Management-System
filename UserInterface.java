package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

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
	
	//Admin - Add a user to system : Should have text fields to fill in the information of the new user. Dropbox for type of User, likely.
	private JScrollPane addUserScroll;
	private JPanel addUserPanel;
	private JComboBox<String> addUserTypeBox;
	private JButton addUserConfirmBtn;
	private JButton addUserCancelBtn;
	private JTextField addUserFNameField;
	private JTextField addUserLNameField;
	private JPasswordField addUserPwdField;
	
	private JLabel addUserTitle;
	private JLabel addUserTypeLabel;
	private JLabel addFNameLabel;
	private JLabel addLNameLabel;
	private JLabel addPwdLabel;
	
	//Admin - Remove a user from system : Probably a drop down box containing IDs? Then a confirm button to complete removal... I think
	private JScrollPane removeUserScroll;
	private JPanel removeUserPanel;
	private JComboBox<String> removeUserListBox;
	private JButton removeUserConfirmBtn;
	private JButton removeUserCancelBtn;
	private JLabel removeUserLabel;
	private JLabel removeUserDeniedLabel;
	
	//Admin - Add a course into system: TextFields, a drop down box for Professor, and confirm button. 
	
	//Admin - Remove a course from system: A drop down box, and a confirm button. 
	
	//Admin - Assign Professor to a Course : Likely 2 drop down boxes, one for courses, and another for Professor. Then a confirm button.
	
	//Admin - Remove Professor from Course : One drop down box, and a confirm button. (Only 1 professor is in a Course)
	
	//Admin - Add Student to a Course : 2 drop down boxes, one for courses, one for student. Then a confirm button.
	
	//Admin - Remove Student from a Course: 2 drop down boxes, one for the course, and one for its current student. Then a confirm button. 
	
	//Admin - View all Users in the system
	private JScrollPane viewUsersScroll;
	private JPanel viewUsersPanel;
	private JLabel viewUsersFNameLabel;
	private JLabel viewUsersLNameLabel;
	private JLabel viewUsersIdLabel;
	private JLabel viewUsersPwdLabel;
	private JLabel viewUsersTypeLabel;
	private JButton viewUsersExitBtn;
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Professor - Pick an option page
	private JScrollPane profOptionScroll;
	private JPanel profOptionPanel;
	private JComboBox<String> profOptionsBox;
	private JButton profOptionConfirmBtn;
	private JButton profLogoutBtn;
	private JLabel profWelcomeLabel;
	
	//Professor - Add student screen from course
	private JScrollPane addStudentScroll;
	private JPanel addStudentPanel;
	private JComboBox<String> addStudentTypeBox;
	private JButton addStudentConfirmBtn;
	private JButton addStudentCancelBtn;
	private JTextField addStudentFNameField;
	private JTextField addStudentLNameField;
	private JPasswordField addStudentPwdField;
	
	private JLabel addStudentTitle;
	private JLabel addStudentTypeLabel;
	private JLabel addSFNameLabel;
	private JLabel addSLNameLabel;
	private JLabel addSPwdLabel;
	
	//Professor - Remove student screen from course
	
	//Professor - Add an assignment for all students in a course.
	
	//Professor - Edit an assignment for all students in a course.
	
	//Professor - Remove an assignment for all students in a course.
	
	//Professor - View students in a course + grades
	
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
		frame.setTitle("In Pain and Agony :D");
		
		
		setupLoginScreen();
		
		//Setup "Home" menu for Users
		setupAdminOptionScreen();
		setupProfessorOptionScreen();
		setupStudentOptionScreen();
		
		//Setup Admin specific GUI
		setupAdminAddUserScreen();
		setupAdminRemoveUserScreen();
		setupAdminViewUsersScreen();
		
		//Setup Professor specific GUI
		setupProfessorAddStudentScreen();
		
		//Setup Student specific GUI
		
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
		
		String[] adminOptions = {"Add User", "Remove User", "Add Course", "Remove Course" , "Set Professor for Course", "Remove Professor from Course", "Add Student to Course", "Remove Student from Course", "View All Users"};
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
		adminWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		adminWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		adminWelcomeLabel.setBounds(90, 50, 225, 25);
		adminOptionPanel.add(adminWelcomeLabel);
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
		profWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
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
		studentWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		studentWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		studentWelcomeLabel.setBounds(90, 50, 225, 25);
		studentOptionPanel.add(studentWelcomeLabel);
	}
	
	
	public void setupAdminAddUserScreen() {
		addUserPanel = new JPanel();
		addUserPanel.setLayout(null);
		addUserPanel.setPreferredSize(new Dimension(400, 275));
		addUserScroll = new JScrollPane(addUserPanel);
		
		String[] types = {"Admin", "Professor", "Student"};
		addUserTypeBox = new JComboBox<String>(types);
		addUserTypeBox.setBounds(100,80,100,25);
		addUserPanel.add(addUserTypeBox);
		
		addUserFNameField = new JTextField();
		addUserFNameField.setBounds(100, 110, 200, 25);
		addUserPanel.add(addUserFNameField);
		
		addUserLNameField = new JTextField();
		addUserLNameField.setBounds(100, 140, 200, 25);
		addUserPanel.add(addUserLNameField);
		
		addUserPwdField = new JPasswordField();
		addUserPwdField.setBounds(100, 170, 200, 25);
		addUserPanel.add(addUserPwdField);
		
		addUserConfirmBtn = new JButton("Confirm");
		addUserConfirmBtn.setBounds(200, 220, 100, 25);
		addUserConfirmBtn.addActionListener(this);
		addUserPanel.add(addUserConfirmBtn);
		
		addUserCancelBtn = new JButton("Cancel");
		addUserCancelBtn.setBounds(80, 220, 100, 25);
		addUserCancelBtn.addActionListener(this);
		addUserPanel.add(addUserCancelBtn);
		
		addUserTitle = new JLabel("Add User");
		addUserTitle.setHorizontalAlignment(JLabel.CENTER);
		addUserTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		addUserTitle.setBounds(100, 30, 200, 25);
		addUserPanel.add(addUserTitle);
		
		addUserTypeLabel = new JLabel("User Type");
		addUserTypeLabel.setBounds(30, 80, 100, 25);
		addUserPanel.add(addUserTypeLabel);
		
		addFNameLabel = new JLabel("First Name:");
		addFNameLabel.setBounds(30, 110, 100, 25);
		addUserPanel.add(addFNameLabel);
		
		addLNameLabel = new JLabel("Last Name:");
		addLNameLabel.setBounds(30, 140, 100, 25);
		addUserPanel.add(addLNameLabel);
		
		addPwdLabel = new JLabel("Password:");
		addPwdLabel.setBounds(30, 170, 100, 25);
		addUserPanel.add(addPwdLabel);
		
	}
	
	public void setupAdminRemoveUserScreen() {
		removeUserPanel = new JPanel();
		removeUserPanel.setLayout(null);
		removeUserPanel.setPreferredSize(new Dimension(400, 275));
		removeUserScroll = new JScrollPane(removeUserPanel);
		
		removeUserConfirmBtn = new JButton("Confirm");
		removeUserConfirmBtn.setBounds(210, 200, 90, 25);
		removeUserConfirmBtn.addActionListener(this);
		removeUserPanel.add(removeUserConfirmBtn);
		
		removeUserCancelBtn = new JButton("Cancel");
		removeUserCancelBtn.setBounds(100, 200, 90, 25);
		removeUserCancelBtn.addActionListener(this);
		removeUserPanel.add(removeUserCancelBtn);
		
		removeUserLabel = new JLabel("Remove User");
		removeUserLabel.setHorizontalAlignment(JLabel.CENTER);
		removeUserLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		removeUserLabel.setBounds(100, 30, 200, 25);
		removeUserPanel.add(removeUserLabel);
		
		removeUserDeniedLabel = new JLabel("Denied: Must have minimum 1 Admin in System.");
		removeUserDeniedLabel.setBounds(100, 150, 200, 25);
		removeUserDeniedLabel.setForeground(Color.RED);
		removeUserDeniedLabel.setVisible(false);
		removeUserPanel.add(removeUserDeniedLabel);
	}
	
	public void updateRemoveUserScreen() {
		ArrayList<User> users = control.getUserList();
		String[] userIds = new String[users.size()];
		
		for (int i = 0; i < users.size(); i++) {
			userIds[i] = users.get(i).getId();
		}
		
		removeUserListBox = new JComboBox<String>(userIds);
		removeUserListBox.setBounds(100, 100, 200, 25);
		removeUserPanel.add(removeUserListBox);
	}
	
	public void setupAdminViewUsersScreen() {
		viewUsersPanel = new JPanel();
		viewUsersPanel.setLayout(null);
		viewUsersPanel.setPreferredSize(new Dimension(600, 500));
		viewUsersScroll = new JScrollPane(viewUsersPanel);
		
		viewUsersTypeLabel = new JLabel("Type");
		viewUsersTypeLabel.setBounds(50, 30, 100, 25);
		viewUsersPanel.add(viewUsersTypeLabel);
		
		viewUsersFNameLabel = new JLabel("First name");
		viewUsersFNameLabel.setBounds(150, 30, 100, 25);
		viewUsersPanel.add(viewUsersFNameLabel);
		
		viewUsersLNameLabel = new JLabel("Last name");
		viewUsersLNameLabel.setBounds(250, 30, 100, 25);
		viewUsersPanel.add(viewUsersLNameLabel);
		
		viewUsersIdLabel = new JLabel("ID");
		viewUsersIdLabel.setBounds(350, 30, 100, 25);
		viewUsersPanel.add(viewUsersIdLabel);
		
		viewUsersPwdLabel = new JLabel("Password");
		viewUsersPwdLabel.setBounds(450, 30, 100, 25);
		viewUsersPanel.add(viewUsersPwdLabel);
		
		viewUsersExitBtn = new JButton("Exit");
		viewUsersExitBtn.setBounds(40, 200, 100, 25);
		viewUsersExitBtn.addActionListener(this);
		viewUsersPanel.add(viewUsersExitBtn);
	}
	
	private void updateViewUsersScreen() {
		ArrayList<User> arr = control.getUserList();
		
		//Gets rid old data
		viewUsersPanel = new JPanel();
		viewUsersPanel.setLayout(null);
		viewUsersPanel.setPreferredSize(new Dimension(600, 500));
		viewUsersScroll = new JScrollPane(viewUsersPanel);
		
		viewUsersPanel.add(viewUsersTypeLabel);
		viewUsersPanel.add(viewUsersFNameLabel);
		viewUsersPanel.add(viewUsersLNameLabel);
		viewUsersPanel.add(viewUsersIdLabel);
		viewUsersPanel.add(viewUsersPwdLabel);
		
		// For each User, we give them a NEW row. 
		for (int i = 0; i < arr.size(); i++) {
			User currentUser = arr.get(i);
			JLabel type = null;
			
			if (currentUser instanceof Admin) {
				type = new JLabel("Admin");
			} else if (currentUser instanceof Professor) {
				type = new JLabel("Professor");
			} else if (currentUser instanceof Student) {
				type = new JLabel("Student");
			}
			
			type.setBounds(50, 50 + 20 * i, 100, 25);
			
			JLabel fName = new JLabel(currentUser.getFirstName());
			fName.setBounds(150, 50 + 20 * i, 100, 25);
			JLabel lName = new JLabel(currentUser.getLastName());
			lName.setBounds(250, 50 + 20 * i, 100, 25);
			JLabel id = new JLabel(currentUser.getId());
			id.setBounds(350, 50 + 20 * i, 100, 25);
			JLabel pwd = new JLabel(currentUser.getPassword());
			pwd.setBounds(450, 50 + 20 * i, 100, 25);
			
			viewUsersPanel.add(type);
			viewUsersPanel.add(fName);
			viewUsersPanel.add(lName);
			viewUsersPanel.add(id);
			viewUsersPanel.add(pwd);
			viewUsersExitBtn.setBounds(40, 80 + 20 * i, 100, 25);
		}
		viewUsersPanel.add(viewUsersExitBtn);
		
		if (arr.size() < 25) {
			viewUsersPanel.setPreferredSize(new Dimension(600, arr.size() * 20 + 100));
		} else {
			viewUsersPanel.setPreferredSize(new Dimension(600, 600));
		}
	}
	public void setupProfessorAddStudentScreen() {
		addStudentPanel = new JPanel();
		addStudentPanel.setLayout(null);
		addStudentPanel.setPreferredSize(new Dimension(400, 275));
		addStudentScroll = new JScrollPane(addStudentPanel);
		
		addStudentFNameField = new JTextField();
		addStudentFNameField.setBounds(100, 110, 200, 25);
		addStudentPanel.add(addStudentFNameField);
		
		addStudentLNameField = new JTextField();
		addStudentLNameField.setBounds(100, 140, 200, 25);
		addStudentPanel.add(addStudentLNameField);
		
		addStudentPwdField = new JPasswordField();
		addStudentPwdField.setBounds(100, 170, 200, 25);
		addStudentPanel.add(addStudentPwdField);
		
		addStudentConfirmBtn = new JButton("Confirm");
		addStudentConfirmBtn.setBounds(200, 220, 100, 25);
		addStudentConfirmBtn.addActionListener(this);
		addStudentPanel.add(addStudentConfirmBtn);
		
		addStudentCancelBtn = new JButton("Cancel");
		addStudentCancelBtn.setBounds(80, 220, 100, 25);
		addStudentCancelBtn.addActionListener(this);
		addStudentPanel.add(addStudentCancelBtn);
		
		addStudentTitle = new JLabel("Add Student");
		addStudentTitle.setHorizontalAlignment(JLabel.CENTER);
		addStudentTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		addStudentTitle.setBounds(100, 30, 200, 25);
		addStudentPanel.add(addStudentTitle);
		
		
		
		addSFNameLabel = new JLabel("First Name:");
		addSFNameLabel.setBounds(30, 110, 100, 25);
		addStudentPanel.add(addSFNameLabel);
		
		addSLNameLabel = new JLabel("Last Name:");
		addSLNameLabel.setBounds(30, 140, 100, 25);
		addStudentPanel.add(addSLNameLabel);
		
		addSPwdLabel = new JLabel("Student ID:");
		addSPwdLabel.setBounds(30, 170, 100, 25);
		addStudentPanel.add(addSPwdLabel);
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
					pageTransition(loginScroll, adminOptionScroll);
					
				} else if (user instanceof Professor) {
					profWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(loginScroll, profOptionScroll);
				
				} else if (user instanceof Student) {
					studentWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(loginScroll, studentOptionScroll);
				}
				
				statusLabel.setVisible(false);
				loginIdField.setText("");
				loginPwdField.setText("");
				
				frame.pack();
				
			//Failed Login
			} else {
				statusLabel.setVisible(true);
			}
			
		// Admin Logout
		} else if (e.getSource() == adminLogoutBtn) {
			adminOptionsBox.setSelectedIndex(0);
			pageTransition(adminOptionScroll, loginScroll);
			control.logoutUser();
			
		// Professor Logout	
		} else if (e.getSource() == profLogoutBtn) {
			profOptionsBox.setSelectedIndex(0);
			pageTransition(profOptionScroll, loginScroll);
			control.logoutUser();
		
		// Student Logout		
		} else if (e.getSource() == studentLogoutBtn) {
			studentOptionsBox.setSelectedIndex(0);
			pageTransition(studentOptionScroll, loginScroll);
			control.logoutUser();	

// =========================================================================== ADMIN STUFF ===========================================================================			
			
		// Admin Picks an option
		} else if (e.getSource() == adminOptionConfirmBtn) {
			
			if (((String)adminOptionsBox.getSelectedItem()).equals("Add User")) {
				pageTransition(adminOptionScroll, addUserScroll);
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Remove User")) {
				updateRemoveUserScreen();
				pageTransition(adminOptionScroll, removeUserScroll);
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Add Course")) {
				
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Remove Course")) {
				
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Set Professor for Course")) {
				
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Remove Professor from Course")) {
				
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Add Student to Course")) {
				
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("Remove Student from Course")) {
				
				
			} else if (((String)adminOptionsBox.getSelectedItem()).equals("View All Users")) {
				updateViewUsersScreen();
				pageTransition(adminOptionScroll, viewUsersScroll);
			}
			
		// Admin cancels adding a user
		} else if (e.getSource() == addUserCancelBtn) {
			addUserTypeBox.setSelectedIndex(0);
			addUserFNameField.setText("");
			addUserLNameField.setText("");
			addUserPwdField.setText("");
			pageTransition(addUserScroll, adminOptionScroll);
		
		} else if (e.getSource() == removeUserCancelBtn) {	
			pageTransition(removeUserScroll, adminOptionScroll);
			
		//Admin exits viewing all users
		} else if (e.getSource() == viewUsersExitBtn) {
			pageTransition(viewUsersScroll, adminOptionScroll);
		}
		
		
		
		// =========================================================================== PROFESSOR STUFF ===========================================================================			
		
		//Professor pick a option
else if (e.getSource() == profOptionConfirmBtn ) {
			
			if (((String)profOptionsBox.getSelectedItem()).equals("Add Student to Course")) {
				pageTransition(profOptionScroll, addStudentScroll);
				
			} else if (((String)profOptionsBox.getSelectedItem()).equals("Remove Student from Course")){
				
				
			} else if (((String)profOptionsBox.getSelectedItem()).equals("Add an Assignment")) {
				
				
			} else if (((String)profOptionsBox.getSelectedItem()).equals("Edit an Assignment")) {
				
				
			} else if (((String)profOptionsBox.getSelectedItem()).equals("Remove an Assignment")) {
				
				
			} else if (((String)profOptionsBox.getSelectedItem()).equals("View Students + Grades")) {
				
				
		
			}
}
	}

	private void pageTransition(JScrollPane before, JScrollPane after) {
		after.setVisible(true);
		frame.add(after);
		before.setVisible(false);
		frame.remove(before);
		frame.pack();
	}
	
}
