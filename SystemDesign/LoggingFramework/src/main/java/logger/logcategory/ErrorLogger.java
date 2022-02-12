package logger.logcategory;

import logger.LogLevel;
import logger.logsink.LogSubject;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    protected void display(String msg, LogSubject logSubject) {
        String message = "ERROR: " + msg;
        logSubject.notifyAllObservers(this.level, message);
    }
}
