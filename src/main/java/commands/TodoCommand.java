package commands;

import messages.AddMessage;
import messages.Message;
import state.CommandContext;
import tasks.Task;
import tasks.TodoTask;

/**
 * Adds a to-do task to the shared task list.
 */
public class TodoCommand extends Command {
    @Override
    // AI-assisted: Added handling that creates and stores a task in the shared command context.
    public Message handle(CommandContext context, String arg) {
        Task task = new TodoTask(arg);
        context.tasks().add(task);
        return new AddMessage(task);
    }
}
