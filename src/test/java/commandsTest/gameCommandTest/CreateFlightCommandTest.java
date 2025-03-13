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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

class CreateFlightCommandTest {

    @Test
    void createFlightSuccessfullyWhenValidTypeAndNumberProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        // Provide all required inputs for a successful flight creation.
        when(view.getUserInput()).thenReturn("PRIVATE", "FL123", "100", "Some Agency", "John Doe", "10");
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        assertFalse(flights.isEmpty());
        // Use get(0) as ArrayList does not have getFirst()
        Flight createdFlight = flights.get(0);
        // The decorated flight now returns "Private Flight"
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
        // Only two inputs are needed since duplicate is detected early.
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
        // Provide complete input for a unique flight.
        when(view.getUserInput()).thenReturn("PRIVATE", "FL124", "150", "Some Agency", "John Doe", "12");
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        assertEquals(1, flights.size());
        Flight createdFlight = flights.get(0);
        assertEquals("Private Flight", createdFlight.getType());
        assertEquals("FL124", createdFlight.getFlightNumber());
        verify(dispatcher).dispatch(contains("FL124"));
        ConsoleLogger.logSuccess("Created " + createdFlight.getType() + " FL124");
    }

    @Test
    void displayErrorWhenInvalidPassengerCountIsProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        // Supply an invalid passenger count; later prompts will not be reached.
        when(view.getUserInput()).thenReturn("PRIVATE", "FL125", "invalid_passenger_count");
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        
        // Execute without expecting an exception.
        command.execute();
        
        // Validate that no flight was added and dispatcher was not called.
        assertTrue(flights.isEmpty());
        verify(dispatcher, never()).dispatch(anyString());
    }

    @Test
    void displayErrorWhenInvalidCrewCountIsProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        // Provide complete inputs but with an invalid crew count.
        when(view.getUserInput()).thenReturn("PRIVATE", "FL126", "10", "Some Agency", "John Doe", "invalid_crew_count");
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        
        // Execute the command and assert that no flight is created and dispatcher is not called.
        command.execute();
        assertTrue(flights.isEmpty());
        verify(dispatcher, never()).dispatch(anyString());
        // Optionally, verify that the error message was logged (requires a spy or capturing log output).
    }   

    @Test
    void displayErrorWhenInvalidFlightTypeIsProvided() {
        List<Flight> flights = new ArrayList<>();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        // Supply all required inputs even though the flight type is invalid.
        when(view.getUserInput()).thenReturn("INVALID_TYPE", "FL123", "100", "Some Agency", "John Doe", "10");
        CreateFlightCommand command = new CreateFlightCommand(flights, view, dispatcher);
        command.execute();
        // The command should catch the IllegalArgumentException and not add a flight.
        assertTrue(flights.isEmpty());
        verify(dispatcher, never()).dispatch(anyString());
        ConsoleLogger.logError("Invalid flight type or input!");
    }
}
