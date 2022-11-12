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

	// Uses information to return a User in the database.
	// If such a user does not exists, returns null;
	public boolean loginUser(String id, String password) {
		return system.loginAttempt(id, password);
	}
	
	
	public void addUser(User user) {
		system.addUser(user);
	}

	
	public User getCurrentUser() {
		return system.getCurrentUser();
	}
	
	
}
