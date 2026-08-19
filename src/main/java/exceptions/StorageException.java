package exceptions;

public class StorageException extends ArthurRuntimeException {
    public StorageException(Exception e) {
        super("Error while saving state: " + e.getMessage());
    }
}
