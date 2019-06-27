package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Global;
import Views.Gui.BaseWindow;
import Views.Gui.DeadMenu;
import Views.Terminal.DeadMenuOutput;

import java.util.List;

public class DiedMenuParsing extends Global {

	static List<String> instructions;


	enum DiedMenuInstruction {
		exit,
		ok,
		new_game,
		continue_game,
		gui,
	}

	public static void diedMenuCommands(){


		int instructionIndex = -1;
		if (ApplicationControls.status == DEAD) {
			instructions = ApplicationControls.getInstructions();
			DeadMenu.displayDeadMenu();
			DeadMenuOutput.outputDeath();
		}
		try {
			while(ApplicationControls.status == DEAD) {

				for (int i = 0; i < instructions.size(); i++) {
					instructionIndex = i;
					/* IMPORTANT: remove instruction after use. */

					if (instructions.get(i) != null) {
						switch (DiedMenuInstruction.valueOf(instructions.get(i).replaceAll(" ", "_").toLowerCase())) {

							case ok:
								ApplicationControls.setIsRunning(false);
								Controllers.ApplicationControls.closeApplication();
								break;

							case exit:
								ApplicationControls.setIsRunning(false);
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
			diedMenuCommands();

		} finally {
			instructions = ApplicationControls.getInstructions();
		}
	}
}
