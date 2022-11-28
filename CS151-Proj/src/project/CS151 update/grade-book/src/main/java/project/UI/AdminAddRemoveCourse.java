package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.gradebook.gradebook.*;


class AdminAddCourse extends JScrollPane implements ActionListener {

	UserInterface frame;
	JComboBox<String> addCourseProfessorBox;
	JButton addCourseConfirmBtn;
	JButton addCourseCancelBtn;
	JTextField addCourseNameField;
	JLabel addCourseTitleLabel;
	JLabel addCourseNameLabel;
	JLabel addCourseProfLabel;

	public AdminAddCourse(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		addCourseProfessorBox = new JComboBox<String>();

		addCourseConfirmBtn = new JButton("Confirm");
		addCourseConfirmBtn.setBounds(210, 200, 90, 25);
		addCourseConfirmBtn.addActionListener(this);
		this.add(addCourseConfirmBtn);

		addCourseCancelBtn = new JButton("Cancel");
		addCourseCancelBtn.setBounds(100, 200, 90, 25);
		addCourseCancelBtn.addActionListener(this);
		this.add(addCourseCancelBtn);

		addCourseNameField = new JTextField();
		addCourseNameField.setBounds(100, 80, 200, 25);
		this.add(addCourseNameField);

		addCourseNameLabel = new JLabel("Name: ");
		addCourseNameLabel.setBounds(50, 80, 100, 25);
		this.add(addCourseNameLabel);

		addCourseTitleLabel = new JLabel("Add Course");
		addCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		addCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		addCourseTitleLabel.setBounds(100, 30, 200, 25);
		this.add(addCourseTitleLabel);

		addCourseProfLabel = new JLabel("Assign Professor:");
		addCourseProfLabel.setBounds(50, 130, 200, 25);
		this.add(addCourseProfLabel);
		
//		JLabel welcome = new JLabel();
//		welcome.setText("Administration");
//		welcome.setBackground(Color.WHITE);
//		welcome.setFont(new Font("Serif", Font.BOLD, 45));
//		welcome.setForeground(Color.BLACK);
//		welcome.setOpaque(true); // to display background of label
//		//adminWelcomeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates border for label
//		welcome.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
//		welcome.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
//		//home.setLayout(null); // need a layout manager to adjust sizes
//		welcome.setBounds(0, 0, 1000, 65); // sets x,y position of label w/ dimensions
//		this.add(welcome);	
		ImageIcon image3 = new ImageIcon("images/Administration.jpeg");
		
		JLabel image = new JLabel(image3);
		image.setBounds(0,0,1000,500);
		this.add(image);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addCourseConfirmBtn) {
			frame.control.addCourse((String) addCourseNameField.getText(),
					(String) addCourseProfessorBox.getSelectedItem());
			JOptionPane.showMessageDialog(this, "Successfully added course.");
		}
		addCourseNameField.setText("");
		addCourseProfessorBox.setSelectedIndex(0);
		frame.pageTransition(frame.adminOptionScroll);

	}

	public void updateAdminAddCourseScreen() {
		ArrayList<User> users = frame.control.getUserList();
		ArrayList<String> professors = new ArrayList<String>();

		professors.add("N/a");

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i) instanceof Professor) {
				professors.add(users.get(i).getId());
			}
		}

		String[] profsBox = new String[professors.size()];
		profsBox = professors.toArray(profsBox);

		addCourseProfessorBox = new JComboBox<String>(profsBox);
		addCourseProfessorBox.setBounds(170, 130, 130, 25);
		this.add(addCourseProfessorBox);
	}

}

class AdminRemoveCourse extends JScrollPane implements ActionListener {

	UserInterface frame;
	JComboBox<String> removeCourseBox;
	JButton removeCourseConfirmBtn;
	JButton removeCourseCancelBtn;
	JLabel removeCourseTitleLabel;
	JLabel removeCourseNameLabel;

	public AdminRemoveCourse(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		removeCourseBox = new JComboBox<String>();

		removeCourseConfirmBtn = new JButton("Confirm");
		removeCourseConfirmBtn.setBounds(210, 200, 90, 25);
		removeCourseConfirmBtn.addActionListener(this);
		this.add(removeCourseConfirmBtn);

		removeCourseCancelBtn = new JButton("Cancel");
		removeCourseCancelBtn.setBounds(100, 200, 90, 25);
		removeCourseCancelBtn.addActionListener(this);
		this.add(removeCourseCancelBtn);

		removeCourseTitleLabel = new JLabel("Remove Course");
		removeCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		removeCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		removeCourseTitleLabel.setBounds(100, 30, 200, 25);
		this.add(removeCourseTitleLabel);

		removeCourseNameLabel = new JLabel("Course:");
		removeCourseNameLabel.setBounds(100, 110, 100, 25);
		this.add(removeCourseNameLabel);
	}

	public void updateAdminRemoveCourseScreen() {
		ArrayList<Course> courses = frame.control.getAllCourses();
		ArrayList<String> coursesNames = new ArrayList<String>();

		// coursesNames.add("Select a Course");

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);

		removeCourseBox = new JComboBox<String>(coursesBox);
		removeCourseBox.setBounds(150, 110, 150, 25);
		this.add(removeCourseBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeCourseConfirmBtn) {
			frame.control.removeCourse((String) removeCourseBox.getSelectedItem());
			JOptionPane.showMessageDialog(this, "Successfully removed course.");
		}
		removeCourseBox.setSelectedIndex(0);
		frame.pageTransition(frame.adminOptionScroll);
	}

}
