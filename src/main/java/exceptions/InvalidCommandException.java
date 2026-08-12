package exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String command) {
        super("An error occurred while executing the following command: " + command);
    }
}
