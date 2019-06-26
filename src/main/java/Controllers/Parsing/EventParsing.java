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
//					System.out.println("Main Menu");

					MainMenuParsing.mainMenuCommands();
//					System.out.println("Continue Menu");

					ContinueMenuParsing.continueMenuCommands();

					NewGameMenuParsing.newGameMenuCommands();

//					System.out.println("Begin Game");
					GameLoopParsing.gameLoopMenuCommands();

					InventoryMenuParsing.inventoryMenuParsing();
				}
			}
		}).start();
	}

	public static void instructionParse() {
			while(ApplicationControls.getIsRunning()) {
				System.out.println();
			MainMenuParsing.mainMenuCommands();

			ContinueMenuParsing.continueMenuCommands();

			NewGameMenuParsing.newGameMenuCommands();

			GameLoopParsing.gameLoopMenuCommands();

			InventoryMenuParsing.inventoryMenuParsing();

		}
	}
}
