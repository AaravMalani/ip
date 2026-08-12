package tasks;

/**
 * Represents a task entered by the user.
 */
public abstract class Task {
    /*
        AI-assisted:
        * Added a task model that stores the task description.
        * Added marked state and display formatting for tasks.
        * Made Task an abstract base class for todo, deadline, and event tasks.
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
     * Returns the icon representing whether this task is marked.
     *
     * @return {@code [X]} for a marked task, otherwise {@code [ ]}
     */
    protected String getMarkedIcon() {
        return isMarked ? "[X]" : "[ ]";
    }

    /**
     * Returns this task's description for subclass display formatting.
     *
     * @return the task description
     */
    protected String getDescription() {
        return description;
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

    @Override
    public abstract String toString();
}
