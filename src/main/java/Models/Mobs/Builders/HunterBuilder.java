package Models.Mobs.Builders;

import Models.Artifacts.Artifact;
import Models.Mobs.Hunter;

import java.util.List;

public class HunterBuilder {
    private String name;
    private int level;
    private int experiencePnts;
    private int maxHitPnts;
    private int maxAttackPnts;
    private int maxDefencePnts;
    private List<Artifact>[] backpack;
    private Artifact[] equipped;

    public HunterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HunterBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public HunterBuilder setExperiencePnts(int experiencePnts) {
        this.experiencePnts = experiencePnts;
        return this;
    }

    public HunterBuilder setMaxHitPnts(int maxHitPnts) {
        this.maxHitPnts = maxHitPnts;
        return this;
    }

    public HunterBuilder setMaxAttackPnts(int maxAttackPnts) {
        this.maxAttackPnts = maxAttackPnts;
        return this;
    }

    public HunterBuilder setMaxDefencePnts(int maxDefencePnts) {
        this.maxDefencePnts = maxDefencePnts;
        return this;
    }

    public HunterBuilder setBackpack(List<Artifact>[] backpack) {
        this.backpack = backpack;
        return this;
    }

    public HunterBuilder setEquipped(Artifact[] equipped) {
        this.equipped = equipped;
        return this;
    }

    public Hunter createHunter() {
        return new Hunter(name, level, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }
}