package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.gradebook.gradebook.*;


public class AdminViewUser extends JScrollPane implements ActionListener {

	UserInterface frame;
	JLabel viewUsersFNameLabel;
	JLabel viewUsersLNameLabel;
	JLabel viewUsersIdLabel;
	JLabel viewUsersPwdLabel;
	JLabel viewUsersTypeLabel;
	JButton viewUsersExitBtn;

	public AdminViewUser(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		viewUsersTypeLabel = new JLabel("Type");
		viewUsersTypeLabel.setBounds(50, 30, 100, 25);
		this.add(viewUsersTypeLabel);

		viewUsersFNameLabel = new JLabel("First name");
		viewUsersFNameLabel.setBounds(150, 30, 100, 25);
		this.add(viewUsersFNameLabel);

		viewUsersLNameLabel = new JLabel("Last name");
		viewUsersLNameLabel.setBounds(250, 30, 100, 25);
		this.add(viewUsersLNameLabel);

		viewUsersIdLabel = new JLabel("ID");
		viewUsersIdLabel.setBounds(350, 30, 100, 25);
		this.add(viewUsersIdLabel);

		viewUsersPwdLabel = new JLabel("Password");
		viewUsersPwdLabel.setBounds(450, 30, 100, 25);
		this.add(viewUsersPwdLabel);

		viewUsersExitBtn = new JButton("Exit");
		viewUsersExitBtn.setBounds(40, 200, 100, 25);
		viewUsersExitBtn.addActionListener(this);
		this.add(viewUsersExitBtn);

	
		ImageIcon image3 = new ImageIcon("images/Administration.jpeg");
		
		JLabel image = new JLabel(image3);
		image.setBounds(0,0,1000,500);
		this.add(image);
	}

	public void updateViewUsersScreen() {
		ArrayList<User> arr = frame.control.getUserList();

		// Gets rid old data
		this.removeAll();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(600, 500));

		this.add(viewUsersTypeLabel);
		this.add(viewUsersFNameLabel);
		this.add(viewUsersLNameLabel);
		this.add(viewUsersIdLabel);
		this.add(viewUsersPwdLabel);

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

			this.add(type);
			this.add(fName);
			this.add(lName);
			this.add(id);
			this.add(pwd);
			viewUsersExitBtn.setBounds(40, 80 + 20 * i, 100, 25); // Adjusts location of exit button depending on # of
																	// users
		}
		this.add(viewUsersExitBtn);

		if (arr.size() < 25) {
			this.setPreferredSize(new Dimension(600, arr.size() * 20 + 100));
		} else {
			this.setPreferredSize(new Dimension(600, 600)); // Ensures we don't have an oversized window.
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewUsersExitBtn) {
			frame.pageTransition(frame.adminOptionScroll);
		}

	}

}
