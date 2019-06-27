package Models.GameMap;

import Controllers.ApplicationControls;
import Models.Mobs.Hero;

import java.util.Random;

public class GameMap {
	private static int[][] gameMap;
	private static int mapSize;


	public static void createMap(Hero hero){
		int level = hero.getLevel();
		int mapSize = (level - 1)*5 + 10 -(level % 2);
		setMapSize(mapSize);
		int[] heroLoc = ApplicationControls.getHero().findMiddleOfMap(mapSize);
		setGameMap(new int[mapSize][mapSize]);
		for (int i = 0; i < mapSize; i++){
			for (int x = 0; x < mapSize; x++){
				gameMap[i][x] = 0;
			}
		}
		gameMap[heroLoc[0]][heroLoc[1]] = 1;
		hero.setXY(heroLoc[0],heroLoc[1]);
		hero.setPrevXY(heroLoc[0],heroLoc[1]);
		addMonsters(hero, mapSize);
	}

	private static void setGameMap(int[][] gameMap) {
		GameMap.gameMap = gameMap;
	}

	public static int[][] getGameMap() {
		return gameMap;
	}

	public static void updateHeroLoc(Hero hero){
		gameMap[hero.getY()][hero.getX()] = 1;
		gameMap[hero.getPrevY()][hero.getPrevX()] = 0;
	}

	public static void setMapSize(int mapSize) {
		GameMap.mapSize = mapSize;
	}

	public static int getMapSize() {
		return mapSize;
	}

	private static void addMonsters(Hero hero, int mapSize) {
		Random rn = new Random();
		for (int i = 0 ; i < mapSize; i++){
			gameMap[rn.nextInt(mapSize)][rn.nextInt(mapSize)] = rn.nextInt(5) + 2;
		}
	}
}
