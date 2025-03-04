package modelsTest.statesTest;

import models.flight.Flight;
import models.states.OnRunwayState;
import models.states.FlightState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.ConsoleLogger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.*;

class OnRunwayStateTest {

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
    void onRunwayTakeOffPrintsTaxiingMessageAndChangesState() {
        OnRunwayState state = new OnRunwayState();
        DummyFlight flight = new DummyFlight("FL-RWY-01");

        state.takeOff(flight);

        String output = testHandler.getLastMessage();
        assert output != null;
        assertTrue(output.contains("Taxiing to take off"));
        assertNotNull(flight.getDummyState());
        assertEquals("In the air", flight.getDummyState().getStateName());
    }

    @Test
    void onRunwayLandPrintsAlreadyOnTheGround() {
        OnRunwayState state = new OnRunwayState();
        DummyFlight flight = new DummyFlight("FL-RWY-02");

        state.land(flight);

        String output = testHandler.getLastMessage();
        assert output != null;
        assertTrue(output.contains("Already on the ground"));
    }

    @Test
    void onRunwayHoldPrintsCannotHoldWhileOnGround() {
        OnRunwayState state = new OnRunwayState();
        DummyFlight flight = new DummyFlight("FL-RWY-03");

        state.hold(flight);

        String output = testHandler.getLastMessage();
        assert output != null;
        assertTrue(output.contains("Cannot hold while on ground"));
    }

    @Test
    void onRunwayGetStateNameReturnsOnGroundRunway() {
        OnRunwayState state = new OnRunwayState();
        assertEquals("On ground/Runway", state.getStateName());
    }
}