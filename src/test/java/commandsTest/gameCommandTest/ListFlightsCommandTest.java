package commandsTest.gameCommandTest;

import commands.gamecommand.ListFlightsCommand;
import models.flight.Flight;
import views.SimulatorView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListFlightsCommandTest {

    @Test
    public void listFlightsCommandPrintsNoFlightsWhenListIsEmpty() {
        List<Flight> flights = Collections.emptyList();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        ListFlightsCommand command = new ListFlightsCommand(flights, view);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        command.execute();

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("=== Current Flights ==="));
        assertTrue(output.contains("No flights available."));
    }

    @Test
    public void listFlightsCommandPrintsFlightDetailsWhenFlightsAreAvailable() {
        Flight flight1 = Mockito.mock(Flight.class);
        Flight flight2 = Mockito.mock(Flight.class);
        Mockito.when(flight1.getType()).thenReturn("COMMERCIAL");
        Mockito.when(flight1.getFlightNumber()).thenReturn("FL100");
        Mockito.when(flight1.getState()).thenReturn("In Air");
        Mockito.when(flight2.getType()).thenReturn("PRIVATE");
        Mockito.when(flight2.getFlightNumber()).thenReturn("FL200");
        Mockito.when(flight2.getState()).thenReturn("Landed");

        List<Flight> flights = Arrays.asList(flight1, flight2);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        ListFlightsCommand command = new ListFlightsCommand(flights, view);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        command.execute();

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("=== Current Flights ==="));
        assertTrue(output.contains("COMMERCIAL FL100 - Status: In Air"));
        assertTrue(output.contains("PRIVATE FL200 - Status: Landed"));
    }
}