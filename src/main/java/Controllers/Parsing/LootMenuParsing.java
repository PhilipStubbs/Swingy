package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Global;
import Views.Gui.BaseWindow;
import Views.Gui.LootMenu;
import Views.Terminal.LootMenuOutput;

import java.util.List;

public class LootMenuParsing extends Global {

    static List<String> instructions;


    enum LootMenuInstruction {
        save_exit,
        thanks,
        exit,
        gui,
    }

    public static void lootMenuCommands(){


        int instructionIndex = -1;
        LootMenu.displayLoopMenu();
        if (ApplicationControls.status == LOOT) {
            instructions = ApplicationControls.getInstructions();
            LootMenu.displayLoopMenu();
            LootMenuOutput.discribeLoot(ApplicationControls.getHero().getLatestLoot());
        }
        try {
            while(ApplicationControls.status == LOOT) {

                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {
                        switch (LootMenuInstruction.valueOf(instructions.get(i).replaceAll(" ", "_").toLowerCase())) {

                            case thanks:
                                ApplicationControls.status = GAME_LOOP;
                                break;

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
            System.out.println("Invalid instruction:"+instructions.get(instructionIndex));
            ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            lootMenuCommands();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }
}
