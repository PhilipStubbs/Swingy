package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import Models.Mobs.Hero;
import Views.Gui.BaseWindow;

import java.util.List;

public class MainMenuParsing {
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
	instructions = ApplicationControls.getInstructions();

	try {
		while(ApplicationControls.status == 0) {

			if ( instructions.size() != 0)
				System.out.println("instruction size:" + instructions.size());


			for (int i = 0; i < instructions.size(); i++) {
				instructionIndex = i;
				/* IMPORTANT: remove instruction after use. */

				if (instructions.get(i) != null) {
					switch (MainMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

						case print:
							System.out.println(ApplicationControls.getInstructions());
							break;

						case continue_game:
							SavedGameParsing.openSaves();
							ApplicationControls.status = 1;
							break;

						case exit:
							ApplicationControls.setIsRunning(false);
							System.out.println("killing program");
							GameLogic.ApplicationControls.closeApplication();
							break;

						case new_game:
							System.out.println("new game totes created");
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
