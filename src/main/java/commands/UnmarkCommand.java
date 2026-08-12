package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import messages.Message;
import messages.UnmarkMessage;
import state.CommandContext;
import tasks.Task;

/**
 * Marks a numbered task as not completed.
 */
public class UnmarkCommand extends Command {
    private static final String COMMAND_NAME = "unmark";
    private static final String ARGUMENT_NAME = "index";

    @Override
    // AI-assisted: Added handling that marks a one-based task number as not completed.
    public Message handle(CommandContext context, String arg) {
        Task task;
        if (arg.isEmpty()) {
            throw new MissingArgumentException(COMMAND_NAME, ARGUMENT_NAME);
        }
        try {
            task = context.tasks().get(Integer.parseInt(arg) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(arg);
        }
        task.unmark();
        return new UnmarkMessage(task);
    }
}
