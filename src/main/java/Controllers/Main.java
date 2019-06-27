package Controllers;

import Controllers.Parsing.EventParsing;

public class Main {

		public static void main(String[] args) {
			System.out.println("Welcome to Swingy");

			ApplicationControls.createWindow();
			ApplicationControls.status = 0;

			EventParsing.instructionParseAsync();

			EventData.readStdinAsync();
//			EventParsing.instructionParse();

			// TODO check that error on the fight screen where I cant select run

			System.out.println("Main Menu: \"new_game\", \"continue_game\", \"exit\", \"gui\" ");



		}
}
