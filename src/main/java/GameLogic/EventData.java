package GameLogic;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static GameLogic.ApplicationControls.addInstructions;

public class EventData {

	static public Scanner input = new Scanner(System.in);

	public static void readStdinAsync(){

		new Thread(new Runnable() {

			public void run() {
					while (ApplicationControls.getIsRunning() && input.hasNext()) {
						addInstructions(input.nextLine());
					}
					System.out.println("ended stdin");
					Thread.interrupted();
			}
		}).start();
	}



}
