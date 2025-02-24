package models.statesTest;

import models.flight.Flight;
import models.states.FlightState;
import models.states.LandingModeState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LandingModeStateTest {

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
        public void hold() { }

        @Override
        public void setState(FlightState state) {
            this.state = state;
        }

        public FlightState getDummyState() {
            return state;
        }
    }

    @Test
    public void landingModeTakeOffPrintsWarningMessage() {
        LandingModeState state = new LandingModeState();
        DummyFlight flight = new DummyFlight("FL-LND-01");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.takeOff(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Cannot take off while landing"));
    }

    @Test
    public void landingModeLandPrintsCompletionMessageAndChangesFlightState() {
        LandingModeState state = new LandingModeState();
        DummyFlight flight = new DummyFlight("FL-LND-02");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.land(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Landing completed"));
        assertNotNull(flight.getDummyState());
        assertNotEquals("Landing mode", flight.getDummyState().getStateName());
    }

    @Test
    public void landingModeHoldPrintsAbortMessageAndChangesFlightState() {
        LandingModeState state = new LandingModeState();
        DummyFlight flight = new DummyFlight("FL-LND-03");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.hold(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Aborting landing attempt"));
        assertNotNull(flight.getDummyState());
        assertEquals("In the air", flight.getDummyState().getStateName());
    }

    @Test
    public void landingModeGetStateNameReturnsLandingMode() {
        LandingModeState state = new LandingModeState();
        assertEquals("Landing mode", state.getStateName());
    }
}