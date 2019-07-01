package Models.Mobs;

import Models.Artifacts.Artifact;
import Models.Global;

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
    protected List<Artifact>[] backpack;
    protected Artifact[] equipped;
    protected int x;
    protected int y;
    protected int prevX;
    protected int prevY;


    Mob() {
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

    Mob(String name, int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        this.name = name;

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

        this.experiencePnts = experiencePnts;
        maxExperiencePnts = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
        this.backpack = backpack;
        this.equipped = equipped;
    }


    public void takeDamage(int damage){
        if (defencePnts > 0 && defencePnts - damage >= 0){
            defencePnts -= damage;
        } else if (defencePnts > 0 && defencePnts - damage < 0){
            damage -=defencePnts;
            defencePnts = 0;
            hitPnts -= damage;
        }
         else {
            hitPnts -=damage;
        }
    }

    public void gainHitPnts(int health){
        this.hitPnts += health;
    }

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

    public List<Artifact>[] getBackpack() {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPrevXY(int x, int y) {
        setPrevX(x);
        setPrevY(y);
    }

    public int[] getLoc(){
        int[] loc = {this.y,this.x};
        return (loc);
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }
}
