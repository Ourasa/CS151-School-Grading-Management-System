package main.java.project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StudentOptionScroll extends JScrollPane implements ActionListener {

	JComboBox<String> studentOptionsBox;
	JButton studentOptionConfirmBtn;
	JButton studentLogoutBtn;
	JLabel studentWelcomeLabel;
	UserInterface frame;

	public StudentOptionScroll(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		String[] studentOptions = { "View Courses and GPA", "Print Transcript" };
		studentOptionsBox = new JComboBox<String>(studentOptions);
		studentOptionsBox.setBounds(100, 120, 200, 25);
		this.add(studentOptionsBox);

		studentOptionConfirmBtn = new JButton();
		studentOptionConfirmBtn.addActionListener(this);
		studentOptionConfirmBtn.setBounds(210, 200, 90, 25);
		studentOptionConfirmBtn.setText("Confirm");
		this.add(studentOptionConfirmBtn);

		studentLogoutBtn = new JButton();
		studentLogoutBtn.addActionListener(this);
		studentLogoutBtn.setBounds(100, 200, 90, 25);
		studentLogoutBtn.setText("Logout");
		this.add(studentLogoutBtn);

		studentWelcomeLabel = new JLabel();
		studentWelcomeLabel.setText("Welcome, ----");
		studentWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		studentWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		studentWelcomeLabel.setBounds(90, 50, 225, 25);
		this.add(studentWelcomeLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == frame.studentOptionScroll.studentLogoutBtn) {
			frame.studentOptionScroll.studentOptionsBox.setSelectedIndex(0);
			frame.loginScroll = new LoginScrollPane(frame);
			frame.pageTransition(frame.loginScroll);
			frame.control.logoutUser();
		}
	}

}
