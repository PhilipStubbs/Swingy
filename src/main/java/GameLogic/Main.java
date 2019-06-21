package GameLogic;

import Views.BaseWindow;
import Views.MainMenu;


public class Main {





		public static void main(String[] args) {
			System.out.println("Starting");

			MainMenu mainMenu = new MainMenu();

			System.out.println("Starting");
			BaseWindow.createBaseWindow();
			mainMenu.displayMainMenu();
			EventData.setIsRunning(true);
			EventParsing.instructionParseAsync();

			EventData.readStdinAsync();

			System.out.println("End of main");

		}
}
