package commands;

import exceptions.UnknownCommandException;
import messages.Message;
import state.CommandContext;
import tasks.Task;

import java.util.ArrayList;

/**
 * Handles commands sent by the user
 */
public class CommandHandler {
    /** The list of tasks stored during the current program session */
    private final ArrayList<Task> tasks = new ArrayList<>();
    /** The global state passed to all commands */
    private final CommandContext context = new CommandContext(tasks);

    /**
     * Handles a command
     * @param command the command to handle
     * @return the response to the command
     */
    public Message handle(String command) {
        // AI-assisted: Replaced echo handling with registered add and list command handling.
        String commandName = command.split(" ")[0];
        Command commandInstance = CommandRegistry.getCommand(commandName);
        if (commandInstance == null) {
            throw new UnknownCommandException(command);
        }
        // In case of no arguments, substring throws an exception
        if (command.length() == commandName.length()) {
            return commandInstance.handle(context, "");
        }
        String otherArg = command.substring(commandName.length() + 1);
        return commandInstance.handle(context, otherArg);
    }
}
