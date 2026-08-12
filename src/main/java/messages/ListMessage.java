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
        return IntStream.range(0, tasks.size())
                .mapToObj(index -> (index + 1) + ". " + tasks.get(index).getDescription())
                .reduce("", (list, task) -> list.isEmpty() ? task : list + System.lineSeparator() + task);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
