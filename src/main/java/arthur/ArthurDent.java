package arthur;

/**
 * Starts the Arthur application.
 */
public class ArthurDent {
    /**
     * Starts the application and exits with its completion status.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Executor executor = new Executor();
        // AI-assisted: Exit the JVM only after the executor returns its status.
        System.exit(executor.run());
    }
}
