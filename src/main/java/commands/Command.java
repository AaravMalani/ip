package commands;

import messages.Message;

/**
 * A command is a message sent by the user to the program
 *  It has one main function: the {@link handle(String arg)} method which
 */
public abstract class Command {
    /**
     * Handles a command
     * @param arg the extra arguments sent by the user. For example, if the user sends "hello world john", arg will be "world john"
     * @return the response to the command
     */
    abstract Message handle(String arg);
}
