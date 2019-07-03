package Views.Terminal;

import Controllers.ApplicationControls;
import Models.Mobs.Hero;
import Models.Mobs.Monster;

import java.util.List;

public class FightMenuOutput {

	public static void fightInstructions(){
		System.out.println("'fight', 'run', 'simulate'");
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
		Hero hero = ApplicationControls.getHero();
		Monster monster = ApplicationControls.getMonster();
		System.out.println("\nYour stats \n" +
		"Health: " +hero.getHitPnts() + "/" + hero.getMaxHitPnts()+
		"\nDefence: " +hero.getDefencePnts() + "/" + hero.getMaxDefencePnts());
		System.out.println("\n----------------------");

		System.out.println("Their stats \n" +
		"Health: " + monster.getHitPnts() + "/" + monster.getMaxHitPnts() +
		"\nDefence: " + monster.getDefencePnts() + "/" + monster.getMaxDefencePnts());
		System.out.println("\n----------------------");

		fightInstructions();

	}

}
