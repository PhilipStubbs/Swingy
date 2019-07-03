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
    private JButton fightButton;
    private JButton runButton;
    private JButton simulateButton;
    private JScrollPane fightOutput;


    public FightMenu() {
        updateEnemyDisplay();
        updateHeroDisplay();
        updateTextfield();
        saveExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("save_exit");
            }
        });

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("run");
            }
        });
        fightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("fight");
            }
        });
        simulateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("simulate");
            }
        });
    }

    public void updateEnemyDisplay(){
        Monster monster = ApplicationControls.getMonster();

        enemName.setText(monster.getName());
        enemAttack.setText("Attack: "+String.valueOf(monster.getAttackPnts()));
        enemDefence.setText("Defence: " + String.valueOf(monster.getDefencePnts()) + "/" + monster.getMaxDefencePnts());
        enemHealth.setText("Health: " + String.valueOf(monster.getHitPnts() + "/" + monster.getMaxHitPnts()));
    }

    public void updateHeroDisplay(){
        Hero hero = ApplicationControls.getHero();

        heroName.setText(hero.getName());
        heroAttack.setText("Attack: "+String.valueOf(hero.getAttackPnts()));
        heroDefence.setText("Defence: " +String.valueOf(hero.getDefencePnts()) + "/" + hero.getMaxDefencePnts());
        heroHp.setText("Health: " +String.valueOf(hero.getHitPnts() + "/" + hero.getMaxHitPnts()));
    }

    private void updateTextfield(){

        fightOutput.getViewport().add(new JList(ApplicationControls.getFight().toArray()));

    }

    static public void displayFightMenu(){
        frame.setContentPane(new FightMenu().fightPanel);
        frame.pack();
    }
}
