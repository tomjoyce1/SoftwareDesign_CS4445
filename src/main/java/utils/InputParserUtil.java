package utils;

import views.ConsoleLogger;

public class InputParserUtil {

    public static Integer parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ConsoleLogger.logError("Invalid numeric input: " + input);
            return null;
        }
    }
}