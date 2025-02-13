package Interceptors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger("PlaneLog");

    static {
        try {
            Files.createDirectories(Paths.get("./logOutput"));
            FileHandler fh = new FileHandler("./logOutput/logfileOutput.log", true);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            logger.severe("Error creating log file. Error:\n" + e.getMessage());
        }
    }

    @Override
    public void handleRequest(String input) {
        logger.info(input);
    }
}
