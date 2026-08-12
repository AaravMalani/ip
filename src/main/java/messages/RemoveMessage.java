package messages;

import tasks.Task;

/**
 * Confirms that a task has been removed.
 */
public class RemoveMessage extends Message {
    // AI-assisted: Added the confirmation message for marking a task.
    private final Task task;

    public RemoveMessage(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%s\n\nremoved:\n%s", getRandomQuote(), task);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
