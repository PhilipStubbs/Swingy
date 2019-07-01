package Models.Missions;

import Models.Missions.MissionTypes.EnemyHunter;
import Models.Missions.MissionTypes.LevelCompletion;
import Models.Missions.MissionTypes.Mission;

import java.util.Random;

public class MissionFactory {

	public static Mission Mission(){
		Random rn = new Random();

		if (rn.nextInt() % 2 == 0){
			int goal = rn.nextInt(10);
			return new EnemyHunter(0, goal + 2, "Kill Enemies", goal + 10);
		} else {
			int goal = rn.nextInt(10);
			return new LevelCompletion(0, goal + 2, "Finish Levels", goal + 10);
		}
	}
}
