package Models.Mobs.Builders;

import Models.Artifacts.Artifact;
import Models.Mobs.Fighter;

import java.util.List;

public class FighterBuilder {
    private String name;
    private int level;
    private int experiencePnts;
    private int maxHitPnts;
    private int maxAttackPnts;
    private int maxDefencePnts;
    private List<Artifact>[] backpack;
    private Artifact[] equipped;

    public FighterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FighterBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public FighterBuilder setExperiencePnts(int experiencePnts) {
        this.experiencePnts = experiencePnts;
        return this;
    }

    public FighterBuilder setMaxHitPnts(int maxHitPnts) {
        this.maxHitPnts = maxHitPnts;
        return this;
    }

    public FighterBuilder setMaxAttackPnts(int maxAttackPnts) {
        this.maxAttackPnts = maxAttackPnts;
        return this;
    }

    public FighterBuilder setMaxDefencePnts(int maxDefencePnts) {
        this.maxDefencePnts = maxDefencePnts;
        return this;
    }

    public FighterBuilder setBackpack(List<Artifact>[] backpack) {
        this.backpack = backpack;
        return this;
    }

    public FighterBuilder setEquipped(Artifact[] equipped) {
        this.equipped = equipped;
        return this;
    }

    public Fighter createFighter() {
        return new Fighter(name, level, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }
}