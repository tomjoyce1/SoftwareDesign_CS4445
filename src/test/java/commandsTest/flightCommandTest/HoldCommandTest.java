package commandsTest.flightCommandTest;

import commands.flightcommand.HoldCommand;
import models.flight.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class HoldCommandTest {
    private Flight flight;
    private HoldCommand holdCommand;

    @BeforeEach
    public void setUp() {
        flight = Mockito.mock(Flight.class);
        holdCommand = new HoldCommand(flight);
    }

    @Test
    public void testExecute() {
        holdCommand.execute();
        verify(flight).hold();
    }
}