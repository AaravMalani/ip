package commands;

import messages.AddMessage;
import messages.Message;
import state.CommandContext;
import tasks.DeadlineTask;
import tasks.Task;
import tasks.TodoTask;

import java.util.Map;

/**
 * Adds a deadline task to the shared task list.
 */
public class DeadlineCommand extends Command {
    @Override
    // AI-assisted: Added handling that creates and stores a task in the shared command context.
    Message handle(CommandContext context, String arg) {
        Map<String, String> args = parseArgs(arg, "/by");
        String description = args.get("").trim();
        String by = args.get("/by").trim();
        Task task = new DeadlineTask(description, by);
        context.tasks().add(task);
        return new AddMessage(task);
    }
}
