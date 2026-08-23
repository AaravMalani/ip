package arthur;

public class ArthurDent {
    public static void main(String[] args) {
        Executor executor = new Executor();
        // AI-assisted: Exit the JVM only after the executor returns its status.
        System.exit(executor.run());
    }
}
