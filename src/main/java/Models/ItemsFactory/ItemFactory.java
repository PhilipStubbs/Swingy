package Models.ItemsFactory;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.Hero;

import java.util.Random;

import static java.lang.Math.abs;

public class ItemFactory extends Global {

    private static String[] rarity = {"Common", "Rare", "Epic", "Legendary"};
    private static String[] adjectives = {"Wonderful", "Small", "pointy", "Good", "Alright", "Nice", "Spooky"};
    private static String[] hatType = {"Cap", "Helmet", "Top_Hat", "bowler"};
    private static String[] armourType = {"Leather_chest", "Chainmail_chest", "Steel_chest_piece", "cloth_shirt"};
    private static String[] weaponType = {"Sword", "Staff", "Crossbow", "Stick"};


    static public Artifact generateLoot(Hero hero) {

        Random rn = new Random();
        int x = hero.getX();
        int y = hero.getY();
        int level = hero.getLevel();
        int rar = abs((rn.nextInt() + x + y) % 4);
        int adj = abs((rn.nextInt() + x + y) % 7);
        int tpe = abs((rn.nextInt() + x + y) % 3);
        int itemType = abs((rn.nextInt() + x + y) % 4);

        String itemName = "";
        switch (tpe){
            case HELM:
                itemName += rarity[rar] + "_" + adjectives[adj] + "_" + hatType[itemType];
                break;

            case ARMOUR:
                itemName += rarity[rar] + "_" + adjectives[adj] + "_" + armourType[itemType];
                break;

            case WEAPON:
                itemName += rarity[rar] + "_" + adjectives[adj] + "_" + weaponType[itemType];
                break;
        }

        Artifact newItem = new Artifact(itemName, rn.nextInt(level * 10) + 5 + rn.nextInt(level * 10), tpe);
        ApplicationControls.getHero().setLatestLoot(newItem.getDetails());
        return (newItem);
    }
}
