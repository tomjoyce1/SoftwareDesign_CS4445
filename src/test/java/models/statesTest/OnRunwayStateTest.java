package models.statesTest;

import models.flight.Flight;
import models.states.OnRunwayState;
import models.states.FlightState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OnRunwayStateTest {

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
        }

        @Override
        public void setState(FlightState state) {
            this.state = state;
        }

        public FlightState getDummyState() {
            return state;
        }
    }

    @Test
    public void onRunwayTakeOffPrintsTaxiingMessageAndChangesState() {
        OnRunwayState state = new OnRunwayState();
        DummyFlight flight = new DummyFlight("FL-RWY-01");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.takeOff(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Taxiing to take off"));
        assertNotNull(flight.getDummyState());
        assertEquals("In the air", flight.getDummyState().getStateName());
    }

    @Test
    public void onRunwayLandPrintsAlreadyOnTheGround() {
        OnRunwayState state = new OnRunwayState();
        DummyFlight flight = new DummyFlight("FL-RWY-02");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.land(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Already on the ground"));
    }

    @Test
    public void onRunwayHoldPrintsCannotHoldWhileOnGround() {
        OnRunwayState state = new OnRunwayState();
        DummyFlight flight = new DummyFlight("FL-RWY-03");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        state.hold(flight);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Cannot hold while on ground"));
    }

    @Test
    public void onRunwayGetStateNameReturnsOnGroundRunway() {
        OnRunwayState state = new OnRunwayState();
        assertEquals("On ground/Runway", state.getStateName());
    }
}