package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Items.Item;
import Models.Mobs.Hero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SavedGameParsing extends Global {
    private static final String savedGameDir = "src/main/java/Models/savedGames.txt";
   private static File file = new File(savedGameDir);
   private static Scanner sc = null;
   private static List<Hero> heroes = new ArrayList<Hero>();

    public static List<Hero> getHeroes() {
        return heroes;
    }

    public static void saveGame(){
           heroes.add(ApplicationControls.getHero());
        try {
            PrintWriter writer = new PrintWriter(savedGameDir, "UTF-8");
            for (int p = 0; p < heroes.size(); p++) {
                writer.println(heroes.get(p).saveString());
            }
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot save game. file not found.");
        } catch (UnsupportedEncodingException e){
            System.out.println("Cannot save game. unsupported encoding.");
        }
    }

    public static List<Hero> openSaves(){

        try {
            sc = new Scanner(file);

            String name;
            String heroClass;
            int level;
            int xpPnts;
            int hpPnts;
            int attackPnts;
            int defencePnts;
            List<Artifact> backpack = new ArrayList<Artifact>();
            Artifact[] equipped = new Artifact[3];
            int equppied;
            int backPackSize;

            while (sc.hasNext()) {
                name = sc.next();
                heroClass = sc.next();
                level = sc.nextInt();
                xpPnts = sc.nextInt();
                hpPnts = sc.nextInt();
                attackPnts = sc.nextInt();
                defencePnts = sc.nextInt();
                equppied = sc.nextInt();
                equipped[0] = new Artifact(sc.next(), sc.nextInt(), HELM);
                equipped[1] = new Artifact(sc.next(), sc.nextInt(), ARMOUR);
                equipped[2] = new Artifact(sc.next(), sc.nextInt(), WEAPON);
                backPackSize = sc.nextInt();
                // TODO confirm that this works.

                for(int i = 0; i < backPackSize; i++){
                    backpack.add(new Artifact(sc.next(), sc.nextInt(), sc.nextInt()));
                }

                heroes.add(new Hero(name,heroClass ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
            }

        } catch (FileNotFoundException e) {
            System.out.println("No saved games");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("invalid saved game");
            e.printStackTrace();
        } catch ( InputMismatchException e){
            System.out.println("invalid saved game " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return (heroes);
    }
}
