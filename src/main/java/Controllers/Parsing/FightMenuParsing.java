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
            ApplicationControls.addToFight("You hit the monster for " + hero.getAttackPnts());

        }
        if (rn.nextInt() % 2 == 0){
            hero.takeDamage(monster.getAttackPnts());
            ApplicationControls.addToFight(monster.getName() + " hit you for " + monster.getAttackPnts());
        }
    }

    private static void looting(){
        if (getMonster().isMonsterDead()) {
//            ApplicationControls.addToFight(getMonster().getName()+ " is dead");
            getHero().addEpForMonsterKill(getMonster());
            Random rn = new Random();
            if (abs(rn.nextInt() % 2) == 0) {
                ApplicationControls.getHero().lootEnemy();
                ApplicationControls.status = LOOT;
                return;
            } else {
                ApplicationControls.status = GAME_LOOP;
                return;
            }

        } else {
            FightMenu.displayFightMenu();
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


    enum fightMenuInstruction {
        save_exit,
        exit,
        gui,
        fight,
        run
    }

    public static void fightMenuParsing() {
        System.out.println("here");
        int instructionIndex = -1;

        if (ApplicationControls.status == FIGHT_MENU) {
            FightMenu.displayFightMenu();

            instructions = ApplicationControls.getInstructions();
            // TODO fight terminal output
//            InventoryMenuOutput.displayInventoryInstrucitons();
            FightMenuOutput.fightInstructions();
        }

        try {
            FightMenuOutput.fightIntro(getMonster());
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
