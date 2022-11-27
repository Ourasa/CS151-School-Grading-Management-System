package project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

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
		this.setPreferredSize(new Dimension(400, 275));
		
		chooseFileBtn = new JButton("<html>Choose a file</html>");
		chooseFileBtn.setBounds(80, 150, 110, 25);
		chooseFileBtn.addActionListener(this);
		this.add(chooseFileBtn);
		
		fileNameLbl = new JLabel("");
		fileNameLbl.setBounds(200, 150, 200, 25);
		this.add(fileNameLbl);
		
		loadFileTitleLbl = new JLabel("Load File");
		loadFileTitleLbl.setHorizontalAlignment(JLabel.CENTER);
		loadFileTitleLbl.setFont(new Font("Serif", Font.PLAIN, 18));
		loadFileTitleLbl.setBounds(100, 30, 200, 25);
		this.add(loadFileTitleLbl);
		
		loadFileDescLbl = new JLabel("<html><center>Select a saved txt file to load into the system.\nWarning: Will log you out. Data not saved will be lost.</center></html>");
		loadFileDescLbl.setHorizontalAlignment(JLabel.CENTER);
		loadFileDescLbl.setBounds(60, 50, 300, 100);
		this.add(loadFileDescLbl);
		
		confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(this);
		confirmBtn.setBounds(210, 200, 90, 25);
		confirmBtn.setVisible(false);
		this.add(confirmBtn);
		
		cancelButton = new JButton("Back");
		cancelButton.setBounds(100, 200, 90, 25);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
		
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(null);
		chooser.setFileFilter(new FileNameExtensionFilter("Txt", "txt"));
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
				JOptionPane.showMessageDialog(this, "Success: File loaded. Logging you out.");
				frame.loginScroll = new LoginScrollPane(frame);
				frame.pageTransition(frame.loginScroll);
				frame.control.loadTxtFile(filePath);
				frame.control.logoutUser();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Failure: The selected file has an invalid formatting.");
			}
		} else if (e.getSource() == cancelButton) {
			frame.pageTransition(frame.adminOptionScroll);
		}
		
	}

}
