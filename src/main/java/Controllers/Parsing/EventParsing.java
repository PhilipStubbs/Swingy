package Controllers.Parsing;

import Controllers.ApplicationControls;

public class EventParsing {

	enum Instruction {
		print,
		exit,
		test,
		gui
	}

	public static void instructionParseAsync() {
		new Thread(new Runnable() {
			public void run() {

				while(ApplicationControls.getIsRunning()) {
					MainMenuParsing.mainMenuCommands();

					ContinueMenuParsing.continueMenuCommands();

					NewGameMenuParsing.newGameMenuCommands();

					GameLoopParsing.gameLoopMenuCommands();

					InventoryMenuParsing.inventoryMenuParsing();

					FightMenuParsing.fightMenuParsing();

					LootMenuParsing.lootMenuCommands();

					DiedMenuParsing.diedMenuCommands();
				}
			}
		}).start();
	}

	public static void instructionParse() {
			while(ApplicationControls.getIsRunning()) {
				MainMenuParsing.mainMenuCommands();

				ContinueMenuParsing.continueMenuCommands();

				NewGameMenuParsing.newGameMenuCommands();

				GameLoopParsing.gameLoopMenuCommands();

				InventoryMenuParsing.inventoryMenuParsing();

				FightMenuParsing.fightMenuParsing();

				LootMenuParsing.lootMenuCommands();

				DiedMenuParsing.diedMenuCommands();
		}
	}
}
