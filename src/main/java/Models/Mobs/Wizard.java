package Models.Mobs;

import Models.Artifacts.Artifact;

import java.util.List;

public class Wizard extends Hero {

    public Wizard(String name, int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name, level, experiencePnts, maxHitPnts + 1, maxAttackPnts + 1, maxDefencePnts + 3, backpack, equipped);
    }
}
