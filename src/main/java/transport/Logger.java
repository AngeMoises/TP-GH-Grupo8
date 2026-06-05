package transport;

public class Logger {

    private static Logger instance;

    // Constructor protegido (no privado) para permitir que FakeLogger lo extienda en tests
    protected Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logWarning(String msg) {
        System.out.println("[WARNING] " + msg);
    }

    public void logDebug(String msg) {
        System.out.println("[DEBUG]   " + msg);
    }

    public void logInfo(String msg) {
        System.out.println("[INFO]    " + msg);
    }

    public void logError(String msg) {
        System.err.println("[ERROR]   " + msg);
    }
}
