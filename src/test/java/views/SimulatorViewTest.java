package views;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

class SimulatorViewTest {

    private static class TestHandler extends StreamHandler {
        private final StringBuilder logMessages = new StringBuilder();

        @Override
        public synchronized void publish(LogRecord logRecord) {
            logMessages.append(logRecord.getMessage()).append("\n");
        }

        public String getLogMessages() {
            return logMessages.toString();
        }
    }

    @Test
    void displayMenuPrintsAllMenuOptions() {
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        SimulatorView view = new SimulatorView();
        view.displayMenu();

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("=== Flight Operations ==="));
        assertTrue(logOutput.contains("1. Create new flight"));
        assertTrue(logOutput.contains("2. Control flight"));
        assertTrue(logOutput.contains("3. Clear Scheduled Flights For Take Off"));

        assertTrue(logOutput.contains("=== Weather Operations ==="));
        assertTrue(logOutput.contains("4. Update weather"));

        assertTrue(logOutput.contains("=== Flight Information ==="));
        assertTrue(logOutput.contains("5. List all flights"));
        assertTrue(logOutput.contains("6. Check Flight Status"));
        assertTrue(logOutput.contains("7. View Flight Info"));

        assertTrue(logOutput.contains("=== Map Operations ==="));
        assertTrue(logOutput.contains("8. View Air Traffic Map"));
        assertTrue(logOutput.contains("9. View Cell Contents"));

        assertTrue(logOutput.contains("=== Exit ==="));
        assertTrue(logOutput.contains("Q. Quit"));
        assertTrue(logOutput.contains("Choose action: "));
    }

    @Test
    void getUserInputReturnsUpperCaseText() {
        String simulatedInput = "q";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        SimulatorView view = new SimulatorView();
        String input = view.getUserInput();
        System.setIn(originalIn);
        assertEquals("Q", input);
    }
}