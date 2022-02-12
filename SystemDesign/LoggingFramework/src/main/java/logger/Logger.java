package logger;

import logger.logcategory.AbstractLogger;
import logger.logsink.LogSubject;

import java.io.Serializable;

class Logger implements Cloneable, Serializable {

    private volatile static Logger logger;
    private volatile static AbstractLogger chainOfLogger;
    private volatile static LogSubject logSubject;

    private Logger() {
        if (logger != null) {
            throw new IllegalStateException("Object already created");
        }
    }

    public static Logger getInstance() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                    chainOfLogger = LogManager.buildChainOfLogger();
                    logSubject = LogManager.buildSubject();
                }
            }
        }
        return logger;
    }

    protected Object Clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    protected Object readResolve() {
        return logger;
    }

    void createLog(LogLevel level, String msg) {
        chainOfLogger.logMessage(level, msg, logSubject);
    }

    public void info(String msg) {
        createLog(LogLevel.INFO, msg);
    }

    public void error(String msg) {
        createLog(LogLevel.ERROR, msg);
    }

    public void debg(String msg) {
        createLog(LogLevel.DEBUG, msg);
    }
}