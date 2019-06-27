package Views.Terminal;

import Controllers.ApplicationControls;
import Models.Mobs.Hero;
import Models.Mobs.Monster;

import java.util.List;

public class FightMenuOutput {

	public static void fightInstructions(){
		System.out.println("'fight' or 'run'");
	}

	public static void fightIntro(Monster monster){
		System.out.println("you have encountered a "+ monster.getName());
	}

	public static void fightOutput() {
		List<String> fight = ApplicationControls.getFight();
		for (int i = 0; i < fight.size(); i++) {
			System.out.println(fight.get(i));
		}
		fight.clear();
	}
}
