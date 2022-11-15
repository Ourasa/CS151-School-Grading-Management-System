package project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProfessorOptionScroll extends JScrollPane implements ActionListener {
	JComboBox<String> profOptionsBox;
	JButton profOptionConfirmBtn;
	JButton profLogoutBtn;
	JLabel profWelcomeLabel;
	UserInterface frame;

	public ProfessorOptionScroll(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, 275));

		String[] profOptions = { "Add Student to Course", "Remove Student from Course", "Add an Assignment",
				"Edit an Assignment", "Remove an Assignment", "View Students + Grades" };
		profOptionsBox = new JComboBox<String>(profOptions);
		profOptionsBox.setBounds(100, 120, 200, 25);
		this.add(profOptionsBox);

		profOptionConfirmBtn = new JButton();
		profOptionConfirmBtn.addActionListener(this);
		profOptionConfirmBtn.setBounds(210, 200, 90, 25);
		profOptionConfirmBtn.setText("Confirm");
		this.add(profOptionConfirmBtn);

		profLogoutBtn = new JButton();
		profLogoutBtn.addActionListener(this);
		profLogoutBtn.setBounds(100, 200, 90, 25);
		profLogoutBtn.setText("Logout");
		this.add(profLogoutBtn);

		profWelcomeLabel = new JLabel();
		profWelcomeLabel.setText("Welcome, ----");
		profWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		profWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		profWelcomeLabel.setBounds(90, 50, 225, 25);
		this.add(profWelcomeLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.actionPerformed(e);

	}

}
