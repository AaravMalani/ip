package arthur.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import arthur.utils.Utils;

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
    // AI-assisted: Matched deadline tasks by their deadline date.
    public boolean isOn(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return TASK_TYPE + getMarkedIcon() + " " + getDescription() + " (by " + Utils.formatDateTime(by) + ")";
    }
}
