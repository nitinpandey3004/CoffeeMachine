package logger;

public enum LogLevel {

    INFO (1),
    ERROR (2),
    DEBUG (3);

    private final int level;
    LogLevel(int level) {
        this.level = level;
    }
}
