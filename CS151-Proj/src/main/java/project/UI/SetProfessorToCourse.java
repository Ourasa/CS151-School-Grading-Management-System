package main.java.project.UI;

import javax.swing.*;
import javax.swing.JScrollPane;

import main.java.com.gradebook.gradebookproject.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetProfessorToCourse extends JScrollPane implements ActionListener {

    UserInterface frame;
    ArrayList<Course> courses;
    JComboBox<String> courseComboBox;
    JComboBox<String> professorListComboBox;

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

        courseComboBox = new JComboBox<>();

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

        professorListComboBox = new JComboBox<>(professorBox);
        professorListComboBox.setBounds(180, 70, 150, 25);
        this.add(professorListComboBox);

        courseComboBox = new JComboBox<>(coursesBox);
        courseComboBox.setBounds(180, 110, 150, 25);
        this.add(courseComboBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmBtn) {
            User prof = frame.control.system.getUser((String) professorListComboBox.getSelectedItem());
            Course course = frame.control.system.getCourse((String) courseComboBox.getSelectedItem());
            course.setProfessor((Professor) prof);
            JOptionPane.showMessageDialog(this,
                    "Successfully set professor: " + prof.getId().toString() + ", to course " + course.getName());
        }
        frame.pageTransition(frame.adminOptionScroll);
    }

}
