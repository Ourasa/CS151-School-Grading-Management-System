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
	Controller control;

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

	JFrame frame;

	// Login screen components
	LoginScrollPane loginScroll;

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// Admin - Pick an option page : Either buttons, or a drop down box and a
	// confirm button.
	AdminOptionScroll adminOptionScroll;

	// Admin - Add a user to system : Should have text fields to fill in the
	// information of the new user. Dropbox for type of User, likely.
	AdminAddUser adminAddUser;

	// Admin - Remove a user from system : Probably a drop down box containing IDs?
	// Then a confirm button to complete removal... I think
	AdminRemoveUser adminRemoveUser;

	// Admin - Add a course into system: TextFields, a drop down box for Professor,
	// and confirm button.
	AdminAddCourse adminAddCourse;

	// Admin - Remove a course from system: A drop down box, and a confirm button.
	AdminRemoveCourse adminRemoveCourse;

	// Admin - Assign Professor to a Course : Likely 2 drop down boxes, one for
	// courses, and another for Professor. Then a confirm button.

	// Admin - Remove Professor from Course : One drop down box, and a confirm
	// button. (Only 1 professor is in a Course)

	// Admin - Add Student to a Course : 2 drop down boxes, one for courses, one for
	// student. Then a confirm button.

	// Admin - Remove Student from a Course: 2 drop down boxes, one for the course,
	// and one for its current student. Then a confirm button.

	// Admin - View all Users in the system
	JScrollPane viewUsersScroll;
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
		adminAddUser = new AdminAddUser(this);
		adminRemoveUser = new AdminRemoveUser(this);
		adminAddCourse = new AdminAddCourse(this);
		adminRemoveCourse = new AdminRemoveCourse(this);

		setupAdminViewUsersScreen();

		// Setup Professor specific GUI
		setupProfessorAddStudentScreen();

		// Setup Student specific GUI

		frame.setVisible(true);
	}

	public void updateAdminRemoveUserScreen() {
		ArrayList<User> users = control.getUserList();
		String[] userIds = new String[users.size() + 1];

		userIds[0] = "Select a User";

		for (int i = 0; i < users.size(); i++) {
			userIds[i + 1] = users.get(i).getId();
		}

		adminRemoveUser.removeUserListBox = new JComboBox<String>(userIds);
		adminRemoveUser.removeUserListBox.setBounds(100, 100, 200, 25);
		adminRemoveUser.add(adminRemoveUser.removeUserListBox);
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

// =========================================================================== ADMIN STUFF ===========================================================================			

		// Admin Picks an option

		// Admin cancels adding a user
		if (e.getSource() == adminAddUser.addUserCancelBtn) {
			adminAddUser.addUserTypeBox.setSelectedIndex(0);
			adminAddUser.addUserFNameField.setText("");
			adminAddUser.addUserLNameField.setText("");
			adminAddUser.addUserPwdField.setText("");
			pageTransition(adminAddUser, adminOptionScroll);

			// Admin cancels removing a user
		} else if (e.getSource() == adminRemoveUser.removeUserCancelBtn) {
			pageTransition(adminRemoveUser, adminOptionScroll);

			// Admin cancels adding a course
		} else if (e.getSource() == adminAddCourse.addCourseCancelBtn) {
			adminAddCourse.addCourseNameField.setText("");
			adminAddCourse.addCourseProfessorBox.setSelectedIndex(0);
			pageTransition(adminAddCourse, adminOptionScroll);

		} else if (e.getSource() == adminRemoveCourse.removeCourseCancelBtn) {
			adminRemoveCourse.removeCourseBox.setSelectedIndex(0);
			pageTransition(adminRemoveCourse, adminOptionScroll);

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

	void pageTransition(JScrollPane before, JScrollPane after) {
		after.setVisible(true);
		frame.add(after);
		before.setVisible(false);
		frame.remove(before);
		frame.pack();
	}

}
