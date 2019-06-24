package Views.Gui;

import GameLogic.ApplicationControls;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static GameLogic.Parsing.SavedGameParsing.getHeroes;
import static Models.Global.GAME_LOOP;

public class ContinueMenu extends BaseWindow {
    private JPanel continueMenuPanel;

    private JButton exitButton;
    private JList heroList;
    private JButton selectHeroButton;

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
    }

    static public void displayContinueMenu(){
        frame.setContentPane(new ContinueMenu().continueMenuPanel);
        frame.pack();
    }
}
