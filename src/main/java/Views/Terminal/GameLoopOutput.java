package Views.Terminal;

import Controllers.ApplicationControls;
import Controllers.Parsing.GameLoopParsing;
import Models.Missions.MissionTypes.Mission;

public class GameLoopOutput {
	static public void gameLoopGreating(){
		gameLoopInstructions();
	}

	static public void gameLoopInstructions(){
		System.out.println("'save_exit', 'inventory' ,'north', 'east, 'south', 'west'");
		Mission heroMission = ApplicationControls.getMission();
		String missionDetails = heroMission.getDescription() +":" +heroMission.getProgess()+"/"+heroMission.getGoal();
		System.out.println(missionDetails);
	}

	static public void displayHeroInfo() {
		System.out.println(ApplicationControls.getHero().toString());
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
		displayHeroInfo();
	}
}
