

package com.gradebook.gradebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Driver {

	public static void main(String[] args) {
		Controller con = new Controller();
		Random random = new Random();
		String[] firstName = new String[] { "Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred",
				"Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark",
				"Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor",
				"Walter" };

		String[] lastName = new String[] { "Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd",
				"Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman",
				"Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz",
				"Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar",
				"Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling",
				"Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin",
				"Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Ventotla", "Vogal",
				"Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser",
				"Zeller", "Ziegler", "Bauer", "Baxster", "Casal", "Cataldi", "Caswell", "Celedon", "Chambers",
				"Chapman", "Christensen", "Darnell", "Davidson", "Davis", "DeLorenzo", "Dinkins", "Doran", "Dugelman",
				"Dugan", "Duffman", "Eastman", "Ferro", "Ferry", "Fletcher", "Fietzer", "Hylan", "Hydinger",
				"Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson", "Johnson", "Johnsen", "Jones", "Jurgenson",
				"Kalleg", "Kaskel", "Keller", "Leisinger", "LePage", "Lewis", "Linde", "Lulloff", "Maki", "Martin",
				"McGinnis", "Mills", "Moody", "Moore", "Napier", "Nelson", "Norquist", "Nuttle", "Olson", "Ostrander",
				"Reamer", "Reardon", "Reyes", "Rice", "Ripka", "Roberts", "Rogers", "Root", "Sandstrom", "Sawyer",
				"Schlicht", "Schmitt", "Schwager", "Schutz", "Schuster", "Tapia", "Thompson", "Tiernan", "Tisler" };

		User defaultAdmin = new Admin("Sammy", "Spartan", "DA-0000", "Password1");
		Professor defaultProfessor = new Professor("Abishek", "Gaikwad", "DP-0000", "Password2");
		Student defaultStudent = new Student("Albert", "Einstein", "DS-0000", "Password3");
		con.addUser(defaultStudent);
		con.addUser(defaultAdmin);
		con.addUser(defaultProfessor);
		Course cs151 = new Course("CS-151", defaultProfessor);
		defaultProfessor.addCourse(cs151);
		con.addCourse(cs151);
		User[] usersToAdd = new User[] { defaultAdmin, defaultProfessor,
				new Professor("Michael", "Smith", "MS-3131", "mSmith"),
				new Professor("Jennifer", "Johnson", "JJ-3333", "jJohnson"),
				new Professor("Christopher", "Williams", "CW-5161", "cWill"),
				new Professor("Amanda", "Jones", "AJ-9152", "aJones"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),
				new Student("Albert", "Einstein", "DS-0000", "Password3"),

		};

		for (int i = 0; i < 4; i++) {
			String fn = firstName[random.nextInt(firstName.length)];
			String ln = lastName[random.nextInt(lastName.length)];
			String id = con.system.idGenerator(fn.charAt(0), ln.charAt(0));
			String letter = fn.charAt(0) + "";
			String pass = letter.toLowerCase() + ln;
			con.addUser(new Professor(fn, ln, id, pass));
			for (int b = 0; b < random.nextInt(2) + 1; b++) {
				String randName = "CS-" + random.nextInt(150);
				con.addCourse(new Course(randName));
				Course tempCourse = con.system.getCourse(randName);
				tempCourse.setProfessor((Professor) con.system.getUser(id));
				Professor temp = (Professor) con.system.getUser(id);
				temp.addCourse(tempCourse);
			}
		}

		for (int i = 0; i < 100; i++) {
			String fn = firstName[random.nextInt(firstName.length)];
			String ln = lastName[random.nextInt(lastName.length)];
			String id = con.system.idGenerator(fn.charAt(0), ln.charAt(0));
			String letter = fn.charAt(0) + "";
			String pass = letter.toLowerCase() + ln;
			con.addUser((User) new Student(fn, ln, id, pass));
		}

	}

}
