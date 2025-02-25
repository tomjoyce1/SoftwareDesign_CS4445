package views;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogger {
    public static final Logger logger = Logger.getLogger(ConsoleLogger.class.getName());
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String BLUE = "\033[0;34m";
    private static final String YELLOW = "\033[0;33m";
    private static final String FORMAT = "%s%s%s";

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }

    public static void logError(String message) {
        if (logger.isLoggable(Level.SEVERE)) {
            logger.log(Level.SEVERE, String.format(FORMAT, RED, "ERROR: " + message, RESET));
        }
    }

    public static void logStandard(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(message));
        }
    }

    public static void logSuccess(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, GREEN, message, RESET));
        }
    }

    public static void logInfo(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, BLUE, message, RESET));
        }
    }

    public static void logWarning(String message) {
        if (logger.isLoggable(Level.WARNING)) {
            logger.log(Level.WARNING, String.format(FORMAT, YELLOW, "WARNING: " + message, RESET));
        }
    }

    public static void logTitle(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, BLUE, message, RESET));
        }
    }

    public static void logOption(String[] options) {
        logOption(options, false);
    }

    public static void logOption(String[] options, Boolean hasQuitOption) {
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder optionsMessage = new StringBuilder();
            for (int i = 0; i < options.length; i++) {
                optionsMessage.append(String.format("%d. %s%n", i + 1, options[i]));
            }
            if (Boolean.TRUE.equals(hasQuitOption)) {
                optionsMessage.append(String.format("%sQ. Quit%s%n", YELLOW, RESET));
            }
            optionsMessage.append(String.format("%sChoose action: %s", GREEN, RESET));
            logger.log(Level.INFO, optionsMessage.toString());
        }
    }

    private ConsoleLogger() {
        throw new IllegalStateException("This class should not be instantiated!!");
    }
}