package tasks;

/**
 * Represents a task occurring between two dates.
 */
public class EventTask extends Task {
    // AI-assisted: Added the event task type with from and to dates.
    private final String from;
    private final String to;
    private static final TaskType TASK_TYPE = TaskType.EVENT;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return TASK_TYPE + getMarkedIcon() + " " + getDescription()
                + " (from " + from + ", to " + to + ")";
    }
}
