package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Global;
import Models.Mobs.Hero;
import Views.Gui.BaseWindow;
import Views.Terminal.ContinueMenuOutput;

import java.util.List;

import static Controllers.Parsing.SavedGameParsing.getHeroes;
import static Views.Gui.ContinueMenu.displayContinueMenu;

public class ContinueMenuParsing extends Global {
    static List<String> instructions;


    enum ContinueMenuInstruction {
        exit,
        gui,
    }


    public static void continueMenuCommands(){
        int instructionIndex = -1;

        if (ApplicationControls.status == CONTINUE_MENU) {
            displayContinueMenu();
            instructions = ApplicationControls.getInstructions();
            ContinueMenuOutput.outputHeroes(SavedGameParsing.getHeroes());
        }

        try {
            while(ApplicationControls.status == CONTINUE_MENU && ApplicationControls.getHero() == null) {

                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (ContinueMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                System.out.println("killing program");
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
            try {
                 List<Hero> heroList = getHeroes();
                int heroIndex = Integer.parseInt(instructions.get(instructionIndex));
                    if (heroIndex >= 0 && heroIndex <= heroList.size()){
                    ApplicationControls.setHero(heroList.get(heroIndex));
                    ApplicationControls.status = GAME_LOOP;
                } else {
                    System.out.println("Invalid index:" + heroIndex + ". Range from 0 - " + (getHeroes().size() -1));
                }

            } catch (NumberFormatException x){
                System.out.println("Not an int:" + x.getLocalizedMessage());
            }
            ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            continueMenuCommands();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }
}
