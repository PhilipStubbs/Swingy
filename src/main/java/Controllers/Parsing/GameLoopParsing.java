package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.GameMap.GameMap;
import Models.Global;
import Views.Gui.BaseWindow;
import Views.Terminal.GameLoopOutput;

import java.util.List;
import java.util.Map;

import static Controllers.ApplicationControls.getHero;
import static Views.Gui.GameLoopMenu.displayGameLoopMenu;


public class GameLoopParsing extends Global {

    private static List<String> instructions;
    private static GameMap gameMap = new GameMap();

    public static int[][] getGameLoopMap() {
        return gameMap.getGameMap();
    }

    enum GameLoopMenuInstruction {
        save_exit,
        exit,
        gui,
    }


    public static void gameLoopMenuCommands(){
        int instructionIndex = -1;

        if (ApplicationControls.status == GAME_LOOP) {
            gameMap.createMap(getHero());

            displayGameLoopMenu();
            instructions = ApplicationControls.getInstructions();
            GameLoopOutput.gameLoopGreating();
        }

        try {
            while(ApplicationControls.status == GAME_LOOP) {

                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (GameLoopMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case save_exit:
                                ApplicationControls.setIsRunning(false);
                                SavedGameParsing.saveGame();
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case gui:
                                BaseWindow.showBaseWindow();
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
//            try {
//                List<Hero> heroList = getHeroes();
//                int heroIndex = Integer.parseInt(instructions.get(instructionIndex));
//                if (heroIndex >= 0 && heroIndex <= heroList.size()){
//                    ApplicationControls.setHero(heroList.get(heroIndex));
//                    ApplicationControls.status = GAME_LOOP;
//                    return;
//                } else {
//                    System.out.println("Invalid index:" + heroIndex + ". Range from 0 - " + (getHeroes().size() -1));
//                }
//
//            } catch (NumberFormatException x){
//                System.out.println("Not an int:" + x.getLocalizedMessage());
//            }
            ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            gameLoopMenuCommands();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }
}
