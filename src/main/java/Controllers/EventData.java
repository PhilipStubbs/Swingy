package Controllers;


import java.util.Scanner;

import static Controllers.ApplicationControls.addInstructions;

public class EventData {

	static public Scanner input = new Scanner(System.in);

	public static void readStdinAsync(){
		new Thread(new Runnable() {

			public void run() {
					while (ApplicationControls.getIsRunning() && input.hasNext()) {
						addInstructions(input.nextLine().toLowerCase());
					}
					Thread.interrupted();
			}
		}).start();
	}
}
