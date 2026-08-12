import messages.ByeMessage;
import messages.Message;
import messages.WelcomeMessage;

import java.util.ArrayList;
import java.util.List;

public class Executor {
    /** The message queue */
    private final List<Message> messages = new ArrayList<>();
    /** Stores whether the first message has been printed */
    private boolean hasPrintedMessage = false;

    private static final String HORIZONTAL_LINE = "_".repeat(60);

    Executor() {
        messages.add(new WelcomeMessage());
        messages.add(new ByeMessage());
    }

    /**
     * Flushes messages from the message queue
     */
    private void flushMessages() {
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

    public void run() {
        flushMessages();
    }
}
