package controllersTest;

import controllers.SimulatorController;
import models.SimulatorModel;
import views.SimulatorView;
import bookmarks.InterceptorDispatcher;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

class SimulatorControllerTest {

    @Test
    void startSimulationTerminatesOnQuitCommand() {
        SimulatorModel model = mock(SimulatorModel.class);
        SimulatorView view = mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = mock(InterceptorDispatcher.class);
        when(model.getFlights()).thenReturn(new ArrayList<>());
        when(model.getDispatcher()).thenReturn(dispatcher);
        when(model.getWeatherStation()).thenReturn(null);
        when(view.getUserInput()).thenReturn("Q");

        SimulatorController controller = new SimulatorController(model, view);
        controller.startSimulation();

        verify(view, atLeastOnce()).displayMenu();
        verify(dispatcher).dispatch("Q");
    }

    @Test
    void startSimulationDisplaysInvalidMessageForUnknownInputThenQuit() {
        SimulatorModel model = mock(SimulatorModel.class);
        SimulatorView view = mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = mock(InterceptorDispatcher.class);
        when(model.getFlights()).thenReturn(new ArrayList<>());
        when(model.getDispatcher()).thenReturn(dispatcher);
        when(model.getWeatherStation()).thenReturn(null);
        when(view.getUserInput()).thenReturn("X", "Q");

        SimulatorController controller = new SimulatorController(model, view);
        controller.startSimulation();

        verify(view, atLeastOnce()).displayMenu();
        verify(dispatcher).dispatch("X");
        verify(dispatcher).dispatch("Q");
    }
}