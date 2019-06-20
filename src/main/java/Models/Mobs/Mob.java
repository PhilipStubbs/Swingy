package Models.Mobs;

import Models.Artifacts.Artifact;
import Models.Items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob {
    protected String name;
    protected int level;
    protected int attackPnts;
    protected int defencePnts;
    protected int hitPnts;
    protected int experiencePnts;
    protected int maxHitPnts;
    protected int maxAttackPnts;
    protected int maxDefencePnts;
    protected int maxExperiencePnts;
    protected List<Item> backpack = new ArrayList<Item>();
    protected List<Artifact> equipped = new ArrayList<Artifact>();

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

    public Mob(String name, int level, int attackPnts, int defencePnts, int hitPnts, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, int maxExperiencePnts, List<Item> backpack, List<Artifact> equipped) {
        this.name = name;
        this.level = level;
        this.attackPnts = attackPnts;
        this.defencePnts = defencePnts;
        this.hitPnts = hitPnts;
        this.experiencePnts = experiencePnts;
        this.maxHitPnts = maxHitPnts;
        this.maxAttackPnts = maxAttackPnts;
        this.maxDefencePnts = maxDefencePnts;
        this.maxExperiencePnts = maxExperiencePnts;
        this.backpack = backpack;
        this.equipped = equipped;
    }

    public void attack(){}

    public void defend(){}

    public void takeDamage(){}

    public void gainHitPnts(){}
}
