package messages;

import tasks.Task;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Displays the tasks currently stored in the command context.
 */
public class ListMessage extends Message {
    // AI-assisted: Added a message that formats stored tasks as numbered entries.
    private final List<Task> tasks;

    private static final String NO_TASKS = "No tasks to display.";

    /**
     * Creates a message that displays the supplied tasks.
     *
     * @param tasks the tasks to display
     */
    public ListMessage(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        String listStr;
        if (tasks.isEmpty()) {
            listStr = NO_TASKS;
        } else {
            listStr = IntStream.range(0, tasks.size())
                    .mapToObj(index -> (index + 1) + ". " + tasks.get(index))
                    .reduce("", (list, task) -> list.isEmpty() ? task : list + System.lineSeparator() + task);
        }
        return String.format("%s\n\n%s", getRandomQuote(), listStr);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
