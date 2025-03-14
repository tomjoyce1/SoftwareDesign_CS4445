package commandsTest.gameCommandTest;

import commands.gamecommand.ViewFlightInfo;
import models.decorators.flightdecorator.CrewInfoDecorator;
import models.decorators.flightdecorator.FlightAgencyDecorator;
import models.decorators.flightdecorator.PassengerDecorator;
import models.flight.IFlight;
import models.flight.flighttypes.PrivateFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import views.SimulatorView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ViewFlightInfoTest {

    private List<IFlight> flights;
    private SimulatorView view;
    private ViewFlightInfo viewFlightInfo;

    @BeforeEach
    void setUp() {
        flights = new ArrayList<>();
        view = Mockito.mock(SimulatorView.class);
        viewFlightInfo = new ViewFlightInfo(flights, view);
    }

    @Test
    void testExecuteWithNoFlights() {
        viewFlightInfo.execute();
        verify(view, never()).getUserInput();
        assertTrue(flights.isEmpty());
    }

    @Test
    void testExecuteWithFlights() {
        IFlight flight = new PrivateFlight("FL123");
        flights.add(flight);
        when(view.getUserInput()).thenReturn("FL123");

        viewFlightInfo.execute();

        verify(view, times(1)).getUserInput();
    }

    @Test
    void testDisplayFlightInfo() {
        IFlight flight = new PrivateFlight("FL123");
        flight = new PassengerDecorator(flight, 100);
        flight = new FlightAgencyDecorator(flight, "Some Agency");
        flight = new CrewInfoDecorator(flight, "John Doe", 5);
        flights.add(flight);
        when(view.getUserInput()).thenReturn("FL123");

        viewFlightInfo.execute();

        verify(view, times(1)).getUserInput();
    }

    @Test
    void testFlightNotFound() {
        IFlight flight = new PrivateFlight("FL123");
        flights.add(flight);
        when(view.getUserInput()).thenReturn("FL999");

        viewFlightInfo.execute();

        verify(view, times(1)).getUserInput();
    }
}