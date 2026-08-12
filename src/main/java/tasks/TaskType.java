package tasks;

public enum TaskType {
    EVENT("[E]"),
    TODO("[T]"),
    DEADLINE("[D]");


    private final String displayName;
    TaskType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
