package Models.Mobs;

import Models.Artifacts.Artifact;

import java.util.List;

public class Monster extends Mob {

    public Monster(String name, String mobClass, int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name, mobClass,level, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }

}
