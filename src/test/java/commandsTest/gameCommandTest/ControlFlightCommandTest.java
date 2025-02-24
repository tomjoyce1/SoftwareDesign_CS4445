package commandsTest.gameCommandTest;

import commands.gamecommand.ControlFlightCommand;
import models.flight.Flight;
import views.SimulatorView;
import bookmarks.InterceptorDispatcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ControlFlightCommandTest {

    @Test
    void executePrintsNoFlightsAvailableWhenFlightsListIsEmpty() {
        List<Flight> flights = Collections.emptyList();
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        controlFlightCommand.execute();

        System.setOut(originalOut);
        assertTrue(outContent.toString().contains("No flights available!"));
    }

    @Test
    void executePrintsFlightNotFoundWhenFlightNumberIsInvalid() {
        Flight mockFlight = Mockito.mock(Flight.class);
        // Stub the flight number to a valid number.
        when(mockFlight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(mockFlight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher);

        when(view.getUserInput()).thenReturn("invalidFlightNumber");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        controlFlightCommand.execute();

        System.setOut(originalOut);
        assertTrue(outContent.toString().contains("Flight not found!"));
    }

    @Test
    void executeCallsTakeOffCommandWhenOptionOneIsSelected() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightNumber()).thenReturn("FL001");
        List<Flight> flights = List.of(flight);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher);

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
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher);

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
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher);

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
        ControlFlightCommand controlFlightCommand = new ControlFlightCommand(flights, view, dispatcher);

        when(view.getUserInput()).thenReturn("FL001").thenReturn("invalidOption");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        controlFlightCommand.execute();

        System.setOut(originalOut);
        assertTrue(outContent.toString().contains("Invalid option!"));
    }
}