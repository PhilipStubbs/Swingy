package Models.ItemsFactory;

import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.Hero;

import java.util.Random;

import static java.lang.Math.abs;

public class ItemFactory extends Global {



    private static String[] rarity = {"Common", "Rare", "Epic", "Legendary"};
    private static String[] adjectives = {"Wonderful", "Small", "pointy", "Good", "Alright", "Nice", "Spooky"};
    private static String[] hatType = {"Cap", "Helmet", "Top Hat", "bowler"};
    private static String[] armourType = {"Leather chest", "Chainmail chest", "Steel chest piece", "cloth shirt"};
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

        System.out.println(rar);
        System.out.println(adj);

        System.out.println(tpe);

        System.out.println(itemType);

        String itemName = "";
        switch (tpe){
            case HELM:
                itemName += rarity[rar] + " " + adjectives[adj] + " " + hatType[itemType];
                break;

            case ARMOUR:
                itemName += rarity[rar] + " " + adjectives[adj] + " " + armourType[itemType];
                break;

            case WEAPON:
                itemName += rarity[rar] + " " + adjectives[adj] + " " + weaponType[itemType];
                break;
        }
        System.out.println();
        System.out.println(itemName);
        Artifact newItem = new Artifact(itemName, rn.nextInt(level * 10), tpe);
        System.out.println("look:"+newItem.getDetails());
        return (newItem);
    }
}
