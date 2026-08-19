package tasks;

import java.time.LocalDateTime;

import utils.Utils;

/**
 * Represents a task that must be completed by a date.
 */
public class DeadlineTask extends Task {
    // AI-assisted: Added the deadline task type with a by date.
    private final LocalDateTime by;
    private static final TaskType TASK_TYPE = TaskType.DEADLINE;

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return TASK_TYPE + getMarkedIcon() + " " + getDescription() + " (by " + Utils.formatDateTime(by) + ")";
    }
}
