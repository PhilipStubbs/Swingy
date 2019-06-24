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
					MainMenuParsing.mainMenuCommands();
					System.out.println("outside");

					ContinueMenuParsing.continueMenuCommands();
				}
//				try {
//					while (ApplicationControls.getIsRunning()){
//						while(ApplicationControls.status == 0) {
//							instructions = ApplicationControls.getInstructions();
//							for (int i=0; i < instructions.size(); i++) {
//								instructionIndex = i;
//								/* IMPORTANT: remove instruction after use. */
//
//								switch (Instruction.valueOf(instructions.get(i).toLowerCase())) {
//
//									case print: {
//										System.out.println(ApplicationControls.getInstructions());
//										break;
//									}
//									case exit: {
//										ApplicationControls.setIsRunning(false);
//										System.out.println("killing program");
//										GameLogic.ApplicationControls.closeApplication();
//										break;
//									}
//									case gui:
//										BaseWindow.showBaseWindow();
//										break;
//
//									default: {
//										System.out.println("Invalid instruction:" + instructions.get(i));
//									}
//								}
//								ApplicationControls.removeInstructions(instructions.get(i));
//							}
//						}
//					}
//				} catch (IllegalArgumentException e){
//					System.out.println("Invalid instruction:"+instructions.get(instructionIndex));
//					ApplicationControls.removeInstructions(instructions.get(instructionIndex));
//					instructionParseAsync();
//				}
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
