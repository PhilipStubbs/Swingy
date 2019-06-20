package GameLogic;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventData {

	public EventData() {
	}

	private static List<String> instructions = new ArrayList<String>();
	private static Boolean isRunning = true;


	public static List<String> getOutput() {
		return instructions;
	}

	public static Boolean getIsRunning() {
		return isRunning;
	}

	public static void setIsRunning(Boolean isRunning) {
		EventData.isRunning = isRunning;
	}

	public static void readStdinAsync(){

		new Thread(new Runnable() {
			Scanner input = new Scanner(System.in);

			public void run() {
					while (isRunning && input.hasNext()) {
						addInstructions(input.nextLine());
					}
					return;
			}
		}).start();
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
