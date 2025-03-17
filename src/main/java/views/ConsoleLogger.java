package views;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogger {
    public static final Logger logger = Logger.getLogger(ConsoleLogger.class.getName());
    private static final String FORMAT = "%s%s%s";

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }

    public static void logError(String message) {
        if (logger.isLoggable(Level.SEVERE)) {
            logger.log(Level.SEVERE, String.format(FORMAT, ConsoleColour.RED.getCode(), "ERROR: " + message, ConsoleColour.RESET.getCode()));
        }
    }

    public static void logStandard(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, ConsoleColour.WHITE.getCode(), message, ConsoleColour.RESET.getCode()));
        }
    }

    public static void logSuccess(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, ConsoleColour.GREEN.getCode(), message, ConsoleColour.RESET.getCode()));
        }
    }

    public static void logInfo(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, ConsoleColour.BLUE.getCode(), message, ConsoleColour.RESET.getCode()));
        }
    }

    public static void logWarning(String message) {
        if (logger.isLoggable(Level.WARNING)) {
            logger.log(Level.WARNING, String.format(FORMAT, ConsoleColour.YELLOW.getCode(), message, ConsoleColour.RESET.getCode()));
        }
    }

    public static void logTitle(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, String.format(FORMAT, ConsoleColour.BLUE.getCode(), message, ConsoleColour.RESET.getCode()));
        }
    }

    public static void logOption(String[] options) {
        logOption(options, false);
    }

    public static void logOption(String[] options, Boolean hasQuitOption) {
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder optionsMessage = new StringBuilder();
            for (int i = 0; i < options.length; i++) {
                optionsMessage.append(String.format(FORMAT, ConsoleColour.WHITE.getCode(), String.format("%d. %s%n", i + 1, options[i]), ConsoleColour.RESET.getCode()));
            }
            if (Boolean.TRUE.equals(hasQuitOption)) {
                optionsMessage.append(String.format("%sQ. Quit%s%n", ConsoleColour.YELLOW.getCode(), ConsoleColour.RESET.getCode()));
            }
            optionsMessage.append(String.format("%sChoose action: %s", ConsoleColour.GREEN.getCode(), ConsoleColour.RESET.getCode()));
            logger.log(Level.INFO, optionsMessage.toString());
        }
    }

    private ConsoleLogger() {
        throw new IllegalStateException("This class should not be instantiated!!");
    }
}