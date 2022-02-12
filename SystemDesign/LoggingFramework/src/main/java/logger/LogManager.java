package logger;

import logger.logcategory.AbstractLogger;
import logger.logcategory.DebugLogger;
import logger.logcategory.ErrorLogger;
import logger.logcategory.InfoLogger;
import logger.logsink.ConsoleLogger;
import logger.logsink.FileLogger;
import logger.logsink.LogSubject;

public class LogManager {

    protected static AbstractLogger buildChainOfLogger() {
        AbstractLogger infoLogger = new InfoLogger(LogLevel.INFO);
        AbstractLogger errorLogger = new ErrorLogger(LogLevel.ERROR);
        AbstractLogger debugLogger = new DebugLogger(LogLevel.DEBUG);

        infoLogger.setNextLoggingLevel(errorLogger);
        errorLogger.setNextLoggingLevel(debugLogger);

        return infoLogger;
    }

    protected static LogSubject buildSubject() {
        LogSubject logSubject = new LogSubject();
        ConsoleLogger consoleLogger = new ConsoleLogger();
        FileLogger fileLogger = new FileLogger();

        logSubject.addObserver(LogLevel.INFO, consoleLogger);
        logSubject.addObserver(LogLevel.ERROR, consoleLogger);
        logSubject.addObserver(LogLevel.DEBUG, consoleLogger);

        logSubject.addObserver(LogLevel.INFO, fileLogger);


        return logSubject;
    }
}
