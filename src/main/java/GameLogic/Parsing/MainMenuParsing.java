package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import GameLogic.EventData;
import Views.BaseWindow;

import java.util.List;

import static GameLogic.Parsing.EventParsing.instructionParseAsync;

public class MainMenuParsing {
	static List<String> instructions;


	enum MainMenuInstruction {
		print,
		exit,
		new_game,
		gui
	}

public static void mainMenuCommands(){


	int instructionIndex = -1;
	instructions = ApplicationControls.getInstructions();

	try {
		while(ApplicationControls.status == 0) {
			for (int i = 0; i < instructions.size(); i++) {
				instructionIndex = i;
				/* IMPORTANT: remove instruction after use. */

				switch (MainMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

					case print:
						System.out.println(ApplicationControls.getInstructions());
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
			instructions = ApplicationControls.getInstructions();

		}
	} catch (IllegalArgumentException e){
		System.out.println("Invalid instruction:"+instructions.get(instructionIndex));
		ApplicationControls.removeInstructions(instructions.get(instructionIndex));
		mainMenuCommands();
		}
	}
}
