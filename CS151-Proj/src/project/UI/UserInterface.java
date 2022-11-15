package project.UI;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import project.*;

public class UserInterface implements ActionListener {
	private Controller control;

	/*
	 * The guy that the user actually sees. Good idea to use Java swing for this.
	 * 
	 * The order of how commands work goes as follows: User requests something
	 * through UserInterface. This may call a command from Controller. Controller
	 * may call a command from GradeSystem. GradeSystem takes action.
	 * 
	 * In order to determine what can be shown to the user and what cannot, it will
	 * need to know what instance the current user is. Thankfully, since only one
	 * user can be logged in at any moment, this class only needs to request to see
	 * which one it is without any arguments.
	 * 
	 * =============================================================================
	 * ================================================= IMPORTANT:
	 * 
	 * The biggest catch is that the only way this class can specify an object for
	 * GradeSystem is through Strings. This is because the goal is to only have a
	 * Controller variable in this class. This is why GradeSystem utilizes TreeMaps,
	 * with a String associated with the actual object.
	 * =============================================================================
	 * =================================================
	 * 
	 * Information about the scrollable! - To make something scrollable, use
	 * JScrollPane.
	 * 
	 * - JScrollPane will automatically make something have scroll bars as need, but
	 * ONLY IF the "preferred size" of the panel, container, etc. is smaller than
	 * what is shown. This means you will need to use (container
	 * name).setPreferredSize(Dimension)
	 * 
	 * - Also, in the case a page is made to be scrollable, you only need to add the
	 * JScrollPane into the Frame.
	 */

	private JFrame frame;

	// Login screen components
	LoginScrollPane loginScroll;

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// Admin - Pick an option page : Either buttons, or a drop down box and a
	// confirm button.
	private AdminOptionScroll adminOptionScroll;

	// Admin - Add a user to system : Should have text fields to fill in the
	// information of the new user. Dropbox for type of User, likely.
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

	// Admin - Remove a user from system : Probably a drop down box containing IDs?
	// Then a confirm button to complete removal... I think
	private JScrollPane removeUserScroll;
	private JPanel removeUserPanel;
	private JComboBox<String> removeUserListBox;
	private JButton removeUserConfirmBtn;
	private JButton removeUserCancelBtn;
	private JLabel removeUserLabel;
	private JLabel removeUserDeniedLabel;

	// Admin - Add a course into system: TextFields, a drop down box for Professor,
	// and confirm button.
	private JScrollPane addCourseScroll;
	private JPanel addCoursePanel;
	private JComboBox<String> addCourseProfessorBox;
	private JButton addCourseConfirmBtn;
	private JButton addCourseCancelBtn;
	private JTextField addCourseNameField;
	private JLabel addCourseTitleLabel;
	private JLabel addCourseNameLabel;
	private JLabel addCourseProfLabel;

	// Admin - Remove a course from system: A drop down box, and a confirm button.
	private JScrollPane removeCourseScroll;
	private JPanel removeCoursePanel;
	private JComboBox<String> removeCourseBox;
	private JButton removeCourseConfirmBtn;
	private JButton removeCourseCancelBtn;
	private JLabel removeCourseTitleLabel;
	private JLabel removeCourseNameLabel;

	// Admin - Assign Professor to a Course : Likely 2 drop down boxes, one for
	// courses, and another for Professor. Then a confirm button.

	// Admin - Remove Professor from Course : One drop down box, and a confirm
	// button. (Only 1 professor is in a Course)

	// Admin - Add Student to a Course : 2 drop down boxes, one for courses, one for
	// student. Then a confirm button.

	// Admin - Remove Student from a Course: 2 drop down boxes, one for the course,
	// and one for its current student. Then a confirm button.

	// Admin - View all Users in the system
	private JScrollPane viewUsersScroll;
	private JPanel viewUsersPanel;
	private JLabel viewUsersFNameLabel;
	private JLabel viewUsersLNameLabel;
	private JLabel viewUsersIdLabel;
	private JLabel viewUsersPwdLabel;
	private JLabel viewUsersTypeLabel;
	private JButton viewUsersExitBtn;

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// Professor - Pick an option page
	ProfessorOptionScroll professorOptionScroll;

