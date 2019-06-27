package Views.Gui;

import Controllers.ApplicationControls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LootMenu extends BaseWindow{
    private JButton saveExitButton;
    private JButton sayThanksButton;
    private JLabel foundItem;
    private JPanel lootMenuPanel;

    public LootMenu() {

        foundItem.setText(ApplicationControls.getHero().getLatestLoot());

        saveExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("exit");
            }
        });
        sayThanksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("thanks");
            }
        });
    }

    static public void displayLoopMenu(){
        frame.setContentPane(new LootMenu().lootMenuPanel);
        frame.pack();
    }
}
