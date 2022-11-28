package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class ProfessorAddStudent extends JScrollPane implements ActionListener {

	UserInterface frame;
	JComboBox<String> addStudentTypeBox;
	JButton addStudentConfirmBtn;
	JButton addStudentCancelBtn;
	JTextField addStudentFNameField;
	JTextField addStudentLNameField;
	JPasswordField addStudentPwdField;

	JLabel addStudentTitle;
	JLabel addStudentTypeLabel;
	JLabel addSFNameLabel;
	JLabel addSLNameLabel;
	JLabel addSPwdLabel;

	public ProfessorAddStudent(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		addStudentFNameField = new JTextField();
		addStudentFNameField.setBounds(100, 110, 200, 25);
		this.add(addStudentFNameField);

		addStudentLNameField = new JTextField();
		addStudentLNameField.setBounds(100, 140, 200, 25);
		this.add(addStudentLNameField);

		addStudentPwdField = new JPasswordField();
		addStudentPwdField.setBounds(100, 170, 200, 25);
		this.add(addStudentPwdField);

		addStudentConfirmBtn = new JButton("Confirm");
		addStudentConfirmBtn.setBounds(200, 220, 100, 25);
		addStudentConfirmBtn.addActionListener(this);
		this.add(addStudentConfirmBtn);

		addStudentCancelBtn = new JButton("Cancel");
		addStudentCancelBtn.setBounds(80, 220, 100, 25);
		addStudentCancelBtn.addActionListener(this);
		this.add(addStudentCancelBtn);

		addStudentTitle = new JLabel("Add Student");
		addStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addStudentTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		addStudentTitle.setBounds(100, 30, 200, 25);
		this.add(addStudentTitle);

		addSFNameLabel = new JLabel("First Name:");
		addSFNameLabel.setBounds(30, 110, 100, 25);
		this.add(addSFNameLabel);

		addSLNameLabel = new JLabel("Last Name:");
		addSLNameLabel.setBounds(30, 140, 100, 25);
		this.add(addSLNameLabel);

		addSPwdLabel = new JLabel("Student ID:");
		addSPwdLabel.setBounds(30, 170, 100, 25);
		this.add(addSPwdLabel);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addStudentCancelBtn) {
			addStudentFNameField.setText("");
			addStudentLNameField.setText("");
			addStudentPwdField.setText("");
			frame.pageTransition(frame.professorOptionScroll);
		}
	}
}
