package Models.Mobs;

import Models.Artifacts.Artifact;

import java.util.List;

public class Hunter extends Hero{
    public Hunter(String name,  int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name, level, experiencePnts, maxHitPnts + 5, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }
}
