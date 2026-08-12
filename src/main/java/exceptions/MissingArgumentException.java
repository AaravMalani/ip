package exceptions;

public class MissingArgumentException extends ArthurRuntimeException {
    public MissingArgumentException(String commandName, String missingArgument) {
        super("Missing argument from " + commandName + ": " + missingArgument);
    }
}
