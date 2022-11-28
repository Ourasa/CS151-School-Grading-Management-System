package project.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.setPreferredSize(new Dimension(400, 275));

		title = new JLabel("Print Transcript");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 18));
		title.setBounds(110, 30, 200, 25);
		this.add(title);

		print = new JButton("Print");
		print.setBounds(150, 90, 100, 25);
		print.addActionListener(this);
		this.add(print);

		back = new JButton("Back");
		back.setBounds(150, 150, 100, 25);
		back.addActionListener(this);
		this.add(back);

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
