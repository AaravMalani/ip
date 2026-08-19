package commands;

import messages.AddMessage;
import messages.Message;
import state.CommandContext;
import tasks.EventTask;
import tasks.Task;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Adds a deadline task to the shared task list.
 */
public class EventCommand extends Command {
    @Override
    // AI-assisted: Added handling that creates and stores a task in the shared command context.
    public Message handle(CommandContext context, String arg) {
        Map<String, String> args = parseArgs(arg, "/from", "/to");
        String description = args.get("").trim();
        LocalDateTime from = Utils.parseDateTime(args.get("/from").trim());
        LocalDateTime to = Utils.parseDateTime(args.get("/to").trim());
        Task task = new EventTask(description, from, to);
        context.tasks().add(task);
        return new AddMessage(task);
    }
}
