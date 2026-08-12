package commands;

import messages.MarkMessage;
import messages.Message;
import state.CommandContext;
import tasks.Task;

/**
 * Marks a numbered task as completed.
 */
public class MarkCommand extends Command {
    @Override
    // AI-assisted: Added handling that marks a one-based task number as completed.
    Message handle(CommandContext context, String arg) {
        Task task = context.tasks().get(Integer.parseInt(arg) - 1);
        task.mark();
        return new MarkMessage(task);
    }
}
