package modelsTest.statesTest;

import models.flight.Flight;
import models.states.FlightState;
import models.states.InAirState;
import org.junit.jupiter.api.Test;
import views.ConsoleLogger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.*;

class InAirStateTest {

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
        private final StringBuilder logMessages = new StringBuilder();

        @Override
        public synchronized void publish(LogRecord logRecord) {
            logMessages.append(logRecord.getMessage()).append("\n");
        }

        public String getLogMessages() {
            return logMessages.toString();
        }
    }

    @Test
    void inAirStateTakeOffPrintsAlreadyInTheClouds() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-01");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        state.takeOff(flight);

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("Already in the clouds"));
    }

    @Test
    void inAirStateLandPrintsTransitioningMessageAndChangesFlightState() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-02");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        state.land(flight);

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("Transitioning to landing now"));
        assertNotNull(flight.getDummyState());
        assertNotEquals("In the air", flight.getDummyState().getStateName());
    }

    @Test
    void inAirStateHoldPrintsHoldingAtCurrentAltitude() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-03");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        state.hold(flight);

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("Holding at current altitude"));
    }

    @Test
    void inAirStateGetStateNameReturnsInTheAir() {
        InAirState state = new InAirState();
        assertEquals("In the air", state.getStateName());
    }

    @Test
    void inAirStateCallingLandTwicePrintsTransitioningMessageTwice() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-04");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        state.land(flight);
        state.land(flight);

        String logOutput = testHandler.getLogMessages();
        int occurrences = logOutput.split("Transitioning to landing now", -1).length - 1;
        assertEquals(2, occurrences);
    }

    @Test
    void inAirStateHoldCalledMultipleTimesPrintsMessageEachTime() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-05");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        state.hold(flight);
        state.hold(flight);

        String logOutput = testHandler.getLogMessages();
        int occurrences = logOutput.split("Holding at current altitude", -1).length - 1;
        assertEquals(2, occurrences);
    }
}