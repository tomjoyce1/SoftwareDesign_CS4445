import Models.Decorators.FuelDecorator.FuelDecorator;
import Models.Flight.Flight;
import Models.Flight.FlightTypes.FlightType;

import org.junit.jupiter.api.Test;

import Factories.FlightFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class AutomatedTest {
    private Flight passengerFlight;
    private Flight militaryFlight;
    
    @BeforeEach
    void setUp() {
        passengerFlight = FlightFactory.createFlight(FlightType.PASSENGER, "PA123");
        militaryFlight = FlightFactory.createFlight(FlightType.MILITARY, "MIL456");
    }

    @Test
    void testFlightCreation() {
        // Test passenger flight
        Assertions.assertEquals("Passenger Flight", passengerFlight.getType());
        Assertions.assertEquals("PA123", passengerFlight.getFlightNumber());
        Assertions.assertEquals("On ground/Runway", passengerFlight.getState());
        Assertions.assertEquals(100, passengerFlight.getFuel());

        // Test military flight
        Assertions.assertEquals("Military Flight", militaryFlight.getType());
        Assertions.assertEquals("MIL456", militaryFlight.getFlightNumber());
    }

    @Test
    void testFlightStateTransitions() {
        // Test takeoff state change
        passengerFlight.takeOff();
        Assertions.assertEquals("In the air", passengerFlight.getState());
        
        // Test landing state transitions
        passengerFlight.land();
        Assertions.assertEquals("Landing mode", passengerFlight.getState());
        
        // Complete landing
        passengerFlight.land();
        Assertions.assertEquals("On ground/Runway", passengerFlight.getState());
    }

    @Test
    void testFuelConsumption() {
        int initialFuel = passengerFlight.getFuel();
        passengerFlight.takeOff();
        Assertions.assertTrue(passengerFlight.getFuel() < initialFuel, 
            "Fuel should decrease after takeoff");
    }

    @Test
    void testHoldingPattern() {
        // First take off
        passengerFlight.takeOff();
        Assertions.assertEquals("In the air", passengerFlight.getState());
        
        // Test holding while in air
        passengerFlight.hold();
        Assertions.assertEquals("In the air", passengerFlight.getState());
    }

    @Test
    void testInvalidStateTransitions() {
        // Try to land while on runway
        passengerFlight.land();
        Assertions.assertEquals("On ground/Runway", passengerFlight.getState(),
            "Should remain on runway when trying to land while already on ground");
        
        // Take off
        passengerFlight.takeOff();
        
        // Try to take off while in air
        passengerFlight.takeOff();
        Assertions.assertEquals("In the air", passengerFlight.getState(),
            "Should remain in air when trying to take off while already flying");
    }

    @Test
    void testDifferentFlightTypes() {
        Flight cargoFlight = FlightFactory.createFlight(FlightType.CARGO, "CG789");
        Flight privateFlight = FlightFactory.createFlight(FlightType.PRIVATE, "PV101");
        
        Assertions.assertEquals("Cargo Flight", cargoFlight.getType());
        Assertions.assertEquals("Private Flight", privateFlight.getType());
    }

    @Test
    void testFuelDecorator() {
        Flight decoratedFlight = new FuelDecorator(passengerFlight);
        int initialFuel = decoratedFlight.getFuel();
        
        decoratedFlight.takeOff();
        
        Assertions.assertTrue(decoratedFlight.getFuel() < initialFuel,
            "Decorated flight should consume fuel on takeoff");
    }
}
