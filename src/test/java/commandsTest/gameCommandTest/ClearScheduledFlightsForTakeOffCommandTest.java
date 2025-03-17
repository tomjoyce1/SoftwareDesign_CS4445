package commandsTest.gameCommandTest;

import commands.gamecommand.ClearScheduledFlightsForTakeOffCommand;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import models.map.MapCell;
import models.map.takeoff.ScheduledFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import views.SimulatorView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ClearScheduledFlightsForTakeOffCommandTest {

    @Mock
    private AirTrafficMap airTrafficMap;
    @Mock
    private SimulatorView view;
    @Mock
    private ScheduledFlight scheduledFlight;

    private List<ScheduledFlight> scheduledFlights;
    private ClearScheduledFlightsForTakeOffCommand command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        scheduledFlights = new ArrayList<>();
        command = new ClearScheduledFlightsForTakeOffCommand(airTrafficMap, scheduledFlights, view);
    }

    @Test
    void executeWithFlightNotTakingOffLogsWarning() {
        FlightInterface flight = mock(FlightInterface.class);
        when(scheduledFlight.getFlight()).thenReturn(flight);
        when(flight.takeOff()).thenReturn(false);
        when(flight.getFlightNumber()).thenReturn("123");
        when(flight.getState()).thenReturn("Grounded");

        scheduledFlights.add(scheduledFlight);

        command.execute();

        assertFalse(scheduledFlights.isEmpty());
    }

    @Test
    void executeWithScheduledFlightsProcessesAndClearsFlights() {
        FlightInterface flight = mock(FlightInterface.class);
        when(scheduledFlight.getFlight()).thenReturn(flight);
        when(flight.takeOff()).thenReturn(true);
        when(flight.getFlightNumber()).thenReturn("123");
        when(flight.getState()).thenReturn("Grounded");

        MapCell cell = mock(MapCell.class);
        when(airTrafficMap.getCell(anyInt(), anyInt())).thenReturn(cell);
        when(cell.getAirportLabel()).thenReturn("Airport");

        scheduledFlights.add(scheduledFlight);

        command.execute();

        assertTrue(scheduledFlights.isEmpty());
    }

    @Test
    void executeWithMultipleFlightsProcessesAllCorrectly() {
        ScheduledFlight flight1 = mock(ScheduledFlight.class);
        ScheduledFlight flight2 = mock(ScheduledFlight.class);
        FlightInterface flightInterface1 = mock(FlightInterface.class);
        FlightInterface flightInterface2 = mock(FlightInterface.class);

        when(flight1.getFlight()).thenReturn(flightInterface1);
        when(flight2.getFlight()).thenReturn(flightInterface2);
        when(flightInterface1.takeOff()).thenReturn(true);
        when(flightInterface2.takeOff()).thenReturn(true);
        when(flightInterface1.getState()).thenReturn("Flying");
        when(flightInterface2.getState()).thenReturn("Flying");

        MapCell cell = mock(MapCell.class);
        when(airTrafficMap.getCell(anyInt(), anyInt())).thenReturn(cell);

        scheduledFlights.add(flight1);
        scheduledFlights.add(flight2);

        command.execute();

        assertTrue(scheduledFlights.isEmpty());
    }
}