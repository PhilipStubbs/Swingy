package Controllers;

import Controllers.Parsing.EventParsing;

public class Main {

		public static void main(String[] args) {
			System.out.println("Welcome to Swingy");

			ApplicationControls.createWindow();
			ApplicationControls.status = 0;

//			EventParsing.instructionParseAsync();

			EventData.readStdinAsync();
			System.out.println("Main Menu: \"new_game\", \"continue_game\", \"exit\", \"gui\" ");
			EventParsing.instructionParse();





		}
}
