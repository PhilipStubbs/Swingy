package Models.Mobs;

import Models.Artifacts.Artifact;
import Models.Items.Item;

import java.util.List;

import static Models.Artifacts.Artifact.*;

public class Hero extends Mob {

    public Hero() {
        super();
    }

    public Hero(String name, String mobClass,int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact> backpack, Artifact[] equipped) {
        super(name, mobClass,level, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, backpack, equipped);
    }

    public void gainExperince(int gain){
        experiencePnts += gain;

        if (experiencePnts >= maxExperiencePnts) {
            this.levelUp();
        }
    }

    public void levelUp(){

        level++;

        maxAttackPnts += 2;
        attackPnts = maxAttackPnts;
        if (equipped[WEAPON] != null)
            attackPnts += equipped[WEAPON].getBuff();

        maxDefencePnts += 2;
        defencePnts = maxDefencePnts;
        if (equipped[ARMOUR] != null)
            defencePnts += equipped[ARMOUR].getBuff();

        maxHitPnts += 2;
        hitPnts = maxHitPnts;
        if (equipped[HELM] != null)
            hitPnts += equipped[HELM].getBuff();

        experiencePnts -= maxExperiencePnts;
        maxExperiencePnts = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
    }

    public void unequipArtifact(){}
    public void lootEnemy(){}
    public void equipArtifact(){}
    public void save(){}
    public void load(){}

    @Override
    public String toString() {
        return
                "'" + name + '\'' +
                ", level=" + level +
                ", attackPnts=" + attackPnts +
                ", defencePnts=" + defencePnts +
                ", hitPnts=" + hitPnts +
                ", experiencePnts=" + experiencePnts +
                ", maxHitPnts=" + maxHitPnts +
                ", maxAttackPnts=" + maxAttackPnts +
                ", maxDefencePnts=" + maxDefencePnts +
                ", maxExperiencePnts=" + maxExperiencePnts +
                ", backpack=" + backpack +
                ", equipped=" + equipped;
    }

    public String saveString(){
        String backpackString = "";
        for(int i = 0; i < backpack.size(); i++){
            backpackString += (backpack.get(i).getName() + " " + backpack.get(i).getName()+ " " +backpack.get(i).getType());
        }

     return name + " " +
            mobClass + " " +
            level + " " +
            experiencePnts + " " +
            hitPnts + " " +
            attackPnts + " " +
            defencePnts + " " +
             this.equipped.length + " " +
             this.equipped[0].getName() + " " +
             this.equipped[0].getBuff() + " " +
             this.equipped[1].getName() + " " +
             this.equipped[1].getBuff() + " " +
             this.equipped[2].getName() + " " +
             this.equipped[2].getBuff() + " " +
             this.backpack.size() + " " +
            backpackString ;
    }
}