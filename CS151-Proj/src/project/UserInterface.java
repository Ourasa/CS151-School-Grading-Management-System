package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface {
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
	
	
	public UserInterface(Controller control) {
		this.control = control;
		
	}

	
	/*
	 * List of possible commands it will need:
	 * 
	 * - Get the current user
	 * 
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
