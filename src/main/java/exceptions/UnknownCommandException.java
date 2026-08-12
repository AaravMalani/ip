package exceptions;

public class UnknownCommandException extends ArthurRuntimeException{
    public UnknownCommandException(String name) {
        super("The called command does not exist: " + name);
    }
}
