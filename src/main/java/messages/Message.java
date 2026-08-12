package messages;

/**
 * An abstract response type. It is serialized using {@link Message#toString()}
 */
public abstract class Message {
    @Override
    public abstract String toString();

    /** Returns true if the program should return after this message  */
    public abstract boolean isFinal();
}
