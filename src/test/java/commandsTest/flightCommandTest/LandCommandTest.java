package commandsTest.flightCommandTest;

import commands.flightcommand.LandCommand;
import models.flight.Flight;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class LandCommandTest {

    @Test
    public void executeCallsLandOnFlight() {
        Flight flight = Mockito.mock(Flight.class);
        LandCommand landCommand = new LandCommand(flight);

        landCommand.execute();

        verify(flight).land();
    }
}