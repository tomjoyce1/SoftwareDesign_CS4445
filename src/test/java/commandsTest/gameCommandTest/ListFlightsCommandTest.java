package commandsTest.gameCommandTest;

import commands.gamecommand.ListFlightsCommand;
import models.flight.Flight;
import models.flight.FlightInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import views.ConsoleLogger;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ListFlightsCommandTest {

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
    void listFlightsCommandPrintsNoFlightsWhenListIsEmpty() {
        List<FlightInterface> flights = Collections.emptyList();
        ListFlightsCommand command = new ListFlightsCommand(flights);

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        command.execute();

        String output = testHandler.getLogMessages();
        assertTrue(output.contains("=== Current Flights ==="));
        assertTrue(output.contains("No flights available."));
    }

    @Test
    void listFlightsCommandPrintsFlightDetailsWhenFlightsAreAvailable() {
        FlightInterface flight1 = Mockito.mock(Flight.class);
        FlightInterface flight2 = Mockito.mock(Flight.class);
        Mockito.when(flight1.getType()).thenReturn("COMMERCIAL");
        Mockito.when(flight1.getFlightNumber()).thenReturn("FL100");
        Mockito.when(flight1.getState()).thenReturn("In Air");
        Mockito.when(flight2.getType()).thenReturn("PRIVATE");
        Mockito.when(flight2.getFlightNumber()).thenReturn("FL200");
        Mockito.when(flight2.getState()).thenReturn("Landed");

        List<FlightInterface> flights = List.of(flight1, flight2);
        ListFlightsCommand command = new ListFlightsCommand(flights);

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        command.execute();

        String output = testHandler.getLogMessages();
        assertTrue(output.contains("=== Current Flights ==="));
        assertTrue(output.contains("COMMERCIAL FL100 - Status: In Air"));
        assertTrue(output.contains("PRIVATE FL200 - Status: Landed"));
    }
}