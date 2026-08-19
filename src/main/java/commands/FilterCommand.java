package commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import messages.ListMessage;
import messages.Message;
import state.CommandContext;
import tasks.Task;
import utils.Utils;

/**
 * Lists tasks that occur on a requested date.
 */
public class FilterCommand extends Command {
    /**
     * Filters stored deadline and event tasks by the date supplied with {@code /on}.
     *
     * @param context the shared command state
     * @param arg command arguments containing {@code /on yyyy-MM-dd}
     * @return a message containing matching tasks
     */
    @Override
    // AI-assisted: Added filtering of dated tasks for the /on argument.
    public Message handle(CommandContext context, String arg) {
        Map<String, String> args = parseArgs(arg, "/on");
        LocalDate date = Utils.parseDate(args.get("/on").trim());
        List<Task> filteredTasks = context.tasks().stream()
                .filter(task -> task.isOn(date))
                .toList();
        return new ListMessage(filteredTasks);
    }
}
