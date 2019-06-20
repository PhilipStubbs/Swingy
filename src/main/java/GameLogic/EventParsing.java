package GameLogic;

import java.util.List;

public class EventParsing {

	public EventParsing(){
	}

	enum Instruction {
		print,
		exit,
		test
	}

	static void instructionParseAsync() {
		new Thread(new Runnable() {
			List<String> instructions;
			int instructionIndex = 0;
			public void run() {
				try {
					while(EventData.getIsRunning()) {
						instructions = EventData.getOutput();
						for (int i=0; i < instructions.size(); i++) {
							instructionIndex = i;
							switch (Instruction.valueOf(instructions.get(i).toLowerCase())) {


								case print: {
									System.out.println(EventData.getOutput());
									EventData.removeInstructions(instructions.get(i));
									break;
								}
								case exit: {
									EventData.setIsRunning(false);
									System.out.println("killing program");
									return;
								}
								default:
							}
						}
					}
				} catch (IllegalArgumentException e){
					System.out.println("Invalid instruction:"+instructions.get(instructionIndex));
					EventData.removeInstructions(instructions.get(instructionIndex));
					instructionParseAsync();
				}
			}
		}).start();

	}
}