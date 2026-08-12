package commands;

import exceptions.InvalidCommandException;

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
        registry.put("mark", MarkCommand.class);
        registry.put("unmark", UnmarkCommand.class);

        for (Map.Entry<String, Class<? extends Command>> entry : registry.entrySet()) {
            registryInv.put(entry.getValue(), entry.getKey());
        }
    }


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

    public static String getCommandName(Class<? extends Command> c) {
        if (!registryInv.containsKey(c)) {
            return null;
        }
        return registryInv.get(c);
    }
}
