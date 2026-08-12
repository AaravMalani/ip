package messages;

public class WelcomeMessage extends Message {
    // AI-assisted: Replaced the default Duke banner with ASCII art for "ARTHUR".
    private static final String MESSAGE = """
                _    ____ _____ _   _ _   _ ____
               / \\  |  _ \\_   _| | | | | | |  _ \\
              / _ \\ | |_) || | | |_| | | | | |_) |
             / ___ \\|  _ < | | |  _  | |_| |  _ <
            /_/   \\_\\_| \\_\\|_| |_| |_|\\___/|_| \\_\\
            
            I'm Dent, Arthur Dent. Would it save you a lot of time
            if I just gave up and went mad now?
            """;

    @Override
    public String toString() {
        return MESSAGE;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
