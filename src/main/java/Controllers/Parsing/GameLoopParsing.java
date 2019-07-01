package Controllers.Parsing;

import Controllers.ApplicationControls;
import Models.GameMap.GameMap;
import Models.Global;
import Models.Mobs.Hero;
import Models.Mobs.MonsterFactory;
import Models.SavedGameLoader;
import Views.Gui.BaseWindow;
import Views.Terminal.GameLoopOutput;

import java.util.List;

import static Controllers.ApplicationControls.getHero;
import static Views.Gui.GameLoopMenu.displayGameLoopMenu;


public class GameLoopParsing extends Global {

    private static List<String> instructions;
    private static GameMap gameMap = new GameMap();
    private static boolean mapReset;

    public  static void endLevel(Hero hero){
        hero.gainExperince(100);
        gameMap.createMap(hero);

    }

    private static void monsterCheck(int x, int y){
        if (gameMap.getGameMap()[y][x] >= 2)
        {
            ApplicationControls.setMonster(MonsterFactory.generateMonster());
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

    public static int[][] getGameLoopMap() {
        return gameMap.getGameMap();
    }

    enum GameLoopMenuInstruction {
        save_exit,
        exit,
        gui,
        inventory,
        north,
        south,
        east,
        west
    }


    public static void gameLoopMenuCommands(){
        int instructionIndex = -1;

        if (ApplicationControls.status == GAME_LOOP) {

        if (!mapReset) {
            gameMap.createMap(getHero());
            mapReset = true;
        }
            GameMap.updateHeroLoc(ApplicationControls.getHero());

            displayGameLoopMenu();
            instructions = ApplicationControls.getInstructions();
            GameLoopOutput.gameLoopGreating();
            GameLoopOutput.gameLoopMapDisplay();
        }

        try {
            while(ApplicationControls.status == GAME_LOOP) {
                getHero().isPlayerDead();
                for (int i = 0; i < instructions.size(); i++) {
                    instructionIndex = i;
                    /* IMPORTANT: remove instruction after use. */

                    if (instructions.get(i) != null) {

                        switch (GameLoopMenuInstruction.valueOf(instructions.get(i).toLowerCase())) {

                            case exit:
                                ApplicationControls.setIsRunning(false);
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case north:
                                moveHero(0);
                                break;

                            case south:
                                moveHero(1);
                                break;

                            case east:
                                moveHero(2);
                                break;

                            case west:
                                moveHero(3);
                                break;

                            case save_exit:
                                ApplicationControls.setIsRunning(false);
                                SavedGameLoader.saveGame();
                                Controllers.ApplicationControls.closeApplication();
                                break;

                            case gui:
                                BaseWindow.showBaseWindow();
                                break;

                            case inventory:
                                ApplicationControls.status = INVENTORY_MENU;
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

            ApplicationControls.removeInstructions(instructions.get(instructionIndex));
            gameLoopMenuCommands();

        } finally {
            instructions = ApplicationControls.getInstructions();
        }
    }
}
