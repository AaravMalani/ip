package messages;

/**
 * Confirms that a task has been added.
 */
public class AddMessage extends Message {
    // AI-assisted: Added the response message for a successfully added task.
    private final String task;

    /**
     * Creates a confirmation message for a task.
     *
     * @param task the task that was added
     */
    public AddMessage(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "added: " + task;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
