package GameLogic;

import Models.Mobs.Hero;
import Views.Gui.BaseWindow;
import Views.Gui.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class ApplicationControls {

	/*
	* 0 = main menu */
	public static int status;

	private static Boolean isRunning;
	private static List<String> instructions = new ArrayList<String>();
	private static Hero hero;

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
		MainMenu.displayMainMenu();
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
}
