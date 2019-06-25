package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Items.Item;
import Models.Mobs.Hero;

import java.io.*;
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
//            List<Hero> saveList = openSaves();
//            saveList.add(ApplicationControls.getHero());
            Hero hero = ApplicationControls.getHero();
        try {
            FileWriter fr = new FileWriter(file, true);
            fr.write(hero.saveString() + "\n");
            fr.close();
        } catch (IOException e){
            System.out.println("Cannot save game.");
        }
//        try {
//            PrintWriter writer = new PrintWriter(savedGameDir, "UTF-8");
//            for (int p = 0; p < saveList.size(); p++) {
//                writer.println(saveList.get(p).saveString());
//                System.out.println(saveList.get(p).saveString());
//            }
//            writer.close();
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Cannot save game. file not found.");
//        } catch (UnsupportedEncodingException e){
//            System.out.println("Cannot save game. unsupported encoding.");
//        }
    }

    public static List<Hero> openSaves(){
        List<Hero> heroList = new ArrayList<Hero>();

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
            int equppied;
            int backPackSize;

            while (sc.hasNext()) {
                Artifact[] equipped = new Artifact[3];

                name = sc.next();
                heroClass = sc.next();
                level = sc.nextInt();
                xpPnts = sc.nextInt();
                hpPnts = sc.nextInt();
                attackPnts = sc.nextInt();
                defencePnts = sc.nextInt();
                sc.nextInt();
                equipped[0] = new Artifact(sc.next(), sc.nextInt(), HELM);
                equipped[1] = new Artifact(sc.next(), sc.nextInt(), ARMOUR);
                equipped[2] = new Artifact(sc.next(), sc.nextInt(), WEAPON);
                backPackSize = sc.nextInt();
                // TODO confirm that this works.

                for(int i = 0; i < backPackSize; i++){
                    backpack.add(new Artifact(sc.next(), sc.nextInt(), sc.nextInt()));
                }
                Hero hero = new Hero(name,heroClass ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped);
                System.out.println(hero.saveString());
                heroList.add(hero);
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
        return (heroList);
    }

    public static void setHeroes(List<Hero> heroes) {
        SavedGameParsing.heroes = heroes;
    }
}
