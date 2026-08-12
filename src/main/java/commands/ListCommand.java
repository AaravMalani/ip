package commands;

import messages.ListMessage;
import messages.Message;
import state.CommandContext;

/**
 * Lists all tasks stored during the current program session.
 */
public class ListCommand extends Command {
    @Override
    // AI-assisted: Added handling that returns the shared tasks as a bullet-point list.
    public Message handle(CommandContext context, String arg) {
        return new ListMessage(context.tasks());
    }
}
