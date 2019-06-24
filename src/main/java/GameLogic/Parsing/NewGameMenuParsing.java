package GameLogic.Parsing;

import GameLogic.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.Hero;
import Views.Gui.BaseWindow;
import Views.Gui.NewGameMenu;

import java.util.ArrayList;
import java.util.List;

public class NewGameMenuParsing extends Global {
	static List<String> instructions;

	enum NewGameInstruction {
		exit,
		gui,
	}
	private static Hero createRandomHero(){
		List<Artifact> backpack = new ArrayList<Artifact>();
		Artifact[] equipped = new Artifact[3];
		equipped[0] = new Artifact("Basic_cap", 1, HELM);
		equipped[1] = new Artifact("Cloth_shirt", 1, ARMOUR);
		equipped[2] = new Artifact("Wooden_sword", 1, WEAPON);
		return new Hero("", "Fighter", 0, 0,100, 20, 20,backpack,equipped);
	}

	public static void newGameMenuCommands(){


		int instructionIndex = -1;
		if (ApplicationControls.status == NEW_GAME) {
			ApplicationControls.setHero(createRandomHero());
			instructions = ApplicationControls.getInstructions();
			NewGameMenu.displayNewGameMenu();
		}
		try {
			while(ApplicationControls.status == NEW_GAME) {

				if ( instructions.size() != 0)
					System.out.println("instruction size:" + instructions.size());


				for (int i = 0; i < instructions.size(); i++) {
					instructionIndex = i;
					/* IMPORTANT: remove instruction after use. */

					if (instructions.get(i) != null) {
						switch (NewGameInstruction.valueOf(instructions.get(i).toLowerCase())) {

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
				String[] split = instructions.get(instructionIndex).trim().split("\\s+");
				// TODO check for class type
				if (split.length == 2){
					ApplicationControls.getHero().setName(split[0]);
					ApplicationControls.getHero().setMobClass(split[1]);
					ApplicationControls.status = GAME_LOOP;
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
