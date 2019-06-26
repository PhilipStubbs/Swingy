package Views.Terminal;

import Controllers.Parsing.GameLoopParsing;

public class GameLoopOutput {
	static public void gameLoopGreating(){
		System.out.println("Now the game begins");
		System.out.println("'save_exit', 'inventory' ,'north', 'east, 'south', 'west'");
	}

	static public void gameLoopMapDisplay(){
		System.out.println();
		int[][] gameLoopMap = GameLoopParsing.getGameLoopMap();
		for(int i = 0; i < gameLoopMap.length; i++){
			for(int x = 0; x < gameLoopMap.length; x++) {
				System.out.print(gameLoopMap[i][x]+" ");
			}
			System.out.println("");
		}
		}
}
