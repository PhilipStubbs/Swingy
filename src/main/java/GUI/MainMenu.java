package GUI;

import GameLogic.EventData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
	private JButton button1;
	public JPanel panel1;
	private JButton printButton;
	private JButton validButton;

	public MainMenu() {
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				EventData.addInstructions("this was a button click");
			}
		});
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EventData.addInstructions("print");
			}
		});
		validButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				EventData.addInstructions("test");
			}
		});
	}
}
