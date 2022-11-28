package project.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.gradebook.gradebook.*;

import project.*;

public class UserInterface {
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

	public static final JFrame frame = new JFrame();
	public static final Container contentPane = frame.getContentPane();
	LoginScrollPane loginScroll;

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	AdminOptionScroll adminOptionScroll;
	AdminAddUser adminAddUser;
	AdminRemoveUser adminRemoveUser;
	AdminAddCourse adminAddCourse;
	AdminRemoveCourse adminRemoveCourse;
	AdminViewUser adminViewUsers;
	SetProfessorToCourse setProfessorToCourse;
	AddStudentToCourse addStudentToCourse;
	RemoveStudentFromCourse removeStudentFromCourse;
	// Admin - View all Users in the system

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// Professor - Pick an option page
	ProfessorOptionScroll professorOptionScroll;

	// Professor - Add student screen from course
	ProfessorAddStudent professorAddStudent;

	// Professor - Remove student screen from course

	// Professor - Add an assignment for all students in a course.
	AddAssignment addAssignment;
	EditAssignment editAssignment;
	GradeAssignment gradeAssignment;
	ViewStudentsGrades viewGrades;
	// Professor - Edit an assignment for all students in a course.

	// Professor - Remove an assignment for all students in a course.

	// Professor - View students in a course + grades

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// Student - Pick an option page
	StudentOptionScroll studentOptionScroll;
	AdminLoadFile adminLoadFile;
	// Student - View all Courses + Grades
	ViewGPA viewGPA;
	PrintTranscript printTranscript;
	// Student - View all Assignments (of a current course)

	// Student - Print transcript

	public UserInterface(Controller control) {
		this.control = control;
		frame.setSize(400, 275);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("In Pain and Agony :D");
		loginScroll = new LoginScrollPane(this);
		contentPane.add(loginScroll);
		contentPane.validate();
		frame.setVisible(true);
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
		adminViewUsers = new AdminViewUser(this);
		setProfessorToCourse = new SetProfessorToCourse(this);
		addStudentToCourse = new AddStudentToCourse(this);
		removeStudentFromCourse = new RemoveStudentFromCourse(this);
		// Setup Professor specific GUI
		professorAddStudent = new ProfessorAddStudent(this);
		addAssignment = new AddAssignment(this);

		editAssignment = new EditAssignment(this);
		gradeAssignment = new GradeAssignment(this);
		viewGrades = new ViewStudentsGrades(this);
		adminLoadFile = new AdminLoadFile(this);
		// Setup Student specific GUI
		viewGPA = new ViewGPA(this);
		printTranscript = new PrintTranscript(this);
	}

	public void pageTransition(JScrollPane after) {
		contentPane.removeAll();
		contentPane.add(after);
		contentPane.repaint();
		contentPane.revalidate();
		frame.pack();
	}

	public static String getPwd(JPasswordField field) {
		char[] pwd = field.getPassword();
		String completePwd = "";

		for (int i = 0; i < pwd.length; i++) {
			completePwd += pwd[i];
		}

		pwd = null;

		return completePwd;

	}

}
