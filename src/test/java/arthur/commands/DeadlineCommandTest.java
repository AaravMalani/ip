package arthur.commands;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import arthur.exceptions.InvalidDateTimeException;
import arthur.exceptions.MissingArgumentException;
import arthur.messages.AddMessage;
import arthur.state.CommandContext;
import arthur.tasks.DeadlineTask;

public class DeadlineCommandTest {
    // AI-assisted: Cover valid and incomplete deadline arguments.
    @Test
    public void handle_validDeadline_addsDeadlineTask() {
        CommandContext context = new CommandContext();
        assertInstanceOf(AddMessage.class,
                new DeadlineCommand().handle(context, "submit report /by 2026-09-15 1430"));
        assertInstanceOf(DeadlineTask.class, context.tasks().getFirst());
    }

    @Test
    public void handle_missingByArgument_throwsMissingArgumentException() {
        assertThrows(MissingArgumentException.class, () ->
                new DeadlineCommand().handle(new CommandContext(), "submit report"));
    }

    @Test
    public void handle_invalidByDate_throwsInvalidDateTimeException() {
        assertThrows(InvalidDateTimeException.class, () ->
                new DeadlineCommand().handle(new CommandContext(), "submit report /by tomorrow"));
    }
}
