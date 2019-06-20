package GameLogic;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventData {

	private static List<String> instructions = new ArrayList<String>();


	public static List<String> getOutput() {
		return instructions;
	}


	public static void readSTDinAsync(){

//		new Thread(new Runnable() {
			Scanner input = new Scanner(System.in);

//			public void run() {
				if (input.hasNext()) {
					addInstructions(input.nextLine());
				}
//			}
//		}).start();
	}

	public static void addInstructions(String input) {
		instructions.add(input);
	}

	public static void removeInstructions(String input) {
		instructions.remove(input);
	}


	public static void setOutput(List<String> output) {
		EventData.instructions = output;
	}
}
