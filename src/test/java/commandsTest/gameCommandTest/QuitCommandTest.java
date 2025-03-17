package commandsTest.gameCommandTest;

import commands.gamecommand.QuitCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.ConsoleLogger;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuitCommandTest {
    private TestHandler testHandler;

    @BeforeEach
    void setUp() {
        testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);
    }

    @Test
    void executePrintsExitingMessage() {
        QuitCommand quitCommand = new QuitCommand();
        quitCommand.execute();
        assertTrue(Objects.requireNonNull(testHandler.getLastMessage()).contains("Exiting the system..."));
    }

    private static class TestHandler extends StreamHandler {
        private LogRecord lastRecord;

        @Override
        public synchronized void publish(LogRecord logRecord) {
            lastRecord = logRecord;
        }

        public String getLastMessage() {
            return lastRecord != null ? lastRecord.getMessage() : null;
        }
    }
}