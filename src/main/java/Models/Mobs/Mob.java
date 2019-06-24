package Models.Mobs;

import Models.Artifacts.Artifact;
import Models.Global;
import Models.Items.Item;

import java.util.ArrayList;
import java.util.List;


public abstract class Mob extends Global {

    protected String name;
    protected String mobClass;
    protected int level;
    protected int attackPnts;
    protected int defencePnts;
    protected int hitPnts;
    protected int experiencePnts;
    protected int maxHitPnts;
    protected int maxAttackPnts;
    protected int maxDefencePnts;
    protected int maxExperiencePnts;
    protected List<Artifact> backpack = new ArrayList<Artifact>();
    protected Artifact[] equipped = new Artifact[3];

    public Mob() {
        this.name = "undefined";
        this.level = 0;
        this.attackPnts = 0;
        this.defencePnts = 0;
        this.hitPnts = 0;
        this.experiencePnts = 0;
        this.backpack = null;
        this.equipped = null;
    }

    public String getName() {
        return name;
    }

    public Mob(String name, String mobClass ,int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact> backpack, Artifact[] equipped) {
        this.name = name;
        this.mobClass = mobClass;

        this.level = level;

        this.maxAttackPnts = maxAttackPnts;
        attackPnts = maxAttackPnts;
        if (equipped[WEAPON] != null)
            attackPnts += equipped[WEAPON].getBuff();

        this.maxDefencePnts = maxDefencePnts;
        defencePnts = maxDefencePnts;
        if (equipped[ARMOUR] != null)
            defencePnts += equipped[ARMOUR].getBuff();

        this.maxHitPnts = maxHitPnts;
        hitPnts = maxHitPnts;
        if (equipped[HELM] != null)
            hitPnts += equipped[HELM].getBuff();

        this.experiencePnts -= maxExperiencePnts;
        maxExperiencePnts = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
        this.backpack = backpack;
        this.equipped = equipped;
    }

    public void attack(){}

    public void defend(){}

    public void takeDamage(){}

    public void gainHitPnts(){}

    public int getAttackPnts() {
        return attackPnts;
    }

    public int getDefencePnts() {
        return defencePnts;
    }

    public int getHitPnts() {
        return hitPnts;
    }

    public int getExperiencePnts() {
        return experiencePnts;
    }

    public int getMaxHitPnts() {
        return maxHitPnts;
    }

    public int getMaxAttackPnts() {
        return maxAttackPnts;
    }

    public int getMaxDefencePnts() {
        return maxDefencePnts;
    }

    public int getMaxExperiencePnts() {
        return maxExperiencePnts;
    }

    public List<Artifact> getBackpack() {
        return backpack;
    }

    public Artifact[] getEquipped() {
        return equipped;
    }

    public int getLevel() {
        return level;
    }

    public String getMobClass() {
        return mobClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobClass(String mobClass) {
        this.mobClass = mobClass;
    }
}
