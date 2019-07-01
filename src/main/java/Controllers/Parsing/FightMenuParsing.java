package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Global;
import Models.Mobs.Hero;
import Models.SavedGameLoader;
import Views.Gui.BaseWindow;
import Views.Gui.FightMenu;
import Views.Terminal.FightMenuOutput;
import java.util.List;
import static Controllers.ApplicationControls.getHero;
import static Controllers.ApplicationControls.getMonster;
import static Controllers.Services.FightMenuService.*;

public class FightMenuParsing extends Global {

    static List<String> instructions;

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
//                monsterDeadCheck();
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
                                monsterDeadCheck();
                                FightMenuOutput.fightOutput();
                                FightMenu.displayFightMenu();
                                break;
                            }

                            case run:
                                if (runFromMonster()){
                                    Hero hero = getHero();
                                    hero.setXY(hero.getPrevX(),hero.getPrevY());
                                }
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
