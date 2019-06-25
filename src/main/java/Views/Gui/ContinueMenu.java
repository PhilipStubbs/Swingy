package Views.Gui;

import Controllers.ApplicationControls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static Controllers.Parsing.SavedGameParsing.getHeroes;

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
