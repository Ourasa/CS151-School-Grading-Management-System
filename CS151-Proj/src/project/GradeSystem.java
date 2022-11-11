package project;

import java.util.HashSet;

public class GradeSystem {
	private Controller control;
	private HashSet<User> users;
	private HashSet<Course> courses;
	
	public GradeSystem(Controller control) {
		this.control = control;
	}

	public void addUser(User user) {
		users.add(user);
	}
	
	
}
