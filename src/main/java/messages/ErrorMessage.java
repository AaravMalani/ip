package messages;

import exceptions.ArthurRuntimeException;

/** Displayed when a command throws an error */
public class ErrorMessage extends Message{
    private final ArthurRuntimeException exception;

    public ErrorMessage(ArthurRuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", exception.getMessage(), getRandomQuote());
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
