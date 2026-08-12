package messages;

import tasks.Task;

/**
 * Confirms that a task has been marked as completed.
 */
public class MarkMessage extends Message {
    // AI-assisted: Added the confirmation message for marking a task.
    private final Task task;

    public MarkMessage(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("%s\n\nmarked:\n%s", getRandomQuote(), task);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
