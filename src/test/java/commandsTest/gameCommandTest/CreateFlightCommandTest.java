package commandsTest.gameCommandTest;

import commands.gamecommand.CreateFlightCommand;
import bookmarks.InterceptorDispatcher;
import models.flight.Flight;
import views.ConsoleLogger;
import views.SimulatorView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateFlightCommandTest {

    @Test
    void createFlightSuccessfullyWhenValidTypeAndNumberProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        when(view.getUserInput()).thenReturn("PRIVATE", "FL123");
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        assertFalse(flights.isEmpty());
        Flight createdFlight = flights.getFirst();
        assertEquals("Private Flight", createdFlight.getType());
        verify(dispatcher).dispatch(contains("FL123"));
        ConsoleLogger.logSuccess("Created " + createdFlight.getType() + " FL123");
    }

    @Test
    void displayErrorWhenInvalidFlightTypeIsProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("INVALID_TYPE", "FL123");
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        assertTrue(flights.isEmpty());
        ConsoleLogger.logError("Invalid flight type!");
    }
}