package tasks;

/**
 * Represents a task entered by the user.
 */
public class Task {
    // AI-assisted: Added a task model that stores the task description.
    private final String description;

    /**
     * Creates a task with the supplied description.
     *
     * @param description the text describing the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the task description.
     *
     * @return the description entered by the user
     */
    public String getDescription() {
        return description;
    }
}
