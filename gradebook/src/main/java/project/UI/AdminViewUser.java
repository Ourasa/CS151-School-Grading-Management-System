package project.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gradebook.gradebook.*;


public class AdminViewUser extends JScrollPane implements ActionListener {

	UserInterface frame;
	JPanel panel;
	
	JLabel viewUsersFNameLabel;
	JLabel viewUsersLNameLabel;
	JLabel viewUsersIdLabel;
	JLabel viewUsersPwdLabel;
	JLabel viewUsersTypeLabel;
	JButton viewUsersExitBtn;

	public AdminViewUser(UserInterface in) {
		frame = in;
		//this.setLayout(null);
		//this.setPreferredSize(new Dimension(600, 500));

		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 275));
		
		viewUsersTypeLabel = new JLabel("Type");
		viewUsersTypeLabel.setBounds(50, 30, 100, 25);
		panel.add(viewUsersTypeLabel);

		viewUsersFNameLabel = new JLabel("First name");
		viewUsersFNameLabel.setBounds(150, 30, 100, 25);
		panel.add(viewUsersFNameLabel);

		viewUsersLNameLabel = new JLabel("Last name");
		viewUsersLNameLabel.setBounds(250, 30, 100, 25);
		panel.add(viewUsersLNameLabel);

		viewUsersIdLabel = new JLabel("ID");
		viewUsersIdLabel.setBounds(350, 30, 100, 25);
		panel.add(viewUsersIdLabel);

		viewUsersPwdLabel = new JLabel("Password");
		viewUsersPwdLabel.setBounds(450, 30, 100, 25);
		panel.add(viewUsersPwdLabel);

		viewUsersExitBtn = new JButton("Exit");
		viewUsersExitBtn.setBounds(40, 200, 100, 25);
		viewUsersExitBtn.addActionListener(this);
		panel.add(viewUsersExitBtn);
		
		this.setViewportView(panel);

	}

	public void updateViewUsersScreen() {
		ArrayList<User> arr = frame.control.getUserList();

		// Gets rid old data
		panel.removeAll();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(600, 500));

		panel.add(viewUsersTypeLabel);
		panel.add(viewUsersFNameLabel);
		panel.add(viewUsersLNameLabel);
		panel.add(viewUsersIdLabel);
		panel.add(viewUsersPwdLabel);

		// For each User, we give them a NEW row.
		for (int i = 0; i < arr.size(); i++) {
			User currentUser = arr.get(i);
			JLabel type = null;

			if (currentUser instanceof Admin) {
				type = new JLabel("Admin");
			} else if (currentUser instanceof Professor) {
				type = new JLabel("Professor");
			} else if (currentUser instanceof Student) {
				type = new JLabel("Student");
			}

			type.setBounds(50, 50 + 20 * i, 100, 25);

			JLabel fName = new JLabel(currentUser.getFirstName());
			fName.setBounds(150, 50 + 20 * i, 100, 25);
			JLabel lName = new JLabel(currentUser.getLastName());
			lName.setBounds(250, 50 + 20 * i, 100, 25);
			JLabel id = new JLabel(currentUser.getId());
			id.setBounds(350, 50 + 20 * i, 100, 25);
			JLabel pwd = new JLabel(currentUser.getPassword());
			pwd.setBounds(450, 50 + 20 * i, 100, 25);

			panel.add(type);
			panel.add(fName);
			panel.add(lName);
			panel.add(id);
			panel.add(pwd);
			viewUsersExitBtn.setBounds(40, 80 + 20 * i, 100, 25); // Adjusts location of exit button depending on # of
																	// users
		}
		panel.add(viewUsersExitBtn);

//		if (arr.size() < 25) {
			panel.setPreferredSize(new Dimension(600, arr.size() * 20 + 100));
//		} else {
//			panel.setPreferredSize(new Dimension(600, 600)); // Ensures we don't have an oversized window.
//		}
		this.setPreferredSize(new Dimension(600, 500));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewUsersExitBtn) {
			frame.pageTransition(frame.adminOptionScroll);
		}

	}

}