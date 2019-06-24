package Views.Gui;

import GameLogic.ApplicationControls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoopMenu extends BaseWindow{
    private JButton exitButton;
    private JList map;
    private JButton button1;
    private JButton button2;
    private JPanel gameLoopMenu;


    public GameLoopMenu() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("exit");
            }
        });
    }

    static public void displayGameLoopMenu(){
        frame.setContentPane(new GameLoopMenu().gameLoopMenu);
        frame.pack();
    }
}
