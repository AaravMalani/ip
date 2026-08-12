package exceptions;

public class InvalidArgumentException extends ArthurRuntimeException {
    public InvalidArgumentException(String value) {
        super("An invalid argument was passed to the command: " + value);
    }
}
