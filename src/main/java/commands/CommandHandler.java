package commands;

import messages.EchoMessage;
import messages.Message;

/**
 * Handles commands sent by the user
 */
public class CommandHandler {
    /**
     * Handles a command
     * @param command the command to handle
     * @return the response to the command
     */
    public Message handle(String command) {
        String commandName = command.split(" ")[0];
        Command commandInstance = CommandRegistry.getCommand(commandName);
        if (commandInstance == null) {
            return new EchoMessage(command);
        }
        // In case of no arguments, substring throws an exception
        if (command.length() == commandName.length()) {
            return commandInstance.handle("");
        }
        String otherArg = command.substring(commandName.length() + 1);
        return commandInstance.handle(otherArg);
    }
}
