package Models.Artifacts;

import Models.Global;

public class Artifact extends Global {

    private int abilityBuff;
    private int type;
    private String name;

    public Artifact(String name ,int abilityBuff, int type) {
        this.name = name;
        this.abilityBuff = abilityBuff;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getBuff() {
        return this.abilityBuff;
    }
}
