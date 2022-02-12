package logger.logcategory;

import logger.LogLevel;
import logger.logsink.LogSubject;

public abstract class AbstractLogger {
    LogLevel level;
    AbstractLogger nextLoggingLevel;

    public void setNextLoggingLevel(AbstractLogger nextLoggingLevel) {
        this.nextLoggingLevel = nextLoggingLevel;
    }

    public void logMessage(LogLevel level, String msg, LogSubject logSubject) {
        if (this.level == level) {
            this.display(msg, logSubject);
        }
        if (nextLoggingLevel != null) {
            nextLoggingLevel.logMessage(level, msg, logSubject);
        }
    }

    protected abstract void display(String msg, LogSubject logSubject);
}
