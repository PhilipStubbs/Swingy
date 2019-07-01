package Controllers.Services;

import Controllers.ApplicationControls;
import Models.GameMap.GameMap;
import Models.Global;
import Models.Missions.MissionFactory;
import Models.Missions.MissionTypes.Mission;
import Models.Mobs.Hero;
import Models.Mobs.Monster;
import Models.Mobs.MonsterFactory;
import Views.Terminal.GameLoopOutput;

import static Controllers.ApplicationControls.getHero;
import static Controllers.Parsing.GameLoopParsing.gameMap;
import static Views.Gui.GameLoopMenu.displayGameLoopMenu;

public class GameLoopService extends Global {

	public  static void endLevel(Hero hero){
		int xp = 100;
		hero.gainExperince(xp);
		gameMap.createMap(hero);
		Mission mission = ApplicationControls.getMission();
		if (mission.getClass().getSimpleName().contains("LevelCompletion")) {
			mission.addProgess();
			mission.addReward(xp);
			if (mission.getProgess() >= mission.getGoal()) {
				getHero().gainExperince(mission.getReward());
				ApplicationControls.setMission(MissionFactory.Mission());
			}
		}
	}

	public static void monsterCheck(int x, int y){
		if (gameMap.getGameMap()[y][x] >= 2)
		{
			Monster monster = MonsterFactory.generateMonster();
			monster.setXY(x, y);
			ApplicationControls.setMonster(monster);
			ApplicationControls.status = FIGHT_MENU;
		}
	}

	public static void moveHero(int i){
		Hero hero = ApplicationControls.getHero();
		int x = hero.getX();
		int y = hero.getY();
		int size = gameMap.getMapSize();
		System.out.println(size);
		switch(i){
			case 0:     // North
				if (y <= 0) {
					endLevel(hero);
				}else {
					hero.setXY(x, y - 1);
					monsterCheck(x, y-1);
					hero.setPrevXY(x , y);
				}
				break;

			case 1:     // South
				if (y >= size -1) {
					endLevel(hero);
				}else {
					hero.setXY(x, y + 1);
					monsterCheck(x, y+1);
					hero.setPrevXY(x , y);
				}
				break;


			case 2:     // East
				if (x >= size -1) {
					endLevel(hero);
				}else {
					hero.setXY(x + 1, y);
					hero.setPrevXY(x , y);
					monsterCheck(x + 1, y);
				}
				break;

			case 3:     // West
				if (x <= 0) {
					endLevel(hero);
				}else {
					hero.setXY(x - 1, y);
					hero.setPrevXY(x , y);
					monsterCheck(x - 1, y);
				}
				break;
		}
		GameMap.updateHeroLoc(hero);
		displayGameLoopMenu();
		GameLoopOutput.gameLoopMapDisplay();
	}
}
