package commandsTest.gameCommandTest;

import commands.gamecommand.CheckFlightStatusCommand;
import models.flight.Flight;
import views.SimulatorView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CheckFlightStatusCommandTest {
    private List<Flight> flights;
    private SimulatorView view;
    private CheckFlightStatusCommand checkFlightStatusCommand;

    @BeforeEach
    void setUp() {
        flights = new ArrayList<>();
        view = Mockito.mock(SimulatorView.class);
        checkFlightStatusCommand = new CheckFlightStatusCommand(flights, view);
    }

    @Test
    void testExecuteWithNoFlights() {
        checkFlightStatusCommand.execute();
        verify(view, never()).getUserInput();
    }

    @Test
    void testExecuteWithFlightFound() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL123");
        when(flight.getType()).thenReturn("TypeA");
        when(flight.getState()).thenReturn("OnRunway");
        when(flight.getFuel()).thenReturn(100);
        flights.add(flight);

        when(view.getUserInput()).thenReturn("FL123");

        checkFlightStatusCommand.execute();

        verify(view).getUserInput();
    }

    @Test
    void testExecuteWithFlightNotFound() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL123");
        flights.add(flight);

        when(view.getUserInput()).thenReturn("FL999");

        checkFlightStatusCommand.execute();

        verify(view).getUserInput();
        verify(view, never()).displayRadar();
    }
}