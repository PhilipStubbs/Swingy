package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import Models.Global;
import Models.Mobs.Hero;
import Views.Gui.BaseWindow;
import Views.Terminal.ContinueMenuOutput;

import java.util.ArrayList;
import java.util.List;

import static GameLogic.Parsing.SavedGameParsing.getHeroes;

public class ContinueMenuParsing extends Global {
    static List<String> instructions;


    enum ContinueMenuInstruction {
        exit,
        gui,
    }




    public static void continueMenuCommands(){

        ContinueMenuOutput.outputHeroes(SavedGameParsing.getHeroes());

        int instructionIndex = -1;
        instructions = ApplicationControls.getInstructions();

        try {
            while(ApplicationControls.status == CONTINUE_MENU) {

                if ( instructions.size() != 0)
                    System.out.println("instruction size:" + instructions.size());


                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (ContinueMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                System.out.println("killing program");
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
            try {
                 List<Hero> heroList = getHeroes();
                int heroIndex = Integer.parseInt(instructions.get(instructionIndex));
                    if (heroIndex >= 1 && heroIndex <= heroList.size()){
                    ApplicationControls.setHero(heroList.get(heroIndex));
                } else {
                    System.out.println("Invalid index:" + heroIndex + ". Range from 0 - " + (getHeroes().size() -1));
                }

            } catch (NumberFormatException x){
                System.out.println("Not an int:" + x.getLocalizedMessage());
            }
//            System.out.println("Invalid instruction:"+instructions.get(instructionIndex));
            ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            continueMenuCommands();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }
}
