package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface {
	private Controller control;
	
	/*
	 * The guy that the user actually sees. Good idea to use Java swing for this.
	 */
	
	public UserInterface(Controller control) {
		this.control = control;
	}

}
