package Models.Mobs;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;

import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Monster extends Mob {

    public Monster(String name, String mobClass, int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name, mobClass,level, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }

    public boolean isMonsterDead() {
        Random rn = new Random();
        if (this.hitPnts <= 0){
            if (abs(rn.nextInt() % 2 ) == 0) {
                ApplicationControls.getHero().lootEnemy();
                ApplicationControls.status = LOOT;
            } else{
                ApplicationControls.status = GAME_LOOP;
            }
            return (true);
        }
        return (false);
    }
}
