package commandsTest.flightCommandTest;

import commands.flightcommand.TakeOffCommand;
import models.flight.Flight;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class TakeOffCommandTest {
    @Test
    void executeCallsTakeOffOnFlight() {
        Flight flight = Mockito.mock(Flight.class);
        TakeOffCommand takeOffCommand = new TakeOffCommand(flight);

        takeOffCommand.execute();

        verify(flight).takeOff();
    }
}
