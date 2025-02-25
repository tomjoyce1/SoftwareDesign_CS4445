package models.flightTest;

import models.flight.Flight;
import models.states.FlightState;
import models.states.InAirState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class FlightTest {

    private static class DummyFlight extends Flight {
        boolean holdCalled = false;
        String dummyType;

        public DummyFlight(String flightNumber, String dummyType) {
            super(flightNumber);
            this.dummyType = dummyType;
        }

        @Override
        public String getType() {
            return dummyType;
        }

        @Override
        public void hold() {
            holdCalled = true;
            super.hold();
        }
    }

    private static class DummyOnRunwayState implements FlightState {
        @Override
        public void takeOff(Flight flight) {
            // Empty placeholder method
        }

        @Override
        public void land(Flight flight) {
            // Empty placeholder method
        }

        @Override
        public void hold(Flight flight) {
            // Empty placeholder method
        }

        @Override
        public String getStateName() {
            return "OnRunway";
        }
    }

    @Test
    void flightNumberIsAssignedCorrectly() {
        DummyFlight flight = new DummyFlight("FL101", "Dummy");
        assertEquals("FL101", flight.getFlightNumber());
    }

    @Test
    void initialFuelValueIsHundred() {
        DummyFlight flight = new DummyFlight("FL102", "Dummy");
        assertEquals(100, flight.getFuel());
    }

    @Test
    void takeOffReducesFuelByTen() {
        DummyFlight flight = new DummyFlight("FL103", "Dummy");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    void setFuelUpdatesFuelValue() {
        DummyFlight flight = new DummyFlight("FL104", "Dummy");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }

    @Test
    void receivingStormMessageInAirTriggersHold() {
        DummyFlight flight = new DummyFlight("FL105", "Dummy");
        flight.setState(new InAirState());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        flight.receive("WEATHER.STORM", "Severe storm");

        System.setOut(originalOut);
        assertTrue(flight.holdCalled);
        String output = outContent.toString();
        assertTrue(output.contains("Dummy FL105 received WEATHER.STORM: Severe storm"));
    }

    @Test
    void receivingNonStormMessageDoesNotTriggerHold() {
        DummyFlight flight = new DummyFlight("FL106", "Dummy");
        flight.setState(new DummyOnRunwayState());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        flight.receive("WEATHER.SUNNY", "Clear skies");

        System.setOut(originalOut);
        assertFalse(flight.holdCalled);
        String output = outContent.toString();
        assertTrue(output.contains("Dummy FL106 received WEATHER.SUNNY: Clear skies"));
    }
}