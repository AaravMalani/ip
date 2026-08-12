package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import messages.MarkMessage;
import messages.Message;
import messages.RemoveMessage;
import state.CommandContext;
import tasks.Task;

/**
 * Removes a task from the shared task list.
 */
public class RemoveCommand extends TaskCommand {
    private static final String COMMAND_NAME = "remove";
    private static final String ARGUMENT_NAME = "index";

    @Override
    protected String getArgumentName() {
        return ARGUMENT_NAME;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    // AI-assisted: Added handling that creates and stores a task in the shared command context.
    public Message handle(CommandContext context, int arg) {
        Task task = context.tasks().remove(arg);
        return new RemoveMessage(task);
    }
}
