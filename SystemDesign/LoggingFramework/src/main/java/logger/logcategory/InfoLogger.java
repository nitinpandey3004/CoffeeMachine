package logger.logcategory;

import logger.LogLevel;
import logger.logsink.LogSubject;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    protected void display(String msg, LogSubject logSubject) {
        String message = "INFO: " + msg;
        logSubject.notifyAllObservers(this.level, message);
    }
}
