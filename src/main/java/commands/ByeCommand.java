package commands;

import messages.ByeMessage;
import messages.Message;
import state.CommandContext;

public class ByeCommand extends Command {
    @Override
    Message handle(CommandContext context, String arg) {
        return new ByeMessage();
    }
}
