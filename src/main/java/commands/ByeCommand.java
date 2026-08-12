package commands;

import messages.ByeMessage;
import messages.Message;

public class ByeCommand extends Command {
    @Override
    Message handle(String arg) {
        return new ByeMessage();
    }
}
