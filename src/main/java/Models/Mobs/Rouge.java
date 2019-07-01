package Models.Mobs;

import Models.Artifacts.Artifact;

import java.util.List;

public class Rouge extends Hero {
    public Rouge(String name, int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name, level, experiencePnts, maxHitPnts, maxAttackPnts + 3, maxDefencePnts + 2, backpack, equipped);
    }
}
