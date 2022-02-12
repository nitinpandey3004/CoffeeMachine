package logger.logsink;

import logger.LogLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LogSubject {

    Map<LogLevel, List<LogObserver>> logObservers = new HashMap<>();

    public void addObserver(LogLevel level, LogObserver logObserver) {
        List<LogObserver> logObserverList = logObservers.getOrDefault(level, new ArrayList<>());
        logObserverList.add(logObserver);
        logObservers.put(level, logObserverList);
    }

    public void notifyAllObservers(LogLevel level, String msg) {
        for (Map.Entry<LogLevel, List<LogObserver>> entry: logObservers.entrySet()) {
            if (entry.getKey() == level) {
                entry.getValue().forEach(logObserver -> logObserver.log(msg));
            }
        }
    }
}
