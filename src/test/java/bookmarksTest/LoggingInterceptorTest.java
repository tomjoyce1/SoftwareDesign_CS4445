package bookmarksTest;

import bookmarks.LoggingInterceptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggingInterceptorTest {

    private Logger planeLogger;
    private TestLogHandler testHandler;

    @BeforeEach
    void setUp() {
        planeLogger = Logger.getLogger("PlaneLog");
        testHandler = new TestLogHandler();
        planeLogger.addHandler(testHandler);
    }

    @AfterEach
    void tearDown() {
        planeLogger.removeHandler(testHandler);
    }

    @Test
    void handleLogsProvidedMessage() {
        String message = "Test log message";
        new LoggingInterceptor().handleRequest(message);
        assertTrue(testHandler.getLogMessages().stream().anyMatch(log -> log.contains(message)));
    }

    @Test
    void handleLogsMultipleMessages() {
        String message1 = "First log message";
        String message2 = "Second log message";
        LoggingInterceptor interceptor = new LoggingInterceptor();
        interceptor.handleRequest(message1);
        interceptor.handleRequest(message2);
        List<String> logs = testHandler.getLogMessages();
        assertTrue(logs.stream().anyMatch(log -> log.contains(message1)));
        assertTrue(logs.stream().anyMatch(log -> log.contains(message2)));
    }

    private static class TestLogHandler extends Handler {
        private final List<String> logMessages = new ArrayList<>();

        @Override
        public void publish(LogRecord logRecord) {
            if (logRecord != null && logRecord.getMessage() != null) {
                logMessages.add(logRecord.getMessage());
            }
        }

        @Override
        public void flush() {
            /*
             * This method is required to be overridden by the abstract Handler class.
             * It is used to flush any buffered outputs from the Logger.
             */
        }

        @Override
        public void close() throws SecurityException {
            /*
             * This method is required to be overridden by the abstract Handler class.
             * It is used to close the Handler and free up any resources.
             */
        }

        public List<String> getLogMessages() {
            return logMessages;
        }
    }
}