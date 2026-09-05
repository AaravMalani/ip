package arthur.state;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import arthur.tasks.Task;

/**
 * Stores the shared state that commands need during a program session.
 */
// AI-assisted: Added shared task storage for the add and list commands.
public record CommandContext(ArrayList<Task> tasks, HashMap<String, String> commandAliases) implements Serializable {
    public CommandContext() {
        this(new ArrayList<>(), new HashMap<>());
    }

    /**
     * During deserialization from an old version, some fields may be missing.
     * This method ensures that all fields are initialized.
     *
     * @return A new CommandContext with all fields initialized.
     */
    public CommandContext fixed() {
        ArrayList<Task> tasks = this.tasks;
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        HashMap<String, String> commandAliases = this.commandAliases;
        if (commandAliases == null) {
            commandAliases = new HashMap<>();
        }

        return new CommandContext(tasks, commandAliases);
    }
}
