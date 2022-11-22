package project;

public abstract class User implements Comparable<User> {
	private String firstName;		
	private String lastName;
	private String id;				//Id should follow the format of first character of first name, first character of second name, dash, and 4 numbers. EX:  AB-0000
	private String password;
	
	
	public User(String firstName, String lastName, String id, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.password = password;
	}

	public void printInfo() {
		System.out.println("Placeholder for now");
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int compareTo(User other) {
		return this.getId().compareTo(other.getId());
	}
}
