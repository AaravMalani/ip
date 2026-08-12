package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import messages.MarkMessage;
import messages.Message;
import state.CommandContext;
import tasks.Task;

/**
 * Marks a numbered task as completed.
 */
public class MarkCommand extends Command {
    private static final String COMMAND_NAME = "mark";
    private static final String ARGUMENT_NAME = "index";

    @Override
    // AI-assisted: Added handling that marks a one-based task number as completed.
    public Message handle(CommandContext context, String arg) {
        Task task;
        if (arg.isEmpty()) {
            throw new MissingArgumentException(COMMAND_NAME, ARGUMENT_NAME);
        }
        try {
            task = context.tasks().get(Integer.parseInt(arg) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException  e) {
            throw new InvalidArgumentException(arg);
        }
        task.mark();
        return new MarkMessage(task);
    }
}
