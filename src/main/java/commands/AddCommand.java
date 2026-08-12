package commands;

import messages.AddMessage;
import messages.Message;
import state.CommandContext;
import tasks.Task;

/**
 * Adds a task to the shared task list.
 */
public class AddCommand extends Command {
    @Override
    // AI-assisted: Added handling that creates and stores a task in the shared command context.
    Message handle(CommandContext context, String arg) {
        Task task = new Task(arg);
        context.tasks().add(task);
        return new AddMessage(task);
    }
}
