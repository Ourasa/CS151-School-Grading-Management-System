package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gradebook.gradebook.Course;
import com.gradebook.gradebook.Professor;

public class RemoveAssignment extends JScrollPane implements ActionListener{

	UserInterface frame;
	
	JLabel pageTitle;
	JLabel selectCourseLabel;
	JLabel assignmentNameLabel;
	
	JButton confirmButton;
	JButton cancelButton;
	
	AutoComplete assignmentListBox;
	JComboBox courseListBox;
	
	public RemoveAssignment(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));
		
		pageTitle = new JLabel("Remove Assignment");
		pageTitle.setHorizontalAlignment(JLabel.CENTER);
		pageTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		pageTitle.setBounds(110, 30, 200, 25);
		this.add(pageTitle);
		
		selectCourseLabel = new JLabel("Course:");
		selectCourseLabel.setBounds(30, 60, 50, 25);
		this.add(selectCourseLabel);

		assignmentNameLabel = new JLabel("Assignment:");
		assignmentNameLabel.setBounds(30, 90, 80, 25);
		this.add(assignmentNameLabel);
		
		assignmentListBox = new AutoComplete(new String[0]);
		assignmentListBox.setBounds(120, 90, 100, 25);
		this.add(assignmentListBox);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(210, 200, 90, 25);
		confirmButton.addActionListener(this);
		this.add(confirmButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(100, 200, 90, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
		
		JLabel banner = new JLabel();
		banner.setText("Welcome Professor");
		banner.setBackground(Color.GRAY);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		// banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates
		// border for label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		// home.setLayout(null); // need a layout manager to adjust sizes
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		this.add(banner);

		this.validate();
		this.repaint();
	}
	
	public void updateCourseList() {
		ArrayList<Course> courses;

		Professor prof = (Professor) frame.control.getCurrentUser();
		courses = prof.getCourses();

		ArrayList<String> coursesNames = new ArrayList<String>();

		for (int i = 0; i < courses.size(); i++) {
			coursesNames.add(courses.get(i).getName());
		}

		String[] coursesBox = new String[coursesNames.size()];
		coursesBox = coursesNames.toArray(coursesBox);

		courseListBox = new JComboBox<>(coursesBox);
		courseListBox.setBounds(120, 60, 120, 25);
		updateAssignmentList((String) courseListBox.getSelectedItem());
		courseListBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				updateAssignmentList(e.getItem().toString());
			}
		});
		this.add(courseListBox);

	}
	
	public void updateAssignmentList(String cours) {
		Course course = frame.control.getCourse(cours);
		ArrayList<String> assignmentNames = new ArrayList<String>(course.getAsgnNameList());
		
		String[] assignmentBox = new String[assignmentNames.size()];
		assignmentBox = assignmentNames.toArray(assignmentBox);

		this.remove(assignmentListBox);
		assignmentListBox = new AutoComplete(assignmentBox);
		assignmentListBox.setBounds(120, 90, 100, 25);
		this.add(assignmentListBox);

		ImageIcon image3 = new ImageIcon("images/Professor2.png");

		JLabel image = new JLabel(image3);
		image.setBounds(500, 20, 500, 500);
		this.add(image);

		JLabel banner = new JLabel();
		banner.setText("Welcome Professor");
		banner.setBackground(Color.GRAY);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		// banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 15)); // creates
		// border for label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		// home.setLayout(null); // need a layout manager to adjust sizes
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		this.add(banner);

		this.validate();
		this.repaint();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			if (assignmentListBox.getItemCount() == 0) {
				JOptionPane.showMessageDialog(this, "Failed: There are no assignments to remove.");
			} else {
				frame.control.removeAssignment((String)courseListBox.getSelectedItem(), (String)assignmentListBox.getSelectedItem());
				JOptionPane.showMessageDialog(this, "Success! " + (String)assignmentListBox.getSelectedItem() + " removed.");
				updateAssignmentList((String)courseListBox.getSelectedItem());
			}
		} else if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.professorOptionScroll);
		}
	}

}
