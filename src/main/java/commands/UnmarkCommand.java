package commands;

import messages.Message;
import messages.UnmarkMessage;
import state.CommandContext;
import tasks.Task;

/**
 * Marks a numbered task as not completed.
 */
public class UnmarkCommand extends Command {
    @Override
    // AI-assisted: Added handling that marks a one-based task number as not completed.
    Message handle(CommandContext context, String arg) {
        Task task = context.tasks().get(Integer.parseInt(arg) - 1);
        task.unmark();
        return new UnmarkMessage(task);
    }
}
