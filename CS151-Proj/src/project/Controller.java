package project;

public class Controller {
	private UserInterface ui;
	private GradeSystem system;
	
	/*
	 * Inspired by MVC.
	 * Idea is that the UserInterface and GradeSystem interact through this class medium.
	 */
	
	public Controller() {
		ui = new UserInterface(this);
		system = new GradeSystem(this);
	}
	
	public void addUser(User user) {
		system.addUser(user);
	}

}
