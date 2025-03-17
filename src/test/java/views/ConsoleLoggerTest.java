package views;

import org.junit.jupiter.api.Test;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleLoggerTest {

    @Test
    void logError_logsErrorMessage() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        ConsoleLogger.logError("This is an error");
        assertTrue(handler.getLastMessage().contains("ERROR: This is an error"));
    }

    @Test
    void logStandard_logsStandardMessage() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        ConsoleLogger.logStandard("This is a standard message");
        assertTrue(handler.getLastMessage().contains("This is a standard message"));
    }

    @Test
    void logSuccess_logsSuccessMessage() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        ConsoleLogger.logSuccess("This is a success message");
        assertTrue(handler.getLastMessage().contains("This is a success message"));
    }

    @Test
    void logInfo_logsInfoMessage() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        ConsoleLogger.logInfo("This is an info message");
        assertTrue(handler.getLastMessage().contains("This is an info message"));
    }

    @Test
    void logWarning_logsWarningMessage() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        ConsoleLogger.logWarning("This is a warning");
        assertTrue(handler.getLastMessage().contains("This is a warning"));
    }

    @Test
    void logTitle_logsTitleMessage() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        ConsoleLogger.logTitle("This is a title");
        assertTrue(handler.getLastMessage().contains("This is a title"));
    }

    @Test
    void logOption_logsOptionsWithQuit() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        String[] options = {"Option 1", "Option 2"};
        ConsoleLogger.logOption(options, true);
        assertTrue(handler.getLastMessage().contains("1. Option 1"));
        assertTrue(handler.getLastMessage().contains("2. Option 2"));
        assertTrue(handler.getLastMessage().contains("Q. Quit"));
    }

    @Test
    void logOption_logsOptionsWithoutQuit() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        String[] options = {"Option 1", "Option 2"};
        ConsoleLogger.logOption(options, false);
        assertTrue(handler.getLastMessage().contains("1. Option 1"));
        assertTrue(handler.getLastMessage().contains("2. Option 2"));
        assertFalse(handler.getLastMessage().contains("Q. Quit"));
    }

    @Test
    void logOption_logsEmptyOptionsWithQuit() {
        TestHandler handler = new TestHandler();
        ConsoleLogger.logger.addHandler(handler);
        String[] options = {};
        ConsoleLogger.logOption(options, true);
        assertTrue(handler.getLastMessage().contains("Q. Quit"));
    }

    private static class TestHandler extends StreamHandler {
        private String lastMessage;

        @Override
        public void publish(LogRecord logRecord) {
            lastMessage = logRecord.getMessage();
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}