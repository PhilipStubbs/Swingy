package Models.Mobs.Builders;

import Models.Artifacts.Artifact;
import Models.Mobs.Hero;

import java.util.List;

public class HeroBuilder {
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

    public HeroBuilder setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
        return this;
    }

    public HeroBuilder setCurrentDefence(int currentDefence) {
        this.currentDefence = currentDefence;
        return this;
    }

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public HeroBuilder setExperiencePnts(int experiencePnts) {
        this.experiencePnts = experiencePnts;
        return this;
    }

    public HeroBuilder setMaxHitPnts(int maxHitPnts) {
        this.maxHitPnts = maxHitPnts;
        return this;
    }

    public HeroBuilder setMaxAttackPnts(int maxAttackPnts) {
        this.maxAttackPnts = maxAttackPnts;
        return this;
    }

    public HeroBuilder setMaxDefencePnts(int maxDefencePnts) {
        this.maxDefencePnts = maxDefencePnts;
        return this;
    }

    public HeroBuilder setBackpack(List<Artifact>[] backpack) {
        this.backpack = backpack;
        return this;
    }

    public HeroBuilder setEquipped(Artifact[] equipped) {
        this.equipped = equipped;
        return this;
    }

    public Hero createHero() {
        return new Hero(name, level, experiencePnts, maxHitPnts,currentHp, maxAttackPnts, maxDefencePnts,currentDefence, backpack, equipped);
    }
}