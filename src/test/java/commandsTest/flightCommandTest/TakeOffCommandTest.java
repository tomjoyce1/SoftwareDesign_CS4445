package commandsTest.flightCommandTest;

import commands.flightcommand.TakeOffCommand;
import models.flight.Flight;
import models.map.AirTrafficMap;
import views.SimulatorView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class TakeOffCommandTest {
    @Test
    void executeCallsTakeOffOnFlight() {
        Flight flight = Mockito.mock(Flight.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        TakeOffCommand takeOffCommand = new TakeOffCommand(flight, airTrafficMap, view);

        takeOffCommand.execute();

        verify(flight).takeOff();
    }
}
