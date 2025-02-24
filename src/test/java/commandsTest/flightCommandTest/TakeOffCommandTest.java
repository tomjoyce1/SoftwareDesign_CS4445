package commandsTest.flightCommandTest;

import commands.flightcommand.TakeOffCommand;
import models.flight.Flight;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class TakeOffCommandTest {
    @Test
    public void executeCallsTakeOffOnFlight() {
        Flight flight = Mockito.mock(Flight.class);
        TakeOffCommand takeOffCommand = new TakeOffCommand(flight);

        takeOffCommand.execute();

        verify(flight).takeOff();
    }
}
