package Models.Mobs;

import Models.Artifacts.Artifact;

import java.util.List;

public class Fighter extends Hero {
    public Fighter(String name,  int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name, level, experiencePnts, maxHitPnts + 3, maxAttackPnts + 2, maxDefencePnts, backpack, equipped);
    }
}
