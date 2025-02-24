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

public class LoggingInterceptorTest {

    private Logger planeLogger;
    private TestLogHandler testHandler;

    @BeforeEach
    public void setUp() {
        planeLogger = Logger.getLogger("PlaneLog");
        testHandler = new TestLogHandler();
        planeLogger.addHandler(testHandler);
    }

    @AfterEach
    public void tearDown() {
        planeLogger.removeHandler(testHandler);
    }

    @Test
    public void handleLogsProvidedMessage() {
        String message = "Test log message";
        new LoggingInterceptor().handleRequest(message);
        assertTrue(testHandler.getLogMessages().stream().anyMatch(log -> log.contains(message)));
    }

    @Test
    public void handleLogsMultipleMessages() {
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
        public void publish(LogRecord record) {
            if (record != null && record.getMessage() != null) {
                logMessages.add(record.getMessage());
            }
        }

        @Override
        public void flush() {}

        @Override
        public void close() throws SecurityException {}

        public List<String> getLogMessages() {
            return logMessages;
        }
    }
}