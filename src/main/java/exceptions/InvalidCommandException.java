package exceptions;

public class InvalidCommandException extends ArthurRuntimeException {
    public InvalidCommandException(String command) {
        super("An error occurred while executing the following command: " + command);
    }
}
