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
		Hero hero = ApplicationControls.getHero();
		Monster monster = ApplicationControls.getMonster();
		System.out.println("Your stats" +
		"Health: " +hero.getHitPnts() + "/" + hero.getMaxHitPnts()+
		"\nDefence: " +hero.getDefencePnts() + "/" + hero.getMaxDefencePnts());

		System.out.println("Their stats" +
		"Health: " + monster.getHitPnts() + "/" + monster.getMaxHitPnts() +
		"\nDefence: " + monster.getDefencePnts() + "/" + monster.getMaxDefencePnts());
	}
}
