package tasks;

/**
 * Represents a task that must be completed by a date.
 */
public class DeadlineTask extends Task {
    // AI-assisted: Added the deadline task type with a by date.
    private final String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getMarkedIcon() + " " + getDescription() + " (by " + by + ")";
    }
}
