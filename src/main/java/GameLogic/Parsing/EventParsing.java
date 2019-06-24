package GameLogic.Parsing;

import GameLogic.ApplicationControls;

import java.util.List;

public class EventParsing {

	enum Instruction {
		print,
		exit,
		test,
		gui
	}

	public static void instructionParseAsync() {
		new Thread(new Runnable() {
			List<String> instructions;
			int instructionIndex = 0;
			public void run() {

				while(ApplicationControls.getIsRunning()) {
//					System.out.println("Main Menu");

					MainMenuParsing.mainMenuCommands();
//					System.out.println("Continue Menu");

					ContinueMenuParsing.continueMenuCommands();

//					System.out.println("Begin Game");
					GameLoopParsing.gameLoopMenuCommands();
				}
			}
		}).start();
	}

	public static void instructionParse() {
			while(ApplicationControls.getIsRunning()) {
			MainMenuParsing.mainMenuCommands();

			ContinueMenuParsing.continueMenuCommands();
		}
	}
}
