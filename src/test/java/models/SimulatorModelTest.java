package models;

import models.flight.Flight;
import models.states.FlightState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SimulatorModelTest {

    private static class DummyFlight extends Flight {
        public DummyFlight(String flightNumber) { super(flightNumber); }
        @Override public String getType() { return "Dummy"; }
        @Override public void hold() { }
        @Override public void setState(FlightState state) {
        }
    }

    @Test
    public void simulatorModelWeatherStationIsInitialized() {
        SimulatorModel model = new SimulatorModel();
        assertNotNull(model.getWeatherStation());
    }

    @Test
    public void simulatorModelFlightsListIsInitiallyEmpty() {
        SimulatorModel model = new SimulatorModel();
        List<Flight> flights = model.getFlights();
        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }

    @Test
    public void simulatorModelAddingFlightUpdatesFlightsList() {
        SimulatorModel model = new SimulatorModel();
        DummyFlight flight = new DummyFlight("FL-TEST-01");
        model.getFlights().add(flight);
        List<Flight> flights = model.getFlights();
        assertEquals(1, flights.size());
        assertEquals("Dummy", flights.getFirst().getType());
    }

    @Test
    public void simulatorModelDispatcherIsInitialized() {
        SimulatorModel model = new SimulatorModel();
        assertNotNull(model.getDispatcher());
    }
}