package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import Models.Global;
import Models.Mobs.Hero;
import Views.Gui.BaseWindow;
import Views.Terminal.ContinueMenuOutput;

import java.util.List;

import static Views.Gui.GameLoopMenu.displayGameLoopMenu;


public class GameLoopParsing extends Global {

    static List<String> instructions;


    enum GameLoopMenuInstruction {
        exit,
        gui,
    }


    public static void gameLoopMenuCommands(){
        int instructionIndex = -1;

        if (ApplicationControls.status == GAME_LOOP) {
            displayGameLoopMenu();
            instructions = ApplicationControls.getInstructions();
            ContinueMenuOutput.outputHeroes(SavedGameParsing.getHeroes());
        }

        try {
            while(ApplicationControls.status == GAME_LOOP) {

                if ( instructions.size() != 0)
                    System.out.println("instruction size:" + instructions.size());


                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (GameLoopMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                GameLogic.ApplicationControls.closeApplication();
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
