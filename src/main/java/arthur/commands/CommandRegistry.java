package arthur.commands;

import arthur.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static final Map<String, Class<? extends Command>> registry = new HashMap<>();
    private static final Map<Class<? extends Command>, String> registryInv = new HashMap<>();

    static {
        // AI-assisted: Registered commands for adding and listing session tasks.
        registry.put("deadline", DeadlineCommand.class);
        registry.put("event", EventCommand.class);
        registry.put("todo", TodoCommand.class);
        registry.put("bye", ByeCommand.class);
        registry.put("list", ListCommand.class);
        registry.put("filter", FilterCommand.class);
        registry.put("mark", MarkCommand.class);
        registry.put("unmark", UnmarkCommand.class);
        registry.put("remove", RemoveCommand.class);
        registry.put("find", FindCommand.class);
        for (Map.Entry<String, Class<? extends Command>> entry : registry.entrySet()) {
            registryInv.put(entry.getValue(), entry.getKey());
        }
    }


    /**
     * Gets the command class from the registry by command name
     * @param command the name of the command to get
     * @return the command class
     */
    public static Command getCommand(String command) {
        if (!registry.containsKey(command)) {
            return null;
        }
        try {
            return registry.get(command).getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new InvalidCommandException(command);
        }
    }

    /**
     * Gets the command name from the registry by command class
     * @param c the command class to get
     * @return the command name
     */
    public static String getCommandName(Class<? extends Command> c) {
        if (!registryInv.containsKey(c)) {
            return null;
        }
        return registryInv.get(c);
    }
}
