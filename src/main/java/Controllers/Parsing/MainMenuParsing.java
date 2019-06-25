package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Global;
import Views.Gui.BaseWindow;
import Views.Gui.MainMenu;

import java.util.List;

public class MainMenuParsing extends Global {
	static List<String> instructions;


	enum MainMenuInstruction {
		print,
		exit,
		new_game,
		continue_game,
		gui,
	}

public static void mainMenuCommands(){


	int instructionIndex = -1;
	if (ApplicationControls.status == MAIN_MENU) {
		instructions = ApplicationControls.getInstructions();
		MainMenu.displayMainMenu();
	}
	try {
		while(ApplicationControls.status == MAIN_MENU) {

			for (int i = 0; i < instructions.size(); i++) {
				instructionIndex = i;
				/* IMPORTANT: remove instruction after use. */

				if (instructions.get(i) != null) {
					switch (MainMenuInstruction.valueOf(instructions.get(i).replaceAll(" ", "_").toLowerCase())) {

						case print:
							System.out.println(ApplicationControls.getInstructions());
							break;

						case continue_game:
							SavedGameParsing.setHeroes(SavedGameParsing.openSaves());
							ApplicationControls.status = CONTINUE_MENU;
							break;

						case exit:
							ApplicationControls.setIsRunning(false);
							System.out.println("killing program");
							Controllers.ApplicationControls.closeApplication();
							break;

						case new_game:
							ApplicationControls.status = NEW_GAME;
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
		mainMenuCommands();

		} finally {
		instructions = ApplicationControls.getInstructions();
		}
	}
}
