package GameLogic;

import Views.MainMenu;

public class Main {

		public static void main(String[] args) {
			System.out.println("Starting");

			MainMenu mainMenu = new MainMenu();

			System.out.println("Starting");
			mainMenu.displayMainMenu();
//			mainMenu
//			EventParsing parser = new EventParsing();
//			EventData eventData = new EventData();

			EventParsing.instructionParseAsync();

			EventData.readStdinAsync();

			System.out.println("End of program");

		}
}
