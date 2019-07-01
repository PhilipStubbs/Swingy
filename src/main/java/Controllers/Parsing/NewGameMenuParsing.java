package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.Global;
import Models.Mobs.*;
import Models.SavedGameLoader;
import Views.Gui.BaseWindow;
import Views.Gui.NewGameMenu;
import Views.Terminal.NewGameMenuOutput;

import java.util.ArrayList;
import java.util.List;

public class NewGameMenuParsing extends Global {
	static List<String> instructions;

	enum NewGameInstruction {
		exit,
		gui,
	}

	enum HeroClasses{
		wizard,
		fighter,
		hunter,
		rouge
	}

	private static Hero createHero(String name, String heroClass){
		List<Artifact>[] backpack = new ArrayList[3];
		backpack[HELM] = new ArrayList<Artifact>();
		backpack[ARMOUR] = new ArrayList<Artifact>();
		backpack[WEAPON] = new ArrayList<Artifact>();

		Artifact[] equipped = new Artifact[3];
				switch (HeroClasses.valueOf(heroClass.toLowerCase())) {
				case wizard:
					equipped[HELM] = new Artifact("Cool_Wizard_Hat", 1, HELM);
					equipped[ARMOUR] = new Artifact("Robe", 1, ARMOUR);
					equipped[WEAPON] = new Artifact("Staff", 1, WEAPON);
					return (new Wizard(name, 0, 0, 100, 20, 20, backpack, equipped));

				case rouge:
					equipped[HELM] = new Artifact("Basic_cap", 1, HELM);
					equipped[ARMOUR] = new Artifact("Cloth_shirt", 1, ARMOUR);
					equipped[WEAPON] = new Artifact("Daggers", 1, WEAPON);
					return (new Rouge(name, 0, 0, 100, 20, 20, backpack, equipped));

				case hunter:
					equipped[HELM] = new Artifact("Hunters_Hat", 1, HELM);
					equipped[ARMOUR] = new Artifact("Vest", 1, ARMOUR);
					equipped[WEAPON] = new Artifact("Bow", 1, WEAPON);
					return (new Hunter(name, 0, 0, 100, 20, 20, backpack, equipped));

				case fighter:
					equipped[HELM] = new Artifact("Steel_Cap", 1, HELM);
					equipped[ARMOUR] = new Artifact("Cloth_shirt", 1, ARMOUR);
					equipped[WEAPON] = new Artifact("Wooden_sword", 1, WEAPON);
					return (new Fighter(name, 0, 0, 100, 20, 20, backpack, equipped));

				default:
					return (new Hero(name, 0, 0, 100, 20, 20, backpack, equipped));
			}
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
