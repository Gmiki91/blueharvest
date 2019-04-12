package blueharvest.character;

public class Character {
    private long id;
    private String name;
    private long imageId;

    public Character(long id, String name, long imageId) {
        this.id = id;
        this.name = name;
        this.imageId=imageId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
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
