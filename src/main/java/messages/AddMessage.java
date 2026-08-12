package messages;

import tasks.Task;

/**
 * Confirms that a task has been added.
 */
public class AddMessage extends Message {
    // AI-assisted: Added the response message for a successfully added task.
    private final Task task;

    /**
     * Creates a confirmation message for a task.
     *
     * @param task the task that was added
     */
    public AddMessage(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%s\n\nadded:\n%s", getRandomQuote(), task);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
