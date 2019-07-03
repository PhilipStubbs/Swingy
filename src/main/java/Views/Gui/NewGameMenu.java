package Views.Gui;

import Controllers.ApplicationControls;
import Controllers.Services.NewGameMenuService;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JLabel heroClassType;
	private JLabel xp;

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
		heroClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ApplicationControls.setHero(NewGameMenuService.createHero("",heroClasses[heroClass.getSelectedIndex()].toLowerCase()));
				grabStats();
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
		heroClassType.setText("Class: "+ hero.getClass().getSimpleName());
		xp.setText("xp:" + String.valueOf(hero.getExperiencePnts()) + "/" + String.valueOf(hero.getMaxExperiencePnts()));
	}
}
