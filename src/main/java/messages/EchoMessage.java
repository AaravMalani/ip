package messages;

public class EchoMessage extends Message {
    private final String message;

    public EchoMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

}
