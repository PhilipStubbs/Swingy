package Views.Gui;

import Controllers.ApplicationControls;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static Controllers.ApplicationControls.getHeroes;

public class ContinueMenu extends BaseWindow {
    private JPanel continueMenuPanel;

    private JButton exitButton;
    private JList heroList;
    private JButton selectHeroButton;
    private JLabel heroClass;
    private JLabel heroName;
    private JLabel level;
    private JLabel hp;
    private JLabel xp;

    private static List<String> heroDataList = new ArrayList<String>();

    public ContinueMenu() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("exit");
            }
        });


        for (int i = 0; i < getHeroes().size(); i++){
            heroDataList.add(getHeroes().get(i).getName());
        }
        heroList.setListData(heroDataList.toArray());

        selectHeroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions(String.valueOf(heroList.getSelectedIndex()));
            }
        });
        heroList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Hero hero = getHeroes().get(heroList.getSelectedIndex());
                heroName.setText("Name: " + hero.getName());
                heroClass.setText("Class: "+hero.getClass().getSimpleName());
                level.setText("Level: "+String.valueOf(hero.getLevel()));
                hp.setText("Health: "+String.valueOf(hero.getHitPnts() + "/" + hero.getMaxHitPnts()));
                xp.setText("Xp: "+ String.valueOf(hero.getExperiencePnts()) + "/"+ String.valueOf(hero.getMaxExperiencePnts())) ;
            }
        });
    }

    static public void displayContinueMenu(){
        frame.setContentPane(new ContinueMenu().continueMenuPanel);
        frame.pack();
    }
}
