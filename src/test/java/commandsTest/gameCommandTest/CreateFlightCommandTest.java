package commandsTest.gameCommandTest;

import commands.gamecommand.CreateFlightCommand;
import bookmarks.InterceptorDispatcher;
import models.flight.Flight;
import views.SimulatorView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateFlightCommandTest {

    @Test
    void createFlightSuccessfullyWhenValidTypeAndNumberProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        when(view.getUserInput()).thenReturn("PRIVATE", "FL123");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        System.setOut(originalOut);
        assertFalse(flights.isEmpty());
        Flight createdFlight = flights.getFirst();
        assertEquals("Private Flight", createdFlight.getType());
        assertTrue(outContent.toString().contains("Created " + createdFlight.getType() + " FL123"));
        verify(dispatcher).dispatch(contains("FL123"));
    }

    @Test
    void displayErrorWhenInvalidFlightTypeIsProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("INVALID_TYPE", "FL123");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        System.setOut(originalOut);
        assertTrue(flights.isEmpty());
        assertTrue(outContent.toString().contains("Invalid flight type!"));
    }
}