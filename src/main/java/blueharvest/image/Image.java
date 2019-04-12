package blueharvest.image;

public class Image {
    private long id;
    private byte[] imageArray;

    public Image(long id, byte[] imageArray) {
        this.id = id;
        this.imageArray = imageArray;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImageArray() {
        return imageArray;
    }

    public void setImageArray(byte[] imageArray) {
        this.imageArray = imageArray;
    }
}
