package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.Hero;
import Models.Mobs.Mob;
import Models.Mobs.Monster;
import Models.SavedGameLoader;
import Views.Gui.BaseWindow;
import Views.Gui.FightMenu;
import Views.Gui.InventoryMenu;
import Views.Terminal.FightMenuOutput;
import Views.Terminal.InventoryMenuOutput;

import java.util.List;
import java.util.Random;

import static Controllers.ApplicationControls.getHero;
import static Controllers.ApplicationControls.getMonster;
import static java.lang.Math.abs;

public class FightMenuParsing extends Global {

    static List<String> instructions;

    private static void fightMonster() {
        Hero hero = getHero();
        Monster monster = getMonster();
        Random rn = new Random();
        if (rn.nextInt() % 2 == 0){
            monster.takeDamage(hero.getAttackPnts());
            ApplicationControls.addToFight("You hit the "+monster.getName()+" for " + hero.getAttackPnts() + " damage.");

        } else {
            ApplicationControls.addToFight("you missed");
        }
        if (rn.nextInt() % 2 == 0){
            hero.takeDamage(monster.getAttackPnts());
            ApplicationControls.addToFight(monster.getName() + " hit you for " + monster.getAttackPnts()+ " damage.");
        } else {
            ApplicationControls.addToFight(monster.getName() +" missed");
        }
    }

    private static void fightMonster(Hero hero, Monster monster) {
        Random rn = new Random();
        if (rn.nextInt() % 2 == 0){
            monster.takeDamage(hero.getAttackPnts());
            ApplicationControls.addToFight("You hit the "+monster.getName()+" for " + hero.getAttackPnts()+ " damage.");

        } else {
            ApplicationControls.addToFight("you missed");
        }
        if (rn.nextInt() % 2 == 0){
            hero.takeDamage(monster.getAttackPnts());
            ApplicationControls.addToFight(monster.getName() + " hit you for " + monster.getAttackPnts()+ " damage." );
        } else {
            ApplicationControls.addToFight(monster.getName() +" missed");
        }
    }

    private static void looting(){
        Monster monster = getMonster();
        if (monster.isMonsterDead()) {
            ApplicationControls.addToFight(monster.getName()+ " is dead");
            getHero().addEpForMonsterKill(monster);
            Random rn = new Random();
            if (abs(rn.nextInt() % 2) == 0) {
                ApplicationControls.getHero().lootEnemy();
                ApplicationControls.status = LOOT;
            } else {
                ApplicationControls.status = GAME_LOOP;
            }
            GameLoopParsing.getGameLoopMap()[monster.getY()][monster.getX()] = 1;

        }
    }

    private static void runFromMonster(){
        Hero hero = getHero();
        Monster monster = getMonster();
        Random rn = new Random();
        if (rn.nextInt() % 4 == 0){
            ApplicationControls.addToFight("you were able to run.");
           ApplicationControls.status = GAME_LOOP;
        } else {
            hero.takeDamage(monster.getAttackPnts());
            ApplicationControls.addToFight("you were not able to run.");
        }
    }


    private static void simulateFight(){
        Hero hero = getHero();
        Monster monster = getMonster();
        while(!hero.isPlayerDead() && !monster.isMonsterDead()){
            fightMonster(hero, monster);
        }
        looting();
    }

    enum fightMenuInstruction {
        save_exit,
        exit,
        gui,
        simulate,
        fight,
        run
    }

    public static void fightMenuParsing() {
        int instructionIndex = -1;

        if (ApplicationControls.status == FIGHT_MENU) {
            FightMenu.displayFightMenu();

            instructions = ApplicationControls.getInstructions();
            FightMenuOutput.fightInstructions();
        }

        try {
            if (ApplicationControls.status == FIGHT_MENU) {
                FightMenuOutput.fightIntro(getMonster());
            }
            while (ApplicationControls.status == FIGHT_MENU) {
                looting();
                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (fightMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case gui:
                                BaseWindow.showBaseWindow();
                                break;

                            case save_exit:
                                ApplicationControls.setIsRunning(false);
                                SavedGameLoader.saveGame();
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case simulate:
                                simulateFight();
                                FightMenuOutput.fightOutput();
                                break;

                            case fight: {
                                fightMonster();
                                getHero().isPlayerDead();
                                looting();
                                FightMenuOutput.fightOutput();
                                break;
                            }

                            case run:
                                runFromMonster();
                                getHero().isPlayerDead();
                                FightMenu.displayFightMenu();
                                FightMenuOutput.fightOutput();
                                break;

                            default:
                                System.out.println("Invalid instruction not invaild:" + instructions.get(i));

                        }

                        ApplicationControls.removeInstructions(instructions.get(i));
                    }
                }
                instructions = ApplicationControls.getInstructions();

            }
        } catch (IllegalArgumentException e) {
            if (instructionIndex != -1) {
                System.out.println("Invalid instruction:" + instructions.get(instructionIndex));
                ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            }
            fightMenuParsing();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }

}
