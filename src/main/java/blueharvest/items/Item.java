package blueharvest.items;

public class Item {
    private long id;
    private String name;
    private String description;
    private int price;
    private int qty;
    private Type type;
    private long imageId;

    public Item(long id, String name, String description, int price,int qty, Type type, long imageId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty=qty;
        this.type = type;
        this.imageId = imageId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
