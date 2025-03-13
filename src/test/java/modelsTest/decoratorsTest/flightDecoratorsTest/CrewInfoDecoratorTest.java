package modelsTest.decoratorsTest.flightDecoratorsTest;

import models.decorators.flightdecorator.CrewInfoDecorator;
import models.flight.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrewInfoDecoratorTest {

    private Flight flight;
    private CrewInfoDecorator crewInfoDecorator;

    @BeforeEach
    void setUp() {
        flight = Mockito.mock(Flight.class);
        crewInfoDecorator = new CrewInfoDecorator(flight, "John Doe", 5);
    }

    @Test
    void testGetPilotName() {
        assertEquals("John Doe", crewInfoDecorator.getPilotName());
    }

    @Test
    void testSetPilotName() {
        crewInfoDecorator.setPilotName("Jane Doe");
        assertEquals("Jane Doe", crewInfoDecorator.getPilotName());
    }

    @Test
    void testGetCrewCount() {
        assertEquals(5, crewInfoDecorator.getCrewCount());
    }

    @Test
    void testSetCrewCount() {
        crewInfoDecorator.setCrewCount(10);
        assertEquals(10, crewInfoDecorator.getCrewCount());
    }

    @Test
    void testTakeOff() {
        crewInfoDecorator.takeOff();
        Mockito.verify(flight).takeOff();
    }

    @Test
    void testGetType() {
        Mockito.when(flight.getType()).thenReturn("Private");
        assertEquals("Private", crewInfoDecorator.getType());
    }

    @Test
    void testGetFuel() {
        Mockito.when(flight.getFuel()).thenReturn(100);
        assertEquals(100, crewInfoDecorator.getFuel());
    }

    @Test
    void testConsumeFuel() {
        crewInfoDecorator.consumeFuel();
        Mockito.verify(flight).consumeFuel();
    }

    @Test
    void testSetFuel() {
        crewInfoDecorator.setFuel(50);
        Mockito.verify(flight).setFuel(50);
    }
}