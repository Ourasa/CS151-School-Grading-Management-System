package project.UI;

import javax.swing.*;
import javax.swing.JScrollPane;

import com.gradebook.gradebook.*;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetProfessorToCourse extends JScrollPane implements ActionListener {

    UserInterface frame;
    ArrayList<Course> courses;
    AutoComplete courseComboBox;
    AutoComplete professorListComboBox;

    JButton confirmBtn;

    JButton addStudentToCourseButton;
    JButton cancelButton;
    JTextField addCourseNameField;
    JLabel addCourseTitleLabel;
    JLabel selectCourseNameLabel;
    JLabel selectStudentLabel;

    public SetProfessorToCourse(UserInterface in) {
        frame = in;
        courses = frame.control.getAllCourses();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400, 275));


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(100, 200, 90, 25);
        cancelButton.addActionListener(this);
        this.add(cancelButton);

        addCourseTitleLabel = new JLabel("Set Professor to Course");
        addCourseTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        addCourseTitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        addCourseTitleLabel.setBounds(100, 30, 200, 25);
        this.add(addCourseTitleLabel);

        confirmBtn = new JButton();
        confirmBtn.addActionListener(this);
        confirmBtn.setBounds(210, 200, 90, 25);
        confirmBtn.setText("Confirm");
        this.add(confirmBtn);

        selectCourseNameLabel = new JLabel("Course:");
        selectCourseNameLabel.setBounds(100, 110, 100, 25);
        this.add(selectCourseNameLabel);

        selectStudentLabel = new JLabel("Professor:");
        selectStudentLabel.setBounds(100, 70, 100, 25);
        this.add(selectStudentLabel);

    }

    public void courseListBox() {
        ArrayList<Course> courses = frame.control.getAllCourses();
        ArrayList<String> coursesNames = new ArrayList<String>();

        ArrayList<User> professors = frame.control.getUserList();
        ArrayList<String> professorNames = new ArrayList<String>();
        
        professorNames.add("Choose a Professor");
        professorNames.add("None");
        coursesNames.add("Choose a Course");
        
        for (int i = 0; i < courses.size(); i++) {
            coursesNames.add(courses.get(i).getName());
        }
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i) instanceof Professor) {
                professorNames.add(professors.get(i).getId());
            }
        }
        
        String[] professorBox = new String[professorNames.size()];
        professorBox = professorNames.toArray(professorBox);
        
        String[] coursesBox = new String[coursesNames.size()];
        coursesBox = coursesNames.toArray(coursesBox);

        professorListComboBox = new AutoComplete(professorBox);
        professorListComboBox.setBounds(180, 70, 150, 25);
        this.add(professorListComboBox);

        courseComboBox = new AutoComplete(coursesBox);
        courseComboBox.setBounds(180, 110, 150, 25);
        this.add(courseComboBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmBtn) {
        	
        	if ( ((String) professorListComboBox.getSelectedItem()).equals("Choose a Professor")) {
        		JOptionPane.showMessageDialog(this, "Please select a Professor");
        	} else if (((String) courseComboBox.getSelectedItem()).equals("Choose a Course")) {
        		JOptionPane.showMessageDialog(this, "Please select a Course");
        	} else {
        		
        		String course = (String) courseComboBox.getSelectedItem();
        		if (((String) professorListComboBox.getSelectedItem()).equals("None")) {
        			frame.control.removeProfessorFromCourse(course);
        			JOptionPane.showMessageDialog(this,
                            "Successfully removed professor for course " + course);
        		} else {
        			String profId = (String) professorListComboBox.getSelectedItem();
        			frame.control.setProfessorForCourse(course, profId);
        			JOptionPane.showMessageDialog(this,
                          "Successfully set professor: " + profId + ", to course " + course);
        		}
                frame.pageTransition(frame.adminOptionScroll);
        	}
        	
        } else {
        	frame.pageTransition(frame.adminOptionScroll);
        }
    }

}
