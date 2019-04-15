package blueharvest.character;

public enum Status {
    AVAILABLE(0), HUNTING(2), DEAD(0);

    long hours;

    Status(long hours) {
        this.hours = hours;
    }

    public long getHours() {
        return hours;
    }
}
