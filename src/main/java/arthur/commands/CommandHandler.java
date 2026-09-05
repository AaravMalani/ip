package arthur.commands;

import arthur.exceptions.ArthurRuntimeException;
import arthur.exceptions.UnknownCommandException;
import arthur.messages.ErrorMessage;
import arthur.messages.Message;
import arthur.state.CommandContext;
import arthur.state.Storage;

/**
 * Handles commands sent by the user
 */
public class CommandHandler {
    // AI-assisted: Named command parsing and application shutdown literals.
    private static final String COMMAND_SEPARATOR = " ";
    private static final int COMMAND_NAME_INDEX = 0;
    private static final long EXIT_DELAY_MILLIS = 1000;
    /** The command context used to store and retrieve data */
    private final CommandContext context;
    /** The storage instance used to save and load the command context */
    private final Storage storage;

    /**
     * Constructs a CommandHandler with the given command context and storage instance.
     *
     * @param context the command context used to store and retrieve data
     * @param storage the storage instance used to save and load the command context
     */
    public CommandHandler(CommandContext context, Storage storage) {
        this.context = context;
        this.storage = storage;
    }

    /**
     * Handles a command
     *
     * @param command the command to handle
     * @return the response to the command
     */
    public Message handle(String command) {
        // AI-assisted: Replaced echo handling with registered add and list command handling.
        String commandName = command.split(COMMAND_SEPARATOR)[COMMAND_NAME_INDEX];
        Command commandInstance = CommandRegistry.getCommand(commandName);
        // In case of no arguments, substring throws an exception
        Message message;
        try {
            if (commandInstance == null) {
                throw new UnknownCommandException(command);
            }
            if (command.length() == commandName.length()) {
                message = commandInstance.handle(context, Command.UNNAMED_ARGUMENT);
            } else {
                String otherArg = command.substring(commandName.length() + COMMAND_SEPARATOR.length());
                message = commandInstance.handle(context, otherArg);
            }
        } catch (ArthurRuntimeException e) {
            message = new ErrorMessage(e);
        }
        storage.save(context);

        if (message.isFinal()) {
            new Thread(() -> {
                try {
                    Thread.sleep(EXIT_DELAY_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            });
        }
        return message;
    }
}
