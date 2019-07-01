package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Global;
import Models.Missions.MissionFactory;
import Views.Gui.BaseWindow;
import Views.Gui.NewGameMenu;
import Views.Terminal.NewGameMenuOutput;
import java.util.List;

import static Controllers.Services.NewGameMenuService.createHero;

public class NewGameMenuParsing extends Global {
	static List<String> instructions;

	enum NewGameInstruction {
		exit,
		gui,
	}



	public static void newGameMenuCommands(){


		int instructionIndex = -1;
		if (ApplicationControls.status == NEW_GAME) {
			ApplicationControls.setHero(createHero("", "fighter"));
			instructions = ApplicationControls.getInstructions();
			NewGameMenuOutput.outputInstructions();
			NewGameMenu.displayNewGameMenu();
		}
		try {
			while(ApplicationControls.status == NEW_GAME) {

				for (int i = 0; i < instructions.size(); i++) {
					instructionIndex = i;
					/* IMPORTANT: remove instruction after use. */

					if (instructions.get(i) != null) {
						switch (NewGameInstruction.valueOf(instructions.get(i).toLowerCase())) {

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
			try {
				String[] split = instructions.get(instructionIndex).trim().split("\\s+");

				if (split.length == 2){
					try {
						ApplicationControls.setHero(createHero(split[0], split[1]));
						ApplicationControls.setMission(MissionFactory.Mission());
						ApplicationControls.status = GAME_LOOP;
					} catch (IllegalArgumentException heroE){
						System.out.println("Failed to create " + heroE.getLocalizedMessage());
					}

				} else {
					System.out.println("invalid creation.");
				}

			} finally {
				ApplicationControls.removeInstructions(instructions.get(instructionIndex));
				newGameMenuCommands();
			}


		} finally {
			instructions = ApplicationControls.getInstructions();
		}
	}
}
