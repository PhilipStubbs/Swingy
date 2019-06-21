package GameLogic;

import GameLogic.Parsing.EventParsing;

public class Main {

		public static void main(String[] args) {
			System.out.println("Welcome to Swingy");
			System.out.println("Main Menu: \"new game\", \"continue\", \"exit\", \"gui\" ");

			ApplicationControls.createWindow();
			ApplicationControls.status = 0;

			EventParsing.instructionParseAsync();

			EventData.readStdinAsync();

			System.out.println("End of main");

		}
}
