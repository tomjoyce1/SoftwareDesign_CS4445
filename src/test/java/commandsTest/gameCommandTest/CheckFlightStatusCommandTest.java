package commandsTest.gameCommandTest;

import commands.gamecommand.CheckFlightStatusCommand;
import models.flight.FlightInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import views.SimulatorView;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class CheckFlightStatusCommandTest {

    @Mock
    private SimulatorView view;
    @Mock
    private FlightInterface flight;
    private List<FlightInterface> flights;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flights = Collections.singletonList(flight);
    }

    @Test
    void executeNoFlightsAvailableLogsError() {
        new CheckFlightStatusCommand(Collections.emptyList(), view).execute();
        verify(view, never()).getUserInput();
        verify(flight, never()).getFlightNumber();
    }

    @Test
    void executeFlightFoundLogsFlightStatus() {
        when(view.getUserInput()).thenReturn("123");
        when(flight.getFlightNumber()).thenReturn("123");
        when(flight.isScheduled()).thenReturn(true);
        when(flight.getType()).thenReturn("Type");
        when(flight.getState()).thenReturn("State");
        when(flight.getFuel()).thenReturn(100);

        new CheckFlightStatusCommand(flights, view).execute();

        verify(view).getUserInput();
        verify(flight, atLeastOnce()).getFlightNumber();
        verify(flight, atLeastOnce()).isScheduled();
        verify(flight, atLeastOnce()).getType();
        verify(flight, atLeastOnce()).getState();
        verify(flight, atLeastOnce()).getFuel();
    }

    @Test
    void executeFlightNotFoundLogsError() {
        when(view.getUserInput()).thenReturn("999");
        when(flight.getFlightNumber()).thenReturn("123");

        new CheckFlightStatusCommand(flights, view).execute();

        verify(view).getUserInput();
        verify(flight, atLeastOnce()).getFlightNumber();
    }
}