package state;

import tasks.Task;

import java.util.ArrayList;

/**
 * Stores the shared state that commands need during a program session.
 */
// AI-assisted: Added shared task storage for the add and list commands.
public record CommandContext(ArrayList<Task> tasks) {
}
