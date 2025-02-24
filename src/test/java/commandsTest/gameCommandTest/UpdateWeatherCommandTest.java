package commandsTest.gameCommandTest;

import commands.gamecommand.UpdateWeatherCommand;
import bookmarks.InterceptorDispatcher;
import weatherpubsub.WeatherStation;
import views.SimulatorView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UpdateWeatherCommandTest {

    @Test
    void executeCallsReportStormWhenOption1Selected() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("1", "StormDetails");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        System.setOut(originalOut);
        verify(weatherStation).reportStorm(eq("StormDetails"));
        verify(dispatcher).dispatch(eq("1StormDetails"));
    }

    @Test
    void executeCallsReportSunnyWhenOption2Selected() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("2", "SunnyDetails");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        System.setOut(originalOut);
        verify(weatherStation).reportSunny(eq("SunnyDetails"));
        verify(dispatcher).dispatch(eq("2SunnyDetails"));
    }

    @Test
    void executeCallsReportFoggyWhenOption3Selected() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("3", "FoggyDetails");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        System.setOut(originalOut);
        verify(weatherStation).reportFoggy(eq("FoggyDetails"));
        verify(dispatcher).dispatch(eq("3FoggyDetails"));
    }

    @Test
    void executePrintsInvalidWeatherTypeWhenOptionIsInvalid() {
        WeatherStation weatherStation = Mockito.mock(WeatherStation.class);
        SimulatorView view = Mockito.mock(SimulatorView.class);
        InterceptorDispatcher dispatcher = Mockito.mock(InterceptorDispatcher.class);
        when(view.getUserInput()).thenReturn("invalid", "SomeDetails");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        UpdateWeatherCommand command = new UpdateWeatherCommand(weatherStation, view, dispatcher);
        command.execute();

        System.setOut(originalOut);
        verify(dispatcher).dispatch(eq("invalidSomeDetails"));
        assertTrue(outContent.toString().contains("Invalid weather type!"));
    }
}