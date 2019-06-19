package GameLogic;


import java.util.ArrayList;
import java.util.List;

public class EventData {

	private static List<String> output = new ArrayList<String>();

	public static List<String> getOutput() {
		return output;
	}

	public static void addOutput(String input) {
		EventData.output.add(input);
	}

	public static void setOutput(List<String> output) {
		EventData.output = output;
	}
}
