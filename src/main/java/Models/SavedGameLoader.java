package Models;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Mobs.*;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SavedGameLoader extends Global {
    private static final String savedGameDir = "src/main/java/Models/savedGames.txt";
   private static File file = new File(savedGameDir);
   private static Scanner sc = null;
   private static List<Hero> heroes = new ArrayList<Hero>();

    public static List<Hero> getHeroes() {
        return heroes;
    }

    enum HeroClasses{
        wizard,
        fighter,
        hunter,
        rouge
    }

    public static void saveGame(){

            Hero hero = ApplicationControls.getHero();
        try {
            FileWriter fr = new FileWriter(file, true);
            fr.write(hero.saveString() + "\n");
            fr.close();
        } catch (IOException e){
            System.out.println("Cannot save game.");
        }
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
            int equppied;
            int backPackSize;

            while (sc.hasNext()) {
                List<Artifact>[] backpack = new ArrayList[3];
                backpack[HELM] = new ArrayList<Artifact>();
                backpack[ARMOUR] = new ArrayList<Artifact>();
                backpack[WEAPON] = new ArrayList<Artifact>();

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

                for(int i = 0; i < backPackSize; i++){
                    String itemName = sc.next();
                    int itemBuff = sc.nextInt();
                    int itemType = sc.nextInt();
                    switch (itemType){
                        case HELM:
                            backpack[HELM].add(new Artifact(itemName, itemBuff,itemType));
                            break;

                        case ARMOUR:
                            backpack[ARMOUR].add(new Artifact(itemName, itemBuff,itemType));
                            break;

                        case WEAPON:
                            backpack[WEAPON].add(new Artifact(itemName, itemBuff,itemType));
                            break;
                    }
                }

                switch (HeroClasses.valueOf(heroClass.toLowerCase())){
                    case wizard:
                        heroList.add(new Wizard(name ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
                        break;

                    case rouge:
                        heroList.add(new Rouge(name ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
                        break;

                    case hunter:
                        heroList.add(new Hunter(name ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
                        break;

                    case fighter:
                        heroList.add(new Fighter(name ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
                        break;


                    default:
                        heroList.add(new Hero(name ,level, xpPnts, hpPnts ,attackPnts, defencePnts, backpack, equipped));
                        break;


                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No saved games");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("invalid saved game");
            e.printStackTrace();
        } catch (InputMismatchException e){
            System.out.println("invalid saved game " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.out.println("invalid Hero Class " + e.getLocalizedMessage());
        }
        return (heroList);
    }

    public static void setHeroes(List<Hero> heroes) {
        SavedGameLoader.heroes = heroes;
    }
}
