package blueharvest.character;

import java.time.LocalDate;

public class Character {
    private long id;
    private String name;
    private String password;
    private long imageId;
    private LocalDate lastVisit;
    private int food;
    private int money;
    private Status status;


    public Character(long id, String name, String password, long imageId, LocalDate lastVisit, int food, int money, Status status) {
        this.id = id;
        this.name = name;
        this.password=password;
        this.imageId=imageId;
        this.lastVisit = lastVisit;
        this.food=food;
        this.money=money;
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
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
