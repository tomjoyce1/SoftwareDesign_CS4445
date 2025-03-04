package views;

import models.decorators.radardecorator.RadarDisplay;
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

        RadarDisplay dummyRadar = () -> {
        };
        SimulatorView view = new SimulatorView(dummyRadar);
        view.displayMenu();

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("=== Main Menu for ISE International Airport ==="));
        assertTrue(logOutput.contains("1. Create new flight"));
        assertTrue(logOutput.contains("2. Control flight"));
        assertTrue(logOutput.contains("3. Update weather"));
        assertTrue(logOutput.contains("4. List all flights"));
        assertTrue(logOutput.contains("5. Check Flight Status"));
        assertTrue(logOutput.contains("Q. Quit"));
        assertTrue(logOutput.contains("Choose action: "));
    }

    @Test
    void getUserInputReturnsUpperCaseText() {
        String simulatedInput = "q";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        RadarDisplay dummyRadar = () -> {
        };
        SimulatorView view = new SimulatorView(dummyRadar);
        String input = view.getUserInput();
        System.setIn(originalIn);
        assertEquals("Q", input);
    }

    @Test
    void displayRadarPrintsHeaderAndCallsRadarShow() {
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        RadarDisplay dummyRadar = () -> ConsoleLogger.logInfo("Radar called");
        SimulatorView view = new SimulatorView(dummyRadar);
        view.displayRadar();

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("--- Radar Display ---"));
        assertTrue(logOutput.contains("Radar called"));
    }
}