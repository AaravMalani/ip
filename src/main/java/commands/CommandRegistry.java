package commands;

import exceptions.InvalidCommandException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static final Map<String, Class<? extends Command>> registry = new HashMap<>();

    static {
        registry.put("bye", ByeCommand.class);
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
