package Views.Gui;

import GameLogic.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameMenu extends BaseWindow {
	private JPanel newGameMenu;
	private JButton exitButton;
	private JTextArea heroName;
	private JList heroClass;
	private JButton createButton;
	private JLabel level;
	private JLabel health;
	private JLabel attack;
	private JLabel defence;

	private String heroClasses[] = {"Wizard", "Fighter", "Hunter", "Rouge"};

	public NewGameMenu() {
		heroClass.setListData(heroClasses);
		grabStats();

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("exit");
			}
		});
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int heroIndex = heroClass.getSelectedIndex();
				if (heroIndex < 0)
					heroIndex = 0;
				String createdData = heroName.getText().replaceAll(" ", "_") + " " + heroClasses[heroIndex];
				ApplicationControls.addInstructions(createdData);
			}
		});
	}

	static public void displayNewGameMenu(){
		frame.setContentPane(new NewGameMenu().newGameMenu);
		frame.pack();
	}

	public void grabStats() {
		Hero hero = ApplicationControls.getHero();

		level.setText("Level: "+ "0");
		health.setText("Health: " + String.valueOf(hero.getHitPnts()) + "/" + String.valueOf(hero.getMaxHitPnts()));
		attack.setText("Attack: " + String.valueOf(hero.getAttackPnts()));
		defence.setText("defence: " + String.valueOf(hero.getDefencePnts()) + "/" + String.valueOf(hero.getMaxDefencePnts()));
	}
}
