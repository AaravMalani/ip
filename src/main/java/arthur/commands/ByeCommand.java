package arthur.commands;

import arthur.messages.ByeMessage;
import arthur.messages.Message;
import arthur.state.CommandContext;

public class ByeCommand extends Command {
    @Override
    public Message handle(CommandContext context, String arg) {
        return new ByeMessage();
    }
}
