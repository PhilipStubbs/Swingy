package Views.Gui;

import Controllers.ApplicationControls;
import Models.Mobs.Hero;
import Models.Mobs.Monster;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightMenu extends BaseWindow {
    private JPanel fightPanel;
    private JComboBox comboBox1;
    private JButton saveExitButton;
    private JButton endTurnButton;
    private JLabel heroName;
    private JLabel heroAttack;
    private JLabel heroDefence;
    private JLabel heroHp;
    private JLabel enemName;
    private JLabel enemAttack;
    private JLabel enemDefence;
    private JLabel enemHealth;

    public FightMenu() {
        updateEnemyDisplay();
        updateHeroDisplay();
        saveExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("save_exit");
            }
        });
    }

    public void updateEnemyDisplay(){
        Monster monster = ApplicationControls.getMonster();

        enemName.setText(monster.getName());
        enemAttack.setText(String.valueOf(monster.getAttackPnts()));
        enemDefence.setText(String.valueOf(monster.getDefencePnts()) + "/" + monster.getMaxDefencePnts());
        enemHealth.setText(String.valueOf(monster.getHitPnts() + "/" + monster.getMaxHitPnts()));
    }

    public void updateHeroDisplay(){
        Hero hero = ApplicationControls.getHero();

        heroName.setText(hero.getName());
        heroAttack.setText(String.valueOf(hero.getAttackPnts()));
        heroDefence.setText(String.valueOf(hero.getDefencePnts()) + "/" + hero.getMaxDefencePnts());
        heroHp.setText(String.valueOf(hero.getHitPnts() + "/" + hero.getMaxHitPnts()));
    }

    static public void displayFightMenu(){
        frame.setContentPane(new FightMenu().fightPanel);
        frame.pack();
    }
}
