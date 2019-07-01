package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.GameMap.GameMap;
import Models.Global;
import Models.Missions.MissionFactory;
import Models.Missions.MissionTypes.Mission;
import Models.Mobs.Hero;
import Models.Mobs.MonsterFactory;
import Models.SavedGameLoader;
import Views.Gui.BaseWindow;
import Views.Terminal.GameLoopOutput;

import java.util.List;

import static Controllers.ApplicationControls.getHero;
import static Controllers.Services.GameLoopService.moveHero;
import static Views.Gui.GameLoopMenu.displayGameLoopMenu;


public class GameLoopParsing extends Global {

    private static List<String> instructions;
    public static GameMap gameMap = new GameMap();
    private static boolean mapReset;



    public static int[][] getGameLoopMap() {
        return gameMap.getGameMap();
    }

    enum GameLoopMenuInstruction {
        save_exit,
        exit,
        gui,
        inventory,
        north,
        south,
        east,
        west
    }


    public static void gameLoopMenuCommands(){
        int instructionIndex = -1;

        if (ApplicationControls.status == GAME_LOOP) {

        if (!mapReset) {
            gameMap.createMap(getHero());
            mapReset = true;
        }
            GameMap.updateHeroLoc(ApplicationControls.getHero());

            displayGameLoopMenu();
            instructions = ApplicationControls.getInstructions();
            GameLoopOutput.gameLoopGreating();
            GameLoopOutput.gameLoopMapDisplay();
        }

        try {
            while(ApplicationControls.status == GAME_LOOP) {
                getHero().isPlayerDead();
                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (GameLoopMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case north:
                                moveHero(0);
                                break;

                            case south:
                                moveHero(1);
                                break;

                            case east:
                                moveHero(2);
                                break;

                            case west:
                                moveHero(3);
                                break;

                            case save_exit:
                                ApplicationControls.setIsRunning(false);
                                SavedGameLoader.saveGame();
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case gui:
                                BaseWindow.showBaseWindow();
                                break;

                            case inventory:
                                ApplicationControls.status = INVENTORY_MENU;
                                break;

                            default:
                                System.out.println("Invalid instruction:" + instructions.get(i));

                        }

                        ApplicationControls.removeInstructions(instructions.get(i));
                    }
                }
                instructions = ApplicationControls.getInstructions();

            }
        } catch (IllegalArgumentException e){

            ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            gameLoopMenuCommands();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }
}
