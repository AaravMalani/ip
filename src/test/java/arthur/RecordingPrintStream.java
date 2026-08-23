package arthur;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import arthur.messages.Message;

/** Records application messages without asserting their rendered text. */
public class RecordingPrintStream extends PrintStream {
    private final List<Message> messages = new ArrayList<>();

    public RecordingPrintStream() {
        super(new ByteArrayOutputStream());
    }

    // AI-assisted: Capture message objects for text-independent executor assertions.
    @Override
    public void println(Object value) {
        if (value instanceof Message message) {
            messages.add(message);
        }
        super.println(value);
    }

    public List<Message> getMessages() {
        return List.copyOf(messages);
    }
}
