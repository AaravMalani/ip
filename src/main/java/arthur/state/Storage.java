package arthur.state;

import arthur.exceptions.StorageException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private static final Path FILE_PATH = Path.of("./data/arthur.bin");
    private static final Path DIRECTORY_PATH = Path.of("./data");


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
