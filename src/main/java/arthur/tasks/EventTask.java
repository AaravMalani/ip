package arthur.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import arthur.utils.Utils;

/**
 * Represents a task occurring between two dates.
 */
public class EventTask extends Task {
    private static final TaskType TASK_TYPE = TaskType.EVENT;
    // AI-assisted: Added the event task type with from and to dates.
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new event task
     * @param description The description of the event task
     * @param from The start date of the event task
     * @param to The end date of the event task
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    // AI-assisted: Matched event tasks across their inclusive date range.
    public boolean isOn(LocalDate date) {
        LocalDate startDate = from.toLocalDate();
        LocalDate endDate = to.toLocalDate();
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    @Override
    public String toString() {
        return TASK_TYPE + getMarkedIcon() + " " + getDescription()
                + " (from " + Utils.formatDateTime(from) + ", to " + Utils.formatDateTime(to) + ")";
    }
}
