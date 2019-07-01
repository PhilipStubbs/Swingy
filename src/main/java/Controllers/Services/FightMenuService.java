package Controllers.Services;

import Controllers.ApplicationControls;
import Models.Global;
import Models.Missions.MissionFactory;
import Models.Missions.MissionTypes.Mission;
import Models.Mobs.Hero;
import Models.Mobs.Monster;

import java.util.Random;

import static Controllers.ApplicationControls.getHero;
import static Controllers.ApplicationControls.getMonster;
import static Models.GameMap.GameMap.setHeroLocation;
import static java.lang.Math.abs;

public class FightMenuService extends Global {

	public static void fightMonster() {
		Hero hero = getHero();
		Monster monster = getMonster();
		fightMonsterLogic(hero, monster);
	}

	public static void fightMonsterLogic(Hero hero, Monster monster) {
		Random rn = new Random();
		if (rn.nextInt() % 2 == 0){
			monster.takeDamage(hero.getAttackPnts());
			ApplicationControls.addToFight("You hit the "+monster.getName()+" for " + hero.getAttackPnts()+ " damage.");

		} else {
			ApplicationControls.addToFight("you missed");
		}
		if (rn.nextInt() % 2 == 0){
			hero.takeDamage(monster.getAttackPnts());
			ApplicationControls.addToFight(monster.getName() + " hit you for " + monster.getAttackPnts()+ " damage." );
		} else {
			ApplicationControls.addToFight(monster.getName() +" missed");
		}
	}

	public static void monsterDeadCheck(){
		Monster monster = getMonster();
		if (monster.isMonsterDead()) {
			Mission mission = ApplicationControls.getMission();
			if (mission.getClass().getSimpleName().contains("EnemyHunter")) {
				mission.addProgess();
				mission.addReward(monster.getMaxAttackPnts() + monster.getMaxHitPnts());
				if (mission.getProgess() >= mission.getGoal()) {
					getHero().gainExperince(mission.getReward() % 2);
					ApplicationControls.setMission(MissionFactory.Mission());
				}
			}
			setHeroLocation(getHero());
			ApplicationControls.addToFight(monster.getName()+ " is dead");
			getHero().addEpForMonsterKill(monster);
			Random rn = new Random();
			if (abs(rn.nextInt() % 2) == 0) {
				ApplicationControls.getHero().lootEnemy();
				ApplicationControls.status = LOOT;
			} else {
				ApplicationControls.status = GAME_LOOP;
			}
		}
	}

	public static boolean runFromMonster(){
		Hero hero = getHero();
		Monster monster = getMonster();
		Random rn = new Random();
		if (rn.nextInt() % 2 == 0){
			ApplicationControls.addToFight("you were able to run.");
			ApplicationControls.status = GAME_LOOP;
			return true;
		} else {
			hero.takeDamage(monster.getAttackPnts());
			ApplicationControls.addToFight("you were not able to run.");
			return false;
		}
	}


	public static void simulateFight(){
		Hero hero = getHero();
		Monster monster = getMonster();
		while(!hero.isPlayerDead() && !monster.isMonsterDead()){
			fightMonsterLogic(hero, monster);
		}
		monsterDeadCheck();
	}

}
