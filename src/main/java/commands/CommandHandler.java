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
        if (command.equals("bye")) {
            return new ByeCommand().handle(command);
        }
        return new EchoMessage(command);
    }
}
