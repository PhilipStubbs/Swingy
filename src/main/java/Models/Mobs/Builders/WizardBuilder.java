package Models.Mobs.Builders;

import Models.Artifacts.Artifact;
import Models.Mobs.Wizard;

import java.util.List;

public class WizardBuilder {
    private String name;
    private int level;
    private int experiencePnts;
    private int maxHitPnts;
    private int maxAttackPnts;
    private int maxDefencePnts;
    private List<Artifact>[] backpack;
    private Artifact[] equipped;

    public WizardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WizardBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public WizardBuilder setExperiencePnts(int experiencePnts) {
        this.experiencePnts = experiencePnts;
        return this;
    }

    public WizardBuilder setMaxHitPnts(int maxHitPnts) {
        this.maxHitPnts = maxHitPnts;
        return this;
    }

    public WizardBuilder setMaxAttackPnts(int maxAttackPnts) {
        this.maxAttackPnts = maxAttackPnts;
        return this;
    }

    public WizardBuilder setMaxDefencePnts(int maxDefencePnts) {
        this.maxDefencePnts = maxDefencePnts;
        return this;
    }

    public WizardBuilder setBackpack(List<Artifact>[] backpack) {
        this.backpack = backpack;
        return this;
    }

    public WizardBuilder setEquipped(Artifact[] equipped) {
        this.equipped = equipped;
        return this;
    }

    public Wizard createWizard() {
        return new Wizard(name, level, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }
}