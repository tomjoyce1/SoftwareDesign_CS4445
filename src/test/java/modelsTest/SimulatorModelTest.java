package modelsTest;

import models.SimulatorModel;
import models.flight.Flight;
import models.flight.FlightInterface;
import models.states.FlightState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SimulatorModelTest {

    private static class DummyFlight extends Flight {
        public DummyFlight(String flightNumber) {
            super(flightNumber);
        }

        @Override
        public String getType() {
            return "Dummy";
        }

        @Override
        public void hold() {
            // Empty placeholder method
        }

        @Override
        public void setState(FlightState state) {
            // Empty placeholder method
        }
    }

    @Test
    void simulatorModelWeatherStationIsInitialized() {
        SimulatorModel model = new SimulatorModel();
        assertNotNull(model.getWeatherStation());
    }

    @Test
    void simulatorModelFlightsListIsInitiallyEmpty() {
        SimulatorModel model = new SimulatorModel();
        List<FlightInterface> flights = model.getFlights();
        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }

    @Test
    void simulatorModelAddingFlightUpdatesFlightsList() {
        SimulatorModel model = new SimulatorModel();
        DummyFlight flight = new DummyFlight("FL-TEST-01");
        model.getFlights().add(flight);
        List<FlightInterface> flights = model.getFlights();
        assertEquals(1, flights.size());
        assertEquals("Dummy", flights.getFirst().getType());
    }

    @Test
    void simulatorModelDispatcherIsInitialized() {
        SimulatorModel model = new SimulatorModel();
        assertNotNull(model.getDispatcher());
    }
}