package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminLoadFile extends JScrollPane implements ActionListener {

	UserInterface frame;
	JButton chooseFileBtn;
	JButton confirmBtn;
	JButton cancelButton;
	JLabel loadFileTitleLbl;
	JLabel loadFileDescLbl;
	JLabel fileNameLbl;
	String filePath;
	JFileChooser chooser;

	public AdminLoadFile(UserInterface in) {
		frame = in;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		chooseFileBtn = new JButton("Choose file");
		chooseFileBtn.setHorizontalAlignment(JLabel.CENTER);
		chooseFileBtn.setVerticalAlignment(JLabel.CENTER);
		chooseFileBtn.setBounds(80, 170, 110, 25);
		chooseFileBtn.addActionListener(this);
		this.add(chooseFileBtn);

		fileNameLbl = new JLabel("");
		fileNameLbl.setBounds(80, 190, 100, 50);
		fileNameLbl.setBackground(Color.WHITE);
		fileNameLbl.setForeground(Color.BLACK);
		fileNameLbl.setOpaque(false);
		this.add(fileNameLbl);

		loadFileTitleLbl = new JLabel("Load File");
		loadFileTitleLbl.setHorizontalAlignment(JLabel.CENTER);
		loadFileTitleLbl.setFont(new Font("Serif", Font.PLAIN, 18));
		loadFileTitleLbl.setBackground(Color.WHITE);
		loadFileTitleLbl.setOpaque(true);
		loadFileTitleLbl.setBounds(100, 30, 200, 25);
		this.add(loadFileTitleLbl);

		loadFileDescLbl = new JLabel(
				"<html><center>Select a saved txt file to load into the system.\nWarning: Will log you out. Data not saved will be lost.</center></html>");
		loadFileDescLbl.setHorizontalAlignment(JLabel.CENTER);
		loadFileDescLbl.setBounds(60, 55, 300, 100);
		loadFileDescLbl.setBackground(Color.WHITE);
		loadFileDescLbl.setOpaque(true);
		this.add(loadFileDescLbl);

		confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(this);
		confirmBtn.setBounds(225, 200, 90, 25);
		confirmBtn.setVisible(false);
		this.add(confirmBtn);

		cancelButton = new JButton("Back");
		cancelButton.setBounds(225, 170, 90, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(null);
		chooser.setFileFilter(new FileNameExtensionFilter("Txt", "txt"));
		chooser.setBackground(Color.WHITE);
		chooser.setOpaque(true);

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
		image.setBounds(0, 0, 1000, 500);
		this.add(image);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == chooseFileBtn) {
			int returnValue = chooser.showOpenDialog(frame.frame);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				try {
					File file = chooser.getSelectedFile();
					fileNameLbl.setText(file.getName());
					filePath = file.getPath();
					confirmBtn.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "There is a problem with the selected file");
				}
			}
		} else if (e.getSource() == confirmBtn) {
			try {
				//System.out.println(fileNameLbl.getText().substring(0, 19));
				if (!fileNameLbl.getText().substring(0, 19).equals("gradeSystemSaveFile")) {
					throw new Exception();
				}
				
				frame.loginScroll = new LoginScrollPane(frame);
				frame.pageTransition(frame.loginScroll);
				frame.control.loadTxtFile(filePath);
				frame.control.logoutUser();
				JOptionPane.showMessageDialog(this, "Success: File loaded. Logging you out.");

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Failure: The selected file has an invalid formatting.");
			}
		} else if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.adminOptionScroll);
		}

	}

}
