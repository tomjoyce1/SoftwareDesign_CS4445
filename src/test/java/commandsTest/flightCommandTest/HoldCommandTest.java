package commandsTest.flightCommandTest;

import commands.flightcommand.HoldCommand;
import models.flight.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class HoldCommandTest {
    private Flight flight;
    private HoldCommand holdCommand;

    @BeforeEach
    void setUp() {
        flight = Mockito.mock(Flight.class);
        holdCommand = new HoldCommand(flight);
    }

    @Test
    void testExecute() {
        holdCommand.execute();
        verify(flight).hold();
    }
}