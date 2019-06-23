package GameLogic.Parsing;

import Models.Artifacts.Artifact;
import Models.Items.Item;
import Models.Mobs.Hero;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SavedGameParsing {
   private static File file = new File("src/main/java/Models/savedGames.txt");
   private static Scanner sc = null;
   private static List<Hero> heroes = new ArrayList<Hero>();

    public static List<Hero> getHeroes() {
        return heroes;
    }

    public static List<Hero> openSaves(){

        try {
            sc = new Scanner(file);

            String name;
            int level;
            int xpPnts;
            int hpPnts;
            int attackPnts;
            int defencePnts;
            List<Item> backpack = new ArrayList<Item>();
            Artifact[] equipped = {};


            while (sc.hasNext()) {
                name = sc.next();
                level = sc.nextInt();
                xpPnts = sc.nextInt();
                hpPnts = sc.nextInt();
                attackPnts = sc.nextInt();
                defencePnts = sc.nextInt();
                // TODO handle items from saved files.
                heroes.add(new Hero(name, level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
            }

        } catch (FileNotFoundException e) {
            System.out.println("No saved games");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("invalid saved game");
            e.printStackTrace();

        }
        return (heroes);
    }
}
