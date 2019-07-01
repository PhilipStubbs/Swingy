package Models.Missions.MissionTypes;

public class Mission {
    private int progess;
    private int goal;
    private  String description;
    private Boolean completed;
    private int reward;

    public Mission(int progess, int goal, String description, int reward){
        this.progess = progess;
        this.goal = goal;
        this.description = description;
        this.reward = reward;
        this.completed = false;
    }

    public int getProgess() {
        return progess;
    }

    public void addProgess() {
        progess++;
    }


    public void setProgess(int progess) {
        this.progess = progess;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public int getReward() {
        return reward;
    }

    public void addReward(int reward) {
        this.reward += reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
