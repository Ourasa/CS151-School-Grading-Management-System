package project.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class PrintTranscript extends JScrollPane implements ActionListener {

	UserInterface frame;
	JLabel title;
	JButton print;
	JButton back;

	public PrintTranscript(UserInterface in) {
		frame = in;

		this.setLayout(null);
		this.setPreferredSize(new Dimension(1000, 500));

		title = new JLabel("Print Transcript");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 18));
		title.setBounds(120, 90, 200, 25);
		this.add(title);

		print = new JButton("Print");
		print.setBounds(170, 180, 100, 25);
		print.addActionListener(this);
		this.add(print);

		back = new JButton("Back");
		back.setBounds(170, 240, 100, 25);
		back.addActionListener(this);
		this.add(back);
		
		JLabel banner = new JLabel();
		banner.setText("Transcript");
		banner.setBackground(Color.MAGENTA);
		banner.setFont(new Font("Serif", Font.BOLD, 30));
		banner.setForeground(Color.WHITE);
		banner.setOpaque(true); // to display background of label
		banner.setHorizontalAlignment(JLabel.CENTER); // horizontal position to text+image in label
		banner.setVerticalAlignment(JLabel.CENTER); // vertical position of text+image in label
		banner.setBounds(500, 0, 500, 50); // sets x,y position of label w/ dimensions
		this.add(banner);
		
		ImageIcon image3 = new ImageIcon("images/student.jpeg");

		JLabel image = new JLabel(image3);
		image.setBounds(500, 20, 500, 500);
		this.add(image);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			frame.pageTransition(frame.studentOptionScroll);
		}
		if (e.getSource() == print) {
			frame.control.printTranscript();
			JOptionPane.showMessageDialog(this, "Transcript successfully printed.");
		}

	}

}
