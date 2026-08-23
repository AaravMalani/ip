package arthur.commands;

import arthur.exceptions.UnknownCommandException;
import arthur.messages.Message;
import arthur.state.CommandContext;

/**
 * Handles commands sent by the user
 */
public class CommandHandler {
    private final CommandContext context;

    public CommandHandler(CommandContext context) {
        this.context = context;
    }

    /**
     * Handles a command
     *
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
