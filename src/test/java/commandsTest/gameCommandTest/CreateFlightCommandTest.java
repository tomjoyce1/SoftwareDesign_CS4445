package commandsTest.gameCommandTest;

import commands.gamecommand.CreateFlightCommand;
import bookmarks.InterceptorDispatcher;
import factories.FlightFactory;
import models.flight.Flight;
import models.flight.flighttypes.FlightType;
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
    void displayErrorWhenFlightNumberAlreadyExists() {
        List<Flight> flights = new ArrayList<>();
        Flight existingFlight = FlightFactory.createFlight(FlightType.PRIVATE, "FL123");
        flights.add(existingFlight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("PRIVATE", "FL123");
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        assertEquals(1, flights.size());
        verify(dispatcher, never()).dispatch(anyString());
        ConsoleLogger.logError("Flight number already exists!");
    }

    @Test
    void createFlightSuccessfullyWhenFlightNumberIsUnique() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("PRIVATE", "FL124");
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        assertEquals(1, flights.size());
        Flight createdFlight = flights.getFirst();
        assertEquals("Private Flight", createdFlight.getType());
        assertEquals("FL124", createdFlight.getFlightNumber());
        verify(dispatcher).dispatch(contains("FL124"));
        ConsoleLogger.logSuccess("Created " + createdFlight.getType() + " FL124");
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