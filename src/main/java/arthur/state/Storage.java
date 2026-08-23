package arthur.state;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import arthur.exceptions.StorageException;

/**
 * Loads and saves the command context on disk.
 */
public class Storage {
    private static final Path FILE_PATH = Path.of("./data/arthur.bin");
    private static final Path DIRECTORY_PATH = Path.of("./data");


    /**
     * Loads the command context from the file.
     * If the file does not exist, a new command context is returned.
     * @return the command context
     */
    public CommandContext load() {
        if (!Files.exists(FILE_PATH)) {
            return new CommandContext();
        }
        try (FileInputStream fileIn = new FileInputStream(FILE_PATH.toFile());
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (CommandContext) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new CommandContext();
        }
    }

    /**
     * Saves the command context to the file.
     * @param context the command context to save
     * @throws StorageException if an error occurs while saving the command context
     */
    public void save(CommandContext context) {
        if (!Files.isDirectory(DIRECTORY_PATH)) {
            try {
                Files.createDirectories(DIRECTORY_PATH);
            } catch (IOException e) {
                throw new StorageException(e);
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH.toFile());
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(context);
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }
}
