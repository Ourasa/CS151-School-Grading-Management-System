package project;

public class Driver {
	
	public static void main(String[] args) {
		User defaultAdmin = new Admin("Default", "Admin", "DA-0000", "Password");
		Controller con = new Controller();
		con.addUser(defaultAdmin);
	}

}
