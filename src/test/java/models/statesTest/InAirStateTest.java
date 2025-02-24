package models.statesTest;

import models.flight.Flight;
import models.states.FlightState;
import models.states.InAirState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InAirStateTest {

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
    public void inAirStateTakeOffPrintsAlreadyInTheClouds() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-01");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.takeOff(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Already in the clouds"));
    }

    @Test
    public void inAirStateLandPrintsTransitioningMessageAndChangesFlightState() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-02");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.land(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Transitioning to landing now"));
        // Verify that flight state has changed to a landing state
        assertNotNull(flight.getDummyState());
        assertNotEquals("In the air", flight.getDummyState().getStateName());
    }

    @Test
    public void inAirStateHoldPrintsHoldingAtCurrentAltitude() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-03");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.hold(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Holding at current altitude"));
    }

    @Test
    public void inAirStateGetStateNameReturnsInTheAir() {
        InAirState state = new InAirState();
        assertEquals("In the air", state.getStateName());
    }

    @Test
    public void inAirStateCallingLandTwicePrintsTransitioningMessageTwice() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-04");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.land(flight);
        state.land(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        int occurrences = output.split("Transitioning to landing now", -1).length - 1;
        assertEquals(2, occurrences);
    }

    @Test
    public void inAirStateHoldCalledMultipleTimesPrintsMessageEachTime() {
        InAirState state = new InAirState();
        DummyFlight flight = new DummyFlight("FL-AIR-05");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.hold(flight);
        state.hold(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        int occurrences = output.split("Holding at current altitude", -1).length - 1;
        assertEquals(2, occurrences);
    }
}