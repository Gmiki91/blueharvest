package blueharvest.character;

public class Character {
    private long id;
    private String name;
    private String password;
    private long imageId;


    public Character(long id, String name, String password, long imageId) {
        this.id = id;
        this.name = name;
        this.password=password;
        this.imageId=imageId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
