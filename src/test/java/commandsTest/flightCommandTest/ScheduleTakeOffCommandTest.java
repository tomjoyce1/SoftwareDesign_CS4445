package commandsTest.flightCommandTest;

import commands.flightcommand.ScheduleTakeOffCommand;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.SimulatorView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ScheduleTakeOffCommandTest {
    private FlightInterface flight;
    private AirTrafficMap airTrafficMap;
    private List<ScheduledFlight> scheduledFlights;
    private ScheduleTakeOffCommand command;

    @BeforeEach
    void setUp() {
        flight = mock(FlightInterface.class);
        airTrafficMap = mock(AirTrafficMap.class);
        SimulatorView view = mock(SimulatorView.class);
        scheduledFlights = new ArrayList<>();
        command = new ScheduleTakeOffCommand(flight, airTrafficMap, view, scheduledFlights);
    }

    @Test
    void execute_flightAlreadyScheduled() {
        ScheduledFlight scheduledFlight = new ScheduledFlight(flight, 1, 1);
        scheduledFlights.add(scheduledFlight);
        when(flight.getFlightNumber()).thenReturn("123");

        command.execute();

        verify(flight, never()).setScheduled(true);
    }

    @Test
    void execute_flightNotAtAirport() {
        when(airTrafficMap.findFlightPosition(flight)).thenReturn(null);
        when(flight.getFlightNumber()).thenReturn("123");

        command.execute();

        verify(flight, never()).setScheduled(true);
    }
}