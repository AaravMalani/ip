import commands.CommandHandler;
import exceptions.ArthurRuntimeException;
import messages.ErrorMessage;
import messages.Message;
import messages.WelcomeMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Executor {
    /** The message queue */
    private final List<Message> messages = new ArrayList<>();
    /** A horizontal line to separate messages */
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    /** The command handler which converts user input into messages */
    private final CommandHandler commandHandler = new CommandHandler();

    /** The scanner which reads user input */
    private final Scanner scanner = new Scanner(System.in);

    private static final String PROMPT = "\t> ";

    Executor() {
        messages.add(new WelcomeMessage());
    }

    /**
     * Flushes messages from the message queue
     */
    private void flushMessages() {
        boolean hasPrintedMessage = false;
        for (Message message : messages) {
            if (!hasPrintedMessage) {
                hasPrintedMessage = true;
                System.out.println(HORIZONTAL_LINE);
            }
            System.out.println(message);
            System.out.println(HORIZONTAL_LINE);
            if (message.isFinal()) {
                System.exit(0);
            }
        }
        messages.clear();
    }

    /** Accepts user input */
    private String acceptInput() {
        System.out.print(PROMPT);
        return scanner.nextLine();
    }

    public void run() {
        while (true) {
            flushMessages();
            String input = acceptInput();
            try {
                messages.add(commandHandler.handle(input));
            } catch (ArthurRuntimeException e) {
                messages.add(new ErrorMessage(e));
            }
        }
    }
}
