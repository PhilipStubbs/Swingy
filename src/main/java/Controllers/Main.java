package Controllers;

import Controllers.Parsing.EventParsing;
import static Views.Gui.BaseWindow.hideBaseWindow;

public class Main {

		public static void main(String[] args) {
			System.out.println("Welcome to Swingy");

			ApplicationControls.createWindow();
			ApplicationControls.status = 0;
			if (args.length >= 1 && args[0].contains("console")){
				hideBaseWindow();
			}
//			EventParsing.instructionParseAsync();


			EventData.readStdinAsync();
			System.out.println("Main Menu: \"new_game\", \"continue_game\", \"exit\", \"gui\" ");
			EventParsing.instructionParse();





		}
}
