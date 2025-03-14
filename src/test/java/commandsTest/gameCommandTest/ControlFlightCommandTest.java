package commandsTest.gameCommandTest;

import commands.gamecommand.ControlFlightCommand;
import models.flight.Flight;
import models.map.AirTrafficMap;
import models.SimulatorModel;
import views.ConsoleLogger;
import views.SimulatorView;
import bookmarks.InterceptorDispatcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ControlFlightCommandTest {

    private static class TestHandler extends StreamHandler {
        private final StringBuilder logMessages = new StringBuilder();

        @Override
        public synchronized void publish(LogRecord logRecord) {
            logMessages.append(logRecord.getMessage()).append("\n");
        }

        public String getLogMessages() {
            return logMessages.toString();
        }
    }

    @Test
    void executePrintsNoFlightsAvailableWhenFlightsListIsEmpty() {
        List<Flight> flights = Collections.emptyList();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorModel model = Mockito.mock(SimulatorModel.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher, airTrafficMap, model);
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        controlFlightCommand.execute();

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("No flights available!"));
    }

    @Test
    void executePrintsFlightNotFoundWhenFlightNumberIsInvalid() {
        Flight mockFlight = Mockito.mock(Flight.class);
        when(mockFlight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(mockFlight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorModel model = Mockito.mock(SimulatorModel.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher, airTrafficMap, model);

        when(view.getUserInput()).thenReturn("invalidFlightNumber");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        controlFlightCommand.execute();

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("Flight not found!"));
    }

    @Test
    void executeCallsTakeOffCommandWhenOptionOneIsSelected() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(flight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorModel model = Mockito.mock(SimulatorModel.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher, airTrafficMap, model);

        when(view.getUserInput()).thenReturn("FL001").thenReturn("1");

        controlFlightCommand.execute();

        verify(flight).takeOff();
    }

    @Test
    void executeCallsLandCommandWhenOptionTwoIsSelected() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(flight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorModel model = Mockito.mock(SimulatorModel.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher, airTrafficMap, model);

        when(view.getUserInput()).thenReturn("FL001").thenReturn("2");

        controlFlightCommand.execute();

        verify(flight).land();
    }

    @Test
    void executeCallsHoldCommandWhenOptionThreeIsSelected() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(flight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorModel model = Mockito.mock(SimulatorModel.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher, airTrafficMap, model);

        when(view.getUserInput()).thenReturn("FL001").thenReturn("3");

        controlFlightCommand.execute();

        verify(flight).hold();
    }

    @Test
    void executePrintsInvalidOptionWhenInvalidOptionIsSelected() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(flight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        AirTrafficMap airTrafficMap = Mockito.mock(AirTrafficMap.class);
        SimulatorModel model = Mockito.mock(SimulatorModel.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher, airTrafficMap, model);

        when(view.getUserInput()).thenReturn("FL001").thenReturn("invalidOption");

        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        controlFlightCommand.execute();

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("Invalid option!"));
    }
}