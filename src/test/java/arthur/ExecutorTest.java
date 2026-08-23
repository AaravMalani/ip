package arthur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ExecutorTest {
    // AI-assisted: Exercise a multi-command session through the executor without text snapshots.
    @Test
    public void run_taskLifecycleCommands_returnsSuccessAndPrintsExpectedMessageTypes() {
        RecordingPrintStream output = new RecordingPrintStream();
        Executor executor = new Executor(input("""
                todo read book
                mark 1
                unmark 1
                remove 1
                list
                bye
                """), output, new InMemoryStorage());

        assertEquals(0, executor.run());
        assertEquals(List.of("WelcomeMessage", "AddMessage", "MarkMessage", "UnmarkMessage",
                        "RemoveMessage", "ListMessage", "ByeMessage"),
                output.getMessages().stream().map(message -> message.getClass().getSimpleName()).toList());
    }

    private static ByteArrayInputStream input(String commands) {
        return new ByteArrayInputStream(commands.getBytes(StandardCharsets.UTF_8));
    }
}
