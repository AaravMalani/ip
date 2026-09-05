package arthur.messages;

import arthur.tasks.Task;

/**
 * Confirms that a task has been marked as not completed.
 */
public class UnmarkMessage extends Message {
    // AI-assisted: Added the confirmation message for unmarking a task.
    private final Task task;

    public UnmarkMessage(Task task) {
        this.task = task;
        assert task != null : "Task should not be null";
    }

    @Override
    public String toString() {
        return String.format("%s\n\nunmarked:\n%s", getRandomQuote(), task);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
