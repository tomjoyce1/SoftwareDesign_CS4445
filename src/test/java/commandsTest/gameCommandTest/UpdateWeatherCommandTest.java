package commandsTest.gameCommandTest;

import bookmarks.InterceptorDispatcher;
import commands.gamecommand.UpdateWeatherCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import views.ConsoleLogger;
import views.SimulatorView;
import weatherpubsub.WeatherStation;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateWeatherCommandTest {

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

    private TestHandler testHandler;

    @BeforeEach
    void setUp() {
        testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);
    }

    @Test
    void executeCallsReportStormWhenOption1Selected() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("1", "StormDetails");

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        verify(weatherStation).reportStorm("StormDetails");
        verify(dispatcher).dispatch("1StormDetails");
    }

    @Test
    void executeCallsReportSunnyWhenOption2Selected() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("2", "SunnyDetails");

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        verify(weatherStation).reportSunny("SunnyDetails");
        verify(dispatcher).dispatch("2SunnyDetails");
    }

    @Test
    void executeCallsReportFoggyWhenOption3Selected() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("3", "FoggyDetails");

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        verify(weatherStation).reportFoggy("FoggyDetails");
        verify(dispatcher).dispatch("3FoggyDetails");
    }

    @Test
    void executePrintsInvalidWeatherTypeWhenOptionIsInvalid() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("invalid", "SomeDetails");

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        verify(dispatcher).dispatch("invalidSomeDetails");
        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("Invalid weather type!"));
    }
}