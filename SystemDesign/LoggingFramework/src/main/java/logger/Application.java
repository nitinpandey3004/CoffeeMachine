package logger;

public class Application {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.info("this is info");
        logger.error("this is error");
        logger.debg("this is debug");
    }
}
