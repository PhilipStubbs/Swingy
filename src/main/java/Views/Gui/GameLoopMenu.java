package Views.Gui;

import Controllers.ApplicationControls;
import Controllers.Parsing.GameLoopParsing;
import Models.Artifacts.Artifact;
import Models.GameMap.GameMap;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLoopMenu extends BaseWindow{
    private JButton exitButton;
    private JList map;
    private JButton inventoryButton;
    private JPanel gameLoopMenu;
    private JLabel heroName;
    private JLabel health;
    private JLabel attack;
    private JLabel defence;
    private JLabel xp;
    private JLabel equippedWeapon;
    private JLabel equippedArmor;
    private JLabel equippedHelm;
    private JLabel level;
    private JButton endTurnButton;
    private JComboBox TravelDirection;
    private static List<String> mapDataList = new ArrayList<String>();

    public GameLoopMenu() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("save_exit");
            }
        });
        updateStats();
        updateMap();
    }

    public void updateMap(){
        int[][] gameLoopMap = GameLoopParsing.getGameLoopMap();
        String tmp = "";

        for(int i = 0; i < gameLoopMap.length; i++){
            for(int x = 0; x < gameLoopMap.length; x++) {
                tmp += String.valueOf(gameLoopMap[i][x]);
                tmp +=" ";
            }
            mapDataList.add(tmp);
            tmp = "";
        }
//       String t = Arrays.deepToString(gameLoopMap);
//        StringBuilder sb = new StringBuilder();
//        for(int[] s1 : gameLoopMap){
//            mapDataList.add(s1.toString());
//
//        }
//        System.out.println(t);

        map.setListData(mapDataList.toArray());
    }

    public void updateStats() {
        Hero hero = ApplicationControls.getHero();
        Artifact[] equipped = hero.getEquipped();

        heroName.setText(hero.getName());
        level.setText("Level: "+String.valueOf(hero.getLevel()));
        health.setText("health: " + String.valueOf(hero.getHitPnts()) + "/" + String.valueOf(hero.getMaxHitPnts()));
        attack.setText("Attack: " + String.valueOf(hero.getAttackPnts()));
        defence.setText("defence: " + String.valueOf(hero.getDefencePnts()) + "/" + String.valueOf(hero.getMaxDefencePnts()));
        xp.setText("xp:" + String.valueOf(hero.getExperiencePnts()) + "/" + String.valueOf(hero.getMaxExperiencePnts()));
        if (equipped != null && equipped[WEAPON] != null)
            equippedWeapon.setText("Weapon: "+String.valueOf(equipped[WEAPON].getName() + ":" + equipped[WEAPON].getBuff()));
        if (hero.getEquipped() != null && equipped[ARMOUR] != null)
            equippedArmor.setText("Armour: "+String.valueOf(equipped[ARMOUR].getName()+ ":" + equipped[ARMOUR].getBuff()));
        if (hero.getEquipped() != null && equipped[HELM] != null)
            equippedHelm.setText("Helmet: "+String.valueOf(equipped[HELM].getName() + ":" + equipped[HELM].getBuff()));
    }

    static public void displayGameLoopMenu(){
        frame.setContentPane(new GameLoopMenu().gameLoopMenu);
        frame.pack();
    }
}
