package Controllers;

import Models.Missions.MissionTypes.Mission;
import Models.Mobs.Hero;
import Models.Mobs.Monster;
import Views.Gui.BaseWindow;

import java.util.ArrayList;
import java.util.List;

public class ApplicationControls {

	/*
	* 0 = main menu */
	public static int status;
	private static Mission mission;
	private static Boolean isRunning;
	private static List<String> instructions = new ArrayList<String>();
	private static Hero hero;
	private static Monster monster;
	private static List<String> fight = new ArrayList<String>();



	/*-----------------------------------------------------------------------------------------------------*/
	/* Application controls */
	public static Boolean getIsRunning() {
		return isRunning;
	}

	public static void setIsRunning(Boolean isRunning) {
		ApplicationControls.isRunning = isRunning;
	}

	static public void createWindow(){
		BaseWindow.createBaseWindow();
		isRunning = true;
	}

	static public void closeApplication(){
		BaseWindow.destroyWindow();
		System.exit(0);
	}

	/*-----------------------------------------------------------------------------------------------------*/
	/* Instruction controls */
	public static List<String> getInstructions() {
		return instructions;
	}

	public static void addInstructions(String input) {
		instructions.add(input);
	}

	public static void removeInstructions(String input) {
		instructions.remove(input);
	}


	/*-----------------------------------------------------------------------------------------------------*/
	/* Hero controls */
	public static Hero getHero() {
		return hero;
	}

	public static void setHero(Hero hero) {
		ApplicationControls.hero = hero;
	}


	/*-----------------------------------------------------------------------------------------------------*/
	/* Monster controls */

	public static Monster getMonster() {
		return monster;
	}

	public static void setMonster(Monster monster) {
		ApplicationControls.monster = monster;
	}

	/*-----------------------------------------------------------------------------------------------------*/
	/* Fight controls */

	public static List<String> getFight() {
		return fight;
	}
	public static void addToFight(String fight) {
		ApplicationControls.fight.add(fight);
	}

	public static void setFight(List<String> fight) {
		ApplicationControls.fight = fight;
	}

	/*-----------------------------------------------------------------------------------------------------*/
	/* Missions control */

	public static Mission getMission() {
		return mission;
	}

	public static void setMission(Mission mission) {
		ApplicationControls.mission = mission;
	}

	public static String saveMissoinString() {
		Mission mission = getMission();
		String save = "";
		save += mission.getClass().getSimpleName() + " "+
			mission.getProgess() + " " +
			mission.getGoal() + " " +
			mission.getDescription().replaceAll(" ", "_") + " " +
			mission.getReward();
		return save;
	}
}

//(int progess, int goal, String description, int reward){