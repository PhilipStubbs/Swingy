package Views.Gui;

import Controllers.ApplicationControls;
import Controllers.Parsing.GameLoopParsing;
import Models.Artifacts.Artifact;
import Models.GameMap.GameMap;
import Models.Missions.MissionTypes.Mission;
import Models.Mobs.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLoopMenu extends BaseWindow{
    private JButton exitButton;
    private JScrollPane gameMapScrollPlane;
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
     private JButton northButton;
    private JButton eastButton;
    private JButton westButton;
    private JButton southButton;
    private JLabel mission;
    private JLabel heroClass;
    private JList mapList;
    private static List<String> mapDataList;
    private String[] dir= {"North", "East", "South", "West"};

    public GameLoopMenu() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("save_exit");
            }
        });
        updateStats();
        displayMap();
        Mission heroMission = ApplicationControls.getMission();
        String missionDetails = heroMission.getDescription() +":" +heroMission.getProgess()+"/"+heroMission.getGoal();
        mission.setText(missionDetails);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ApplicationControls.addInstructions("inventory");
			}
		});
        northButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("North");
            }
        });
        eastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("East");
            }
        });
        southButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("South");
            }
        });
        westButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ApplicationControls.addInstructions("West");
            }
        });
    }

    public void updateMap(){
        mapDataList = new ArrayList<String>();
        int[][] gameLoopMap = GameLoopParsing.getGameLoopMap();

        String tmp = "";
        for(int i = 0; i < gameLoopMap.length; i++){
            for(int x = 0; x < gameLoopMap.length; x++) {
                if (gameLoopMap[i][x] >= 2 &&  gameLoopMap[i][x] < 4) {
                    tmp += "M";
                } else if (gameLoopMap[i][x] == 1) {
                    tmp += "X";
                } else {
                    tmp += "_";
                }
                tmp +="  ";
            }
            mapDataList.add(tmp);
            tmp = "";
        }
    }

    public void displayMap(){
        updateMap();
        gameMapScrollPlane.getViewport().add(new JList(mapDataList.toArray()));
    }

    public void updateStats() {
        Hero hero = ApplicationControls.getHero();
        Artifact[] equipped = hero.getEquipped();

        heroName.setText(hero.getName());
        heroClass.setText(hero.getClass().getSimpleName());
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
