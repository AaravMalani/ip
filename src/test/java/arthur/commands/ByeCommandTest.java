package arthur.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import arthur.Executor;
import arthur.InMemoryStorage;
import arthur.RecordingPrintStream;
import arthur.messages.ByeMessage;
import arthur.messages.Message;
import arthur.state.CommandContext;

public class ByeCommandTest {
    private static ByteArrayInputStream input(String commands) {
        return new ByteArrayInputStream(commands.getBytes(StandardCharsets.UTF_8));
    }

    // AI-assisted: Cover ByeCommand directly and through the full executor loop without text snapshots.
    @Test
    public void handle_noArguments_returnsFinalByeMessage() {
        Message message = new ByeCommand().handle(new CommandContext(), "");
        assertInstanceOf(ByeMessage.class, message);
        assertTrue(message.isFinal());
    }

    @Test
    public void handle_arguments_returnsFinalByeMessage() {
        Message message = new ByeCommand().handle(new CommandContext(), "some arguments");
        assertInstanceOf(ByeMessage.class, message);
        assertTrue(message.isFinal());
    }

    @Test
    public void run_byeCommand_returnsSuccessAndPrintsOneByeMessage() {
        RecordingPrintStream output = new RecordingPrintStream();
        Executor executor = new Executor(input("bye\n"), output, new InMemoryStorage());
        assertEquals(0, executor.run());
        assertEquals(1, output.getMessages().stream().filter(ByeMessage.class::isInstance).count());
    }
}
