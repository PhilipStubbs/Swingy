package Views.Gui;

import Controllers.ApplicationControls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends BaseWindow{

	private JPanel mainMenuPanel;
	private JButton newGameButton;
	private JButton exitButton;
	private JButton continueButton;

	public MainMenu() {
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("continue_game");
			}
		});
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("new_game");
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("exit");
			}
		});
	}

	static public void displayMainMenu(){
		frame.setContentPane(new MainMenu().mainMenuPanel);
		frame.pack();
	}
}
