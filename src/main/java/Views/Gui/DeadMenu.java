package Views.Gui;

import Controllers.ApplicationControls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeadMenu extends BaseWindow {
	private JButton exitButton;
	private JButton okButton;
	private JPanel deadMenuPanel;

	public DeadMenu() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("ok");
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("exit");
			}
		});
	}

	static public void displayDeadMenu(){
		frame.setContentPane(new DeadMenu().deadMenuPanel);
		frame.pack();
	}
}