	// Professor - Add student screen from course
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

	// Professor - Remove student screen from course

	// Professor - Add an assignment for all students in a course.

	// Professor - Edit an assignment for all students in a course.

	// Professor - Remove an assignment for all students in a course.

	// Professor - View students in a course + grades

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// Student - Pick an option page
	StudentOptionScroll studentOptionScroll;

	// Student - View all Courses + Grades

	// Student - View all Assignments (of a current course)

	// Student - Print transcript

	public UserInterface(Controller control) {
		this.control = control;
		frame = new JFrame();
		frame.setSize(400, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
		frame.setTitle("In Pain and Agony :D");

		loginScroll = new LoginScrollPane(this);
		frame.add(loginScroll);
		frame.pack();

		// Setup "Home" menu for Users
		adminOptionScroll = new AdminOptionScroll(this);
		professorOptionScroll = new ProfessorOptionScroll(this);
		studentOptionScroll = new StudentOptionScroll(this);
		// Setup Admin specific GUI
		setupAdminAddUserScreen();
		setupAdminRemoveUserScreen();
		setupAdminAddCourseScreen();
		setupAdminRemoveCourseScreen();
		setupAdminViewUsersScreen();

		// Setup Professor specific GUI
		setupProfessorAddStudentScreen();

		// Setup Student specific GUI

		frame.setVisible(true);
	}

	public void setupStudentOptionScreen() {

	}

	public void setupAdminAddUserScreen() {
		addUserPanel = new JPanel();
		addUserPanel.setLayout(null);
		addUserPanel.setPreferredSize(new Dimension(400, 275));
		addUserScroll = new JScrollPane(addUserPanel);

		String[] types = { "Admin", "Professor", "Student" };
		addUserTypeBox = new JComboBox<String>(types);
		addUserTypeBox.setBounds(100, 80, 100, 25);
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

	public void updateAdminRemoveUserScreen() {
		ArrayList<User> users = control.getUserList();
		String[] userIds = new String[users.size() + 1];

		userIds[0] = "Select a User";

		for (int i = 0; i < users.size(); i++) {
			userIds[i + 1] = users.get(i).getId();
		}

		removeUserListBox = new JComboBox<String>(userIds);
		removeUserListBox.setBounds(100, 100, 200, 25);
		removeUserPanel.add(removeUserListBox);
	}

	public void setupAdminAddCourseScreen() {

		addCoursePanel = new JPanel();
		addCoursePanel.setLayout(null);
		addCoursePanel.setPreferredSize(new Dimension(400, 275));
		addCourseScroll = new JScrollPane(addCoursePanel);

		addCourseProfessorBox = new JComboBox<String>();

		addCourseConfirmBtn = new JButton("Confirm");
		addCourseConfirmBtn.setBounds(210, 200, 90, 25);
		addCourseConfirmBtn.addActionListener(this);
		addCoursePanel.add(addCourseConfirmBtn);

		addCourseCancelBtn = new JButton("Cancel");
		addCourseCancelBtn.setBounds(100, 200, 90, 25);
		addCourseCancelBtn.addActionListener(this);
		addCoursePanel.add(addCourseCancelBtn);

		addCourseNameField = new JTextField();
		addCourseNameField.setBounds(100, 80, 200, 25);
		addCoursePanel.add(addCourseNameField);

		addCourseNameLabel = new JLabel("Name: ");
		addCourseNameLabel.setBounds(50, 80, 100, 25);
		addCoursePanel.add(addCourseNameLabel);

		addCourseTitleLabel = new JLabel("Add Course");
		addCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		addCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		addCourseTitleLabel.setBounds(100, 30, 200, 25);
		addCoursePanel.add(addCourseTitleLabel);

		addCourseProfLabel = new JLabel("Assign Professor:");
		addCourseProfLabel.setBounds(50, 130, 200, 25);
		addCoursePanel.add(addCourseProfLabel);

	}

	public void updateAdminAddCourseScreen() {
		ArrayList<User> users = control.getUserList();
		ArrayList<String> professors = new ArrayList<String>();

		professors.add("N/a");

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i) instanceof Professor) {
				professors.add(users.get(i).getId());
			}
		}

