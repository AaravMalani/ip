package tasks;

/**
 * Represents a task entered by the user.
 */
public class Task {
    /*
        AI-assisted:
        * Added a task model that stores the task description.
        * Added marked state and display formatting for tasks.
     */
    private final String description;
    private boolean isMarked;

    /**
     * Creates a task with the supplied description.
     *
     * @param description the text describing the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns this task in the format expected by the list command.
     *
     * @return the task status followed by its description
     */
    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[ ] ") + description;
    }

    /**
     * Mark the task as done
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmark the task
     */
    public void unmark() {
        this.isMarked  = false;
    }
}
