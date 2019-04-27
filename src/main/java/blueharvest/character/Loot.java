package blueharvest.character;

import blueharvest.items.Item;

public class Loot {
    private Character character;
    private int receivedFood;
    private int receivedMoney;
    private long remainingTime;
    private String nameOfSkillLearned;
    private Item receivedItem;

    public Loot(Character character, int receivedFood, int receivedMoney, long remainingTime, String nameOfSkillLearned, Item receivedItem) {
        this.character = character;
        this.receivedFood = receivedFood;
        this.receivedMoney = receivedMoney;
        this.remainingTime=remainingTime;
        this.nameOfSkillLearned=nameOfSkillLearned;
        this.receivedItem = receivedItem;
    }
    public Loot(){}

    public Item getReceivedItem() {
        return receivedItem;
    }

    public void setReceivedItem(Item receivedItem) {
        this.receivedItem = receivedItem;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getNameOfSkillLearned() {
        return nameOfSkillLearned;
    }

    public void setNameOfSkillLearned(String nameOfSkillLearned) {
        this.nameOfSkillLearned = nameOfSkillLearned;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getReceivedFood() {
        return receivedFood;
    }

    public void setReceivedFood(int receivedFood) {
        this.receivedFood = receivedFood;
    }

    public int getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(int receivedMoney) {
        this.receivedMoney = receivedMoney;
    }
}
