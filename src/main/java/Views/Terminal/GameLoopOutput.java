package Views.Terminal;

import Controllers.Parsing.GameLoopParsing;

public class GameLoopOutput {
	static public void gameLoopGreating(){
		System.out.println("Now the game begins");
		gameLoopInstructions();
	}

	static public void gameLoopInstructions(){
		System.out.println("'save_exit', 'inventory' ,'north', 'east, 'south', 'west'");
	}

	static public void gameLoopMapDisplay(){
		System.out.println();
		int[][] gameLoopMap = GameLoopParsing.getGameLoopMap();

		for(int i = 0; i < gameLoopMap.length; i++) {
			for (int x = 0; x < gameLoopMap.length; x++) {
				if (gameLoopMap[i][x] >= 2 && gameLoopMap[i][x] < 4) {
					System.out.print("M");
				} else if (gameLoopMap[i][x] == 1) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
				System.out.print("  ");
			}
			System.out.println();
		}
		gameLoopInstructions();
	}
}
