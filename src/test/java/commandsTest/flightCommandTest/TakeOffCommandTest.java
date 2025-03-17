package commandsTest.flightCommandTest;

import commands.flightcommand.TakeOffCommand;
import models.flight.Flight;
import org.junit.jupiter.api.Test;
import models.map.AirTrafficMap;
import views.SimulatorView;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class TakeOffCommandTest {

    @Test
    void executeCallsTakeOffOnFlight() {
        Flight flight = Mockito.mock(Flight.class);
        AirTrafficMap map = new AirTrafficMap(0, 0);
        SimulatorView view = new SimulatorView();
        TakeOffCommand takeOffCommand = new TakeOffCommand(flight, map, view);

        takeOffCommand.execute();

        verify(flight).takeOff();
    }
}
