package logger.logcategory;

import logger.LogLevel;
import logger.logsink.LogSubject;

public class DebugLogger extends AbstractLogger {

    public DebugLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    protected void display(String msg, LogSubject logSubject) {
        String message = "DEBUG: " + msg;
        logSubject.notifyAllObservers(this.level, message);
    }
}

