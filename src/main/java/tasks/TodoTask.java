package tasks;

/**
 * Represents a task without a date or time requirement.
 */
public class TodoTask extends Task {
    private static final TaskType TASK_TYPE = TaskType.TODO;

    // AI-assisted: Added the todo task type and its display format.
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TASK_TYPE + getMarkedIcon() + " " + getDescription();
    }
}
