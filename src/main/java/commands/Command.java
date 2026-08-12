package commands;

import messages.Message;
import state.CommandContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A command is a message sent by the user to the program
 *  It has one main function: the {@link Command#handle(CommandContext context, String arg)} method which
 */
public abstract class Command {
    /**
     * Handles a command
     * @param arg the extra arguments sent by the user. For example, if the user sends "hello world john", arg will be "world john"
     * @return the response to the command
     */
    abstract Message handle(CommandContext context, String arg);

    protected Map<String, String> parseArgs(String arg, String... argNames) {
        String[] splits = arg.split(" ");
        List<String> argNamesList = Arrays.asList(argNames);

        Map<String, String> map = new HashMap<>();
        StringBuilder currentArg = new StringBuilder();
        String currentArgName = "";
        for (String split : splits) {
            if (argNamesList.contains(split)) {
                // Trim off the argument
                if (!currentArg.isEmpty()) {
                    currentArg.deleteCharAt(currentArg.length() - 1);
                }
                map.put(currentArgName, currentArg.toString());
                currentArgName = split;
                currentArg = new StringBuilder();
                continue;
            }
            currentArg.append(split).append(" ");
        }
        if (!currentArg.isEmpty()) {
            currentArg.deleteCharAt(currentArg.length() - 1);
        }
        map.put(currentArgName, currentArg.toString());
        return map;
    }
}
