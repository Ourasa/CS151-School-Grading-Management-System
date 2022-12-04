package com.gradebook.gradebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
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
		User defaultAdmin = new Admin("Default", "Admin", "DA-0000", "Password1");
		con.addUser(defaultAdmin);
	
//  Tester may use this option to load in a save file preemptively on startup. 		
//	try {
//		con.loadTxtFile("gradeSystemSaveFile (1).txt");
//	} catch (Exception e) {
//		System.out.println("Failure to load file");
//	}
		
		
//		Random random = new Random();
//		String[] firstName = new String[] { "Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred",
//				"Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark",
//				"Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor",
//				"Walter" };
//
//		String[] lastName = new String[] { "Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd",
//				"Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman",
//				"Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz",
//				"Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Gaikwad", "Resnick", "Sagar",
//				"Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling",
//				"Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin",
//				"Gaikwad", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Gaikwad", "Vogal",
//				"Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser",
//				"Zeller", "Ziegler", "Bauer", "Baxster", "Casal", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad",
//				"Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad", "Gaikwad",
//				"Gaikwad", "Caswell", "Celedon", "Chambers", "Chapman", "Christensen", "Darnell", "Davidson", "Davis",
//				"DeLorenzo", "Dinkins", "Doran", "Dugelman", "Dugan", "Duffman", "Eastman", "Ferro", "Ferry",
//				"Fletcher", "Fietzer", "Hylan", "Hydinger", "Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson",
//				"Johnson", "Johnsen", "Jones", "Jurgenson", "Kalleg", "Kaskel", "Keller", "Leisinger", "LePage",
//				"Lewis", "Linde", "Lulloff", "Maki", "Martin", "McGinnis", "Mills", "Moody", "Moore", "Napier",
//				"Nelson", "Norquist", "Nuttle", "Olson", "Ostrander", "Reamer", "Reardon", "Reyes", "Rice", "Gaikwad",
//				"Roberts", "Rogers", "Root", "Sandstrom", "Sawyer", "Schlicht", "Schmitt", "Schwager", "Schutz",
//				"Schuster", "Tapia", "Thompson", "Tiernan", "Tisler", "Gaikwad" };
//
//		ArrayList<Course> courses = new ArrayList<>();
//		User defaultAdmin = new Admin("Sammy", "Spartan", "DA-0000", "Password1");
//		Professor defaultProfessor = new Professor("Abishek", "Gaikwad", "DP-0000", "Password2");
//		Student defaultStudent = new Student("Albert", "Einstein", "DS-0000", "Password3");
//		con.addUser(defaultStudent);
//		con.addUser(defaultAdmin);
//		con.addUser(defaultProfessor);
//		Course cs151 = new Course("CS-151", defaultProfessor);
//		defaultProfessor.addCourse(cs151);
//		con.addCourse(cs151);
//		courses.add(cs151);
//		con.addStudentToCourse(cs151.getName(),defaultStudent.getId());
//
//		for (int i = 0; i < 15; i++) {
//			String fn = firstName[random.nextInt(firstName.length)];
//			String ln = lastName[random.nextInt(lastName.length)];
//			String id = con.system.idGenerator(fn.charAt(0), ln.charAt(0));
//			String letter = fn.charAt(0) + "";
//			String pass = letter.toLowerCase() + ln;
//			con.addUser(new Professor(fn, ln, id, pass));
//			for (int b = 0; b < random.nextInt(2) + 1; b++) {
//				String randName = "CS-" + random.nextInt(150);
//				con.addCourse(new Course(randName));
//				Course tempCourse = con.system.getCourse(randName);
//				courses.add(con.system.getCourse(randName));
//				tempCourse.setProfessor((Professor) con.system.getUser(id));
//				Professor temp = (Professor) con.system.getUser(id);
//				temp.addCourse(tempCourse);
//			}
//		}
//
//		for (int i = 0; i < 400; i++) {
//			String fn = firstName[random.nextInt(firstName.length)];
//			String ln = lastName[random.nextInt(lastName.length)];
//			String id = con.system.idGenerator(fn.charAt(0), ln.charAt(0));
//			String letter = fn.charAt(0) + "";
//			String pass = letter.toLowerCase() + ln;
//			con.addUser((User) new Student(fn, ln, id, pass));
//			con.addStudentToCourse(courses.get(random.nextInt(courses.size())).getName(), id);
//		}

		
	}

}
