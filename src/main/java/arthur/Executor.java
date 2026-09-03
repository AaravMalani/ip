package arthur;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import arthur.commands.CommandHandler;
import arthur.exceptions.ArthurRuntimeException;
import arthur.messages.ErrorMessage;
import arthur.messages.Message;
import arthur.messages.WelcomeMessage;
import arthur.state.CommandContext;
import arthur.state.Storage;

/**
 * Coordinates command execution, persistence, and console output.
 */
public class Executor {
    /**
     * A horizontal line to separate messages
     */
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static final String PROMPT = "\t> ";
    /**
     * The message queue
     */
    private final List<Message> messages = new ArrayList<>();
    /**
     * The global state
     */
    private final CommandContext context;
    /**
     * The command handler which converts user input into messages
     */
    private final CommandHandler commandHandler;
    /**
     * The scanner which reads user input
     */
    private final Scanner scanner;
    /**
     * The storage engine
     */
    private final Storage storage;

    /**
     * Output stream
     */
    private final PrintStream outputStream;

    public Executor() {
        this(System.in, System.out);
    }

    public Executor(InputStream inputStream, PrintStream outputStream) {
        this(inputStream, outputStream, new Storage());
    }

    /**
     * Creates a new executor
     * @param inputStream The input stream (e.g. System.in)
     * @param outputStream The output stream (e.g. System.out)
     * @param storage The storage engine (e.g. a Storage object)
     */
    public Executor(InputStream inputStream, PrintStream outputStream, Storage storage) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
        this.storage = storage;
        messages.add(new WelcomeMessage());
        context = storage.load();
        commandHandler = new CommandHandler(context);
    }

    /**
     * Flushes messages from the message queue
     */
    private boolean flushMessages() {
        boolean hasPrintedMessage = false;
        for (Message message : messages) {
            if (!hasPrintedMessage) {
                hasPrintedMessage = true;
                outputStream.println(HORIZONTAL_LINE);
            }
            outputStream.println(message);
            outputStream.println(HORIZONTAL_LINE);
            if (message.isFinal()) {
                return true;
            }
        }
        messages.clear();
        return false;
    }

    /**
     * Accepts user input
     */
    private String acceptInput() {
        outputStream.print(PROMPT);
        return scanner.nextLine();
    }

    /**
     * Runs the command loop until a command produces a final message.
     *
     * @return {@code 0} when the application exits normally
     */
    public int run() {
        while (true) {
            if (flushMessages()) {
                return 0;
            }
            String input = acceptInput();
            try {
                messages.add(commandHandler.handle(input));
                storage.save(context);
            } catch (ArthurRuntimeException e) {
                messages.add(new ErrorMessage(e));
            }
        }
    }
}
