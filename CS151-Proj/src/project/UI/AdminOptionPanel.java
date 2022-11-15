package project.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AdminOptionPanel extends JScrollPane implements ActionListener{

private JPanel adminOptionPanel;
private JComboBox<String> adminOptionsBox;
private JButton adminOptionConfirmBtn;
private JButton adminLogoutBtn;
private JLabel adminWelcomeLabel;

	public AdminOptionPanel() {
		adminOptionPanel.setLayout(null);
		adminOptionPanel.setPreferredSize(new Dimension(400, 275));
		this.add(adminOptionPanel);
		
		String[] adminOptions = {"Add User", "Remove User", "Add Course", "Remove Course" , "Set Professor for Course", "Remove Professor from Course", "Add Student to Course", "Remove Student from Course", "View All Users"};
		adminOptionsBox = new JComboBox<String>(adminOptions);
		adminOptionsBox.setBounds(100, 120, 200, 25);
		adminOptionPanel.add(adminOptionsBox);
		
		adminOptionConfirmBtn = new JButton();
		adminOptionConfirmBtn.addActionListener(this);
		adminOptionConfirmBtn.setBounds(210, 200, 90, 25);
		adminOptionConfirmBtn.setText("Confirm");
		adminOptionPanel.add(adminOptionConfirmBtn);
		
		adminLogoutBtn = new JButton();
		adminLogoutBtn.addActionListener(this);
		adminLogoutBtn.setBounds(100, 200, 90, 25);
		adminLogoutBtn.setText("Logout");
		adminOptionPanel.add(adminLogoutBtn);
		
		
		adminWelcomeLabel = new JLabel();
		adminWelcomeLabel.setText("Welcome, ----");
		adminWelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		adminWelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		adminWelcomeLabel.setBounds(90, 50, 225, 25);
		adminOptionPanel.add(adminWelcomeLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
