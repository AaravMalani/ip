package arthur.exceptions;

public class InvalidDateTimeException extends ArthurRuntimeException {
    public InvalidDateTimeException(String argument) {
        super("Unable to parse datetime: " + argument);
    }
}
