package Models.Mobs.Builders;

import Models.Artifacts.Artifact;
import Models.Mobs.Rouge;

import java.util.List;

public class RougeBuilder {
    private String name;
    private int level;
    private int experiencePnts;
    private int maxHitPnts;
    private int maxAttackPnts;
    private int maxDefencePnts;
    private List<Artifact>[] backpack;
    private Artifact[] equipped;
    private int currentHp;
    private int currentDefence;

    public RougeBuilder setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
        return this;
    }

    public RougeBuilder setCurrentDefence(int currentDefence) {
        this.currentDefence = currentDefence;
        return this;
    }

    public RougeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RougeBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public RougeBuilder setExperiencePnts(int experiencePnts) {
        this.experiencePnts = experiencePnts;
        return this;
    }

    public RougeBuilder setMaxHitPnts(int maxHitPnts) {
        this.maxHitPnts = maxHitPnts;
        return this;
    }

    public RougeBuilder setMaxAttackPnts(int maxAttackPnts) {
        this.maxAttackPnts = maxAttackPnts;
        return this;
    }

    public RougeBuilder setMaxDefencePnts(int maxDefencePnts) {
        this.maxDefencePnts = maxDefencePnts;
        return this;
    }

    public RougeBuilder setBackpack(List<Artifact>[] backpack) {
        this.backpack = backpack;
        return this;
    }

    public RougeBuilder setEquipped(Artifact[] equipped) {
        this.equipped = equipped;
        return this;
    }

    public Rouge createRouge() {
        return new Rouge(name, level, experiencePnts, maxHitPnts,currentHp, maxAttackPnts, maxDefencePnts, currentDefence, backpack, equipped);
    }
}