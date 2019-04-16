package blueharvest.skills;

public class Skill {
    private long id;
    private String name;
    private int timeToMaster;

    public Skill(long id, String name, int timeToMaster) {
        this.id=id;
        this.name = name;
        this.timeToMaster=timeToMaster;
    }

    public int getTimeToMaster() {
        return timeToMaster;
    }

    public void setTimeToMaster(int timeToMaster) {
        this.timeToMaster = timeToMaster;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