		String[] profsBox = new String[professors.size()];
		profsBox = professors.toArray(profsBox);

		addCourseProfessorBox = new JComboBox<String>(profsBox);
		addCourseProfessorBox.setBounds(170, 130, 130, 25);
		addCoursePanel.add(addCourseProfessorBox);
	}

	public void setupAdminRemoveCourseScreen() {
		removeCoursePanel = new JPanel();
		removeCoursePanel.setLayout(null);
		removeCoursePanel.setPreferredSize(new Dimension(400, 275));
		removeCourseScroll = new JScrollPane(removeCoursePanel);

		removeCourseBox = new JComboBox<String>();

		removeCourseConfirmBtn = new JButton("Confirm");
		removeCourseConfirmBtn.setBounds(210, 200, 90, 25);
		removeCourseConfirmBtn.addActionListener(this);
		removeCoursePanel.add(removeCourseConfirmBtn);

		removeCourseCancelBtn = new JButton("Cancel");
		removeCourseCancelBtn.setBounds(100, 200, 90, 25);
		removeCourseCancelBtn.addActionListener(this);
		removeCoursePanel.add(removeCourseCancelBtn);

		removeCourseTitleLabel = new JLabel("Remove Course");
		removeCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		removeCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		removeCourseTitleLabel.setBounds(100, 30, 200, 25);
		removeCoursePanel.add(removeCourseTitleLabel);

		removeCourseNameLabel = new JLabel("Course:");
		removeCourseNameLabel.setBounds(100, 110, 100, 25);
		removeCoursePanel.add(removeCourseNameLabel);
	}

	public void updateAdminRemoveCourseScreen() {
		ArrayList<Course> courses = control.getAllCourses();
		ArrayList<String> coursesNames = new ArrayList<String>();

		coursesNames.add("Select a Course");

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);

		removeCourseBox = new JComboBox<String>(coursesBox);
		removeCourseBox.setBounds(150, 110, 150, 25);
		removeCoursePanel.add(removeCourseBox);
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

	public void updateViewUsersScreen() {
		ArrayList<User> arr = control.getUserList();

		// Gets rid old data
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
			viewUsersExitBtn.setBounds(40, 80 + 20 * i, 100, 25); // Adjusts location of exit button depending on # of
																	// users
		}
		viewUsersPanel.add(viewUsersExitBtn);

		if (arr.size() < 25) {
			viewUsersPanel.setPreferredSize(new Dimension(600, arr.size() * 20 + 100));
		} else {
			viewUsersPanel.setPreferredSize(new Dimension(600, 600)); // Ensures we don't have an oversized window.
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

	// ============================================================================
	// GIANT MESSY ACTION LISTENER/PERFORMED BLOB
	// ============================================================================

	/*
	 * It is planned to break down each of the "pages" (ScrollPane + Panels) into
	 * their own classes. This will make things a LOT neater, as they can have their
	 * own actionListeners that way. Not only that, but the variables should become
	 * easier to read, since they will be in their own page's class.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {

		// Login Attempt
		if (e.getSource() == loginScroll.loginButton) {
			String pwd = "";
			for (int i = 0; i < loginScroll.loginPwdField.getPassword().length; i++) {
				pwd += loginScroll.loginPwdField.getPassword()[i];
			}

			// Successful Login
			if (control.loginUser(loginScroll.loginIdField.getText(), pwd)) {
				User user = control.getCurrentUser();
				String fName = user.getFirstName();
				String lName = user.getLastName();

				if (user instanceof Admin) {
					adminOptionScroll.adminWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(loginScroll, adminOptionScroll);

				} else if (user instanceof Professor) {
					professorOptionScroll.profWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(loginScroll, professorOptionScroll);

				} else if (user instanceof Student) {
					studentOptionScroll.studentWelcomeLabel.setText("Welcome, " + fName + " " + lName);
					pageTransition(loginScroll, studentOptionScroll);
				}

				loginScroll.statusLabel.setVisible(false);
				loginScroll.loginIdField.setText("");
				loginScroll.loginPwdField.setText("");

				frame.pack();

				// Failed Login
			} else {
				loginScroll.statusLabel.setVisible(true);
			}

			// Admin Logout
		} else if (e.getSource() == adminOptionScroll.adminLogoutBtn) {
			adminOptionScroll.adminOptionsBox.setSelectedIndex(0);
			pageTransition(adminOptionScroll, loginScroll);
			control.logoutUser();

			// Professor Logout
		} else if (e.getSource() == professorOptionScroll.profLogoutBtn) {
			professorOptionScroll.profOptionsBox.setSelectedIndex(0);
			pageTransition(professorOptionScroll, loginScroll);
			control.logoutUser();

			// Student Logout
		} else if (e.getSource() == studentOptionScroll.studentLogoutBtn) {
			studentOptionScroll.studentOptionsBox.setSelectedIndex(0);
			pageTransition(studentOptionScroll, loginScroll);
			control.logoutUser();

// =========================================================================== ADMIN STUFF ===========================================================================			

			// Admin Picks an option
		} else if (e.getSource() == adminOptionScroll.adminOptionConfirmBtn) {

			if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem()).equals("Add User")) {
				pageTransition(adminOptionScroll, addUserScroll);

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem()).equals("Remove User")) {
				updateAdminRemoveUserScreen();
				pageTransition(adminOptionScroll, removeUserScroll);

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem()).equals("Add Course")) {
				updateAdminAddCourseScreen();
				pageTransition(adminOptionScroll, addCourseScroll);

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem()).equals("Remove Course")) {
				updateAdminRemoveCourseScreen();
				pageTransition(adminOptionScroll, removeCourseScroll);

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem())
					.equals("Set Professor for Course")) {

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem())
					.equals("Remove Professor from Course")) {

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem()).equals("Add Student to Course")) {

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem())
					.equals("Remove Student from Course")) {

			} else if (((String) adminOptionScroll.adminOptionsBox.getSelectedItem()).equals("View All Users")) {
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

			// Admin cancels removing a user
		} else if (e.getSource() == removeUserCancelBtn) {
			pageTransition(removeUserScroll, adminOptionScroll);

			// Admin cancels adding a course
		} else if (e.getSource() == addCourseCancelBtn) {
			addCourseNameField.setText("");
			addCourseProfessorBox.setSelectedIndex(0);
			pageTransition(addCourseScroll, adminOptionScroll);

		} else if (e.getSource() == removeCourseCancelBtn) {
			removeCourseBox.setSelectedIndex(0);
			pageTransition(removeCourseScroll, adminOptionScroll);

			// Admin exits viewing all users
		} else if (e.getSource() == viewUsersExitBtn) {
			pageTransition(viewUsersScroll, adminOptionScroll);
		}

		// ===========================================================================
		// PROFESSOR STUFF
		// ===========================================================================

		// Professor pick a option
		else if (e.getSource() == professorOptionScroll.profOptionConfirmBtn) {

			if (((String) professorOptionScroll.profOptionsBox.getSelectedItem()).equals("Add Student to Course")) {
				pageTransition(professorOptionScroll, addStudentScroll);

			} else if (((String) professorOptionScroll.profOptionsBox.getSelectedItem())
					.equals("Remove Student from Course")) {

			} else if (((String) professorOptionScroll.profOptionsBox.getSelectedItem()).equals("Add an Assignment")) {

			} else if (((String) professorOptionScroll.profOptionsBox.getSelectedItem()).equals("Edit an Assignment")) {

			} else if (((String) professorOptionScroll.profOptionsBox.getSelectedItem())
					.equals("Remove an Assignment")) {

			} else if (((String) professorOptionScroll.profOptionsBox.getSelectedItem())
					.equals("View Students + Grades")) {

			}
			// Professor cancels adding a student
		} else if (e.getSource() == addStudentCancelBtn) {

			addStudentFNameField.setText("");
			addStudentLNameField.setText("");
			addStudentPwdField.setText("");
			pageTransition(addStudentScroll, professorOptionScroll);
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
