package blueharvest.character;

public class CharacterInfo {
    private Character character;
    private int receivedFood;
    private int receivedMoney;
    private long remainingTime;
    private String nameOfSkillLearned;

    public CharacterInfo(Character character, int receivedFood, int receivedMoney, long remainingTime, String nameOfSkillLearned ) {
        this.character = character;
        this.receivedFood = receivedFood;
        this.receivedMoney = receivedMoney;
        this.remainingTime=remainingTime;
        this.nameOfSkillLearned=nameOfSkillLearned;
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
