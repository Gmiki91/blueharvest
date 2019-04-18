package blueharvest.skills;

public class Skill {
    private long id;
    private String name;
    private int timeToMaster;
    private int timeToExecute;
    private SkillType type;
    private String description;

    public Skill(long id, String name,int timeToMaster, int timeToExecute, SkillType type, String description) {
        this.id=id;
        this.name = name;
        this.timeToMaster=timeToMaster;
        this.timeToExecute = timeToExecute;
        this.type=type;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeToMaster() {
        return timeToMaster;
    }

    public void setTimeToMaster(int timeToMaster) {
        this.timeToMaster = timeToMaster;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public int getTimeToExecute() {
        return timeToExecute;
    }

    public void setTimeToExecute(int timeToExecute) {
        this.timeToExecute = timeToExecute;
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
