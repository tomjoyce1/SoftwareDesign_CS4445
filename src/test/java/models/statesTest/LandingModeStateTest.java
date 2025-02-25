package models.statesTest;

import models.flight.Flight;
import models.states.FlightState;
import models.states.LandingModeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.ConsoleLogger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.*;

class LandingModeStateTest {

    private static class DummyFlight extends Flight {
        private FlightState state;

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
            this.state = state;
        }

        public FlightState getDummyState() {
            return state;
        }
    }

    private static class TestHandler extends StreamHandler {
        private LogRecord lastRecord;

        @Override
        public synchronized void publish(LogRecord logRecord) {
            lastRecord = logRecord;
        }

        public String getLastMessage() {
            return lastRecord != null ? lastRecord.getMessage() : null;
        }
    }

    private TestHandler testHandler;

    @BeforeEach
    void setUp() {
        testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);
    }

    @Test
    void landingModeTakeOffPrintsWarningMessage() {
        LandingModeState state = new LandingModeState();
        DummyFlight flight = new DummyFlight("FL-LND-01");

        state.takeOff(flight);

        String output = testHandler.getLastMessage();
        assert output != null;
        assertTrue(output.contains("Cannot take off while landing"));
    }

    @Test
    void landingModeLandPrintsCompletionMessageAndChangesFlightState() {
        LandingModeState state = new LandingModeState();
        DummyFlight flight = new DummyFlight("FL-LND-02");

        state.land(flight);

        String output = testHandler.getLastMessage();
        assert output != null;
        assertTrue(output.contains("Landing completed"));
        assertNotNull(flight.getDummyState());
        assertNotEquals("Landing mode", flight.getDummyState().getStateName());
    }

    @Test
    void landingModeHoldPrintsAbortMessageAndChangesFlightState() {
        LandingModeState state = new LandingModeState();
        DummyFlight flight = new DummyFlight("FL-LND-03");

        state.hold(flight);

        String output = testHandler.getLastMessage();
        assert output != null;
        assertTrue(output.contains("Aborting landing attempt"));
        assertNotNull(flight.getDummyState());
        assertEquals("In the air", flight.getDummyState().getStateName());
    }

    @Test
    void landingModeGetStateNameReturnsLandingMode() {
        LandingModeState state = new LandingModeState();
        assertEquals("Landing mode", state.getStateName());
    }
}