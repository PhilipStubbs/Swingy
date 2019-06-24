package Views.Gui;

import GameLogic.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoopMenu extends BaseWindow{
    private JButton exitButton;
    private JList map;
    private JButton button1;
    private JButton button2;
    private JButton inventoryButton;
    private JPanel gameLoopMenu;
    private JLabel heroName;
    private JLabel Health;
    private JLabel attack;
    private JLabel defence;
    private JLabel xp;
    private JLabel equippedWeapon;
    private JLabel equippedArmor;
    private JLabel equippedHelm;
    private JLabel level;


    public GameLoopMenu() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("exit");
            }
        });

        updateStats();

    }

    public void updateStats() {
        Hero hero = ApplicationControls.getHero();
        Artifact[] equipped = hero.getEquipped();

        heroName.setText(hero.getName());
        level.setText("Level:"+String.valueOf(hero.getLevel()));
        Health.setText("Health:" + String.valueOf(hero.getHitPnts()));
        attack.setText("Attack:" + String.valueOf(hero.getAttackPnts()));
        defence.setText("defence:" + String.valueOf(hero.getDefencePnts()));
        xp.setText("xp:" + String.valueOf(hero.getExperiencePnts()));
        if (equipped != null && equipped[WEAPON] != null)
            equippedWeapon.setText(String.valueOf(equipped[WEAPON]));
        if (hero.getEquipped() != null && equipped[ARMOUR] != null)
            equippedArmor.setText(String.valueOf(equipped[ARMOUR]));
        if (hero.getEquipped() != null && equipped[HELM] != null)
            equippedHelm.setText(String.valueOf(equipped[HELM]));
    }

    static public void displayGameLoopMenu(){
        frame.setContentPane(new GameLoopMenu().gameLoopMenu);
        frame.pack();
    }
}
