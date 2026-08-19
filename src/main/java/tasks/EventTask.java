package tasks;

import java.time.LocalDateTime;

import utils.Utils;

/**
 * Represents a task occurring between two dates.
 */
public class EventTask extends Task {
    // AI-assisted: Added the event task type with from and to dates.
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final TaskType TASK_TYPE = TaskType.EVENT;

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return TASK_TYPE + getMarkedIcon() + " " + getDescription()
                + " (from " + Utils.formatDateTime(from) + ", to " + Utils.formatDateTime(to) + ")";
    }
}
