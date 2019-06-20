package Models.Mobs;

import Models.Artifacts.Artifact;
import Models.Items.Item;

import java.util.List;

public class Hero extends Mob {

    public Hero() {
        super();
    }

    public Hero(String name, int level, int attackPnts, int defencePnts, int hitPnts, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, int maxExperiencePnts, List<Item> backpack, List<Artifact> equipped) {
        super(name, level, attackPnts, defencePnts, hitPnts, experiencePnts, maxHitPnts, maxAttackPnts, maxDefencePnts, maxExperiencePnts, backpack, equipped);
    }

    public void gainExperince(){}
    public void levelUp(){}
    public void unequipArtifact(){}
    public void lootEnemy(){}
    public void equipArtifact(){}
    public void save(){}
    public void load(){}

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", attackPnts=" + attackPnts +
                ", defencePnts=" + defencePnts +
                ", hitPnts=" + hitPnts +
                ", experiencePnts=" + experiencePnts +
                ", backpack=" + backpack +
                ", equipped=" + equipped +
                '}';
    }


}
