package commandsTest.gameCommandTest;

import commands.gamecommand.CreateFlightCommand;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.SimulatorView;
import bookmarks.InterceptorDispatcher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateFlightCommandTest {
    private List<FlightInterface> flights;
    private SimulatorView view;
    private CreateFlightCommand command;

    @BeforeEach
    void setUp() {
        flights = new ArrayList<>();
        view = mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = mock(AirTrafficMap.class);
        command = new CreateFlightCommand(flights, view, dispatcher, airTrafficMap);
    }

    @Test
    void executeCreatesFlightSuccessfully() {
        when(view.getUserInput()).thenReturn("1", "FL123", "100", "Airline", "John Doe", "5");
        command.execute();
        assertEquals(1, flights.size());
        assertEquals("FL123", flights.getFirst().getFlightNumber());
    }

    @Test
    void executeFailsWhenFlightNumberAlreadyExists() {
        FlightInterface existingFlight = mock(FlightInterface.class);
        when(existingFlight.getFlightNumber()).thenReturn("FL123");
        flights.add(existingFlight);

        when(view.getUserInput()).thenReturn("1", "FL123");
        command.execute();
        assertEquals(1, flights.size());
    }

    @Test
    void executeFailsWhenInvalidFlightTypeIndex() {
        when(view.getUserInput()).thenReturn("0");
        command.execute();
        assertEquals(0, flights.size());
    }

    @Test
    void executeFailsWhenInvalidPilotName() {
        when(view.getUserInput()).thenReturn("1", "FL123", "100", "Airline", "John123", "5");
        command.execute();
        assertEquals(0, flights.size());
    }

    @Test
    void executeFailsWhenInvalidCrewCount() {
        when(view.getUserInput()).thenReturn("1", "FL123", "100", "Airline", "John Doe", "invalid");
        assertThrows(NumberFormatException.class, () -> command.execute());
    }
}