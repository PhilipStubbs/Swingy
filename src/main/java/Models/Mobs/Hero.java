package Models.Mobs;

import Controllers.ApplicationControls;
import Models.Artifacts.Artifact;
import Models.ItemsFactory.ItemFactory;
import Models.Missions.MissionTypes.Mission;

import java.util.List;

public class Hero extends Mob {

    public Hero() {
        super();
    }

    public Hero(String name, int level, int experiencePnts, int maxHitPnts, int maxAttackPnts, int maxDefencePnts, List<Artifact>[] backpack, Artifact[] equipped) {
        super(name,
        level,
        experiencePnts,
        maxHitPnts + (equipped[HELM] != null ? equipped[HELM].getBuff() : 0),
        maxAttackPnts + (equipped[WEAPON] != null ? equipped[WEAPON].getBuff() : 0),
        maxDefencePnts + (equipped[WEAPON] != null ? equipped[WEAPON].getBuff() : 0),
        backpack,
        equipped);
    }

    private Mission currentMission;
    private String latestLoot = "";

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

        maxDefencePnts += 2;
        defencePnts = maxDefencePnts;

        maxHitPnts += 2;
        hitPnts = maxHitPnts;

        experiencePnts -= maxExperiencePnts;
        maxExperiencePnts = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
    }

    public void lootEnemy(){
        Artifact newItem = ItemFactory.generateLoot(this);
        backpack[newItem.getType()].add(newItem);
        System.out.println("You found " + newItem.getDetails());
    }

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
        int backpackSize = backpack[HELM].size() + backpack[ARMOUR].size() + backpack[WEAPON].size();
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < backpack[x].size(); i++) {
                backpackString += (backpack[x].get(i).getName() + " " + backpack[x].get(i).getBuff() + " " + backpack[x].get(i).getType() + " ");
            }
        }

     return name + " " +
            getClass().getSimpleName() + " " +
            level + " " +
            experiencePnts + " " +
            hitPnts + " " +
            attackPnts + " " +
            defencePnts + " " +
             this.equipped.length + " " +
             this.equipped[HELM].getName() + " " +
             this.equipped[HELM].getBuff() + " " +
             this.equipped[ARMOUR].getName() + " " +
             this.equipped[ARMOUR].getBuff() + " " +
             this.equipped[WEAPON].getName() + " " +
             this.equipped[WEAPON].getBuff() + " " +
             backpackSize + " " +
            backpackString ;
    }

    public void addToBackpack(Artifact item){
        backpack[item.getType()].add(item);
    }



    public void equipHelm(int helmIndex){
        Artifact newHelm = backpack[HELM].get(helmIndex);
        Artifact oldHelm = equipped[HELM];
        this.equipped[HELM] = newHelm;

        this.hitPnts -= oldHelm.getBuff();
        this.hitPnts += newHelm.getBuff();

        this.maxHitPnts -= oldHelm.getBuff();
        this.maxHitPnts += newHelm.getBuff();

        backpack[HELM].remove(helmIndex);
        addToBackpack(oldHelm);

    }

    public void equipArmour(int ArmourIndex){
        Artifact newArmour = backpack[ARMOUR].get(ArmourIndex);
        Artifact oldArmour = equipped[ARMOUR];
        this.equipped[ARMOUR] = newArmour;

        this.defencePnts -= oldArmour.getBuff();
        this.defencePnts += newArmour.getBuff();

        this.maxDefencePnts -= oldArmour.getBuff();
        this.maxDefencePnts += newArmour.getBuff();

        backpack[ARMOUR].remove(ArmourIndex);
        addToBackpack(oldArmour);
    }

    public void equipWeapon(int WeaponIndex){
        Artifact newWeapon = backpack[WEAPON].get(WeaponIndex);
        Artifact oldWeapon = equipped[WEAPON];
        this.equipped[WEAPON] = newWeapon;

        attackPnts -= oldWeapon.getBuff();
        attackPnts += newWeapon.getBuff();

        maxAttackPnts -= oldWeapon.getBuff();
        maxAttackPnts += newWeapon.getBuff();

        backpack[WEAPON].remove(WeaponIndex);
        addToBackpack(oldWeapon);

    }

    public int[] findMiddleOfMap(int mapSize){
        int[] heroLoc = {mapSize /2, mapSize /2};
        return heroLoc;
    }

    public boolean isPlayerDead() {

        if (hitPnts <= 0){
            ApplicationControls.status = DEAD;
            return(true);
        }
        return false;
    }

    public void addEpForMonsterKill(Monster monster){
        gainExperince(monster.getMaxAttackPnts() + monster.getMaxHitPnts());
    }

    public String getLatestLoot() {
        return latestLoot;
    }

    public void setLatestLoot(String latestLoot) {
        this.latestLoot = latestLoot;
    }


    public Mission getCurrentMission() {
        return currentMission;
    }

    public void setCurrentMission(Mission currentMission) {
        this.currentMission = currentMission;
    }
}
