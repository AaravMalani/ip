package commands;

import exceptions.InvalidCommandException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static final Map<String, Class<? extends Command>> registry = new HashMap<>();

    static {
        // AI-assisted: Registered commands for adding and listing session tasks.
        registry.put("bye", ByeCommand.class);
        registry.put("add", AddCommand.class);
        registry.put("list", ListCommand.class);
        registry.put("mark", MarkCommand.class);
        registry.put("unmark", UnmarkCommand.class);
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
}
