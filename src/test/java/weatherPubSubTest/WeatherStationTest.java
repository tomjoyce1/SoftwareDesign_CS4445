package weatherPubSubTest;

import weatherpubsub.WeatherStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class WeatherStationTest {

    @Test
    void weatherStationReportStormPrintsPublishedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        WeatherStation station = new WeatherStation();
        station.reportStorm("Severe storm incoming");

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("WeatherPubSub.WeatherStation publishing message: Severe storm incoming to topic: WEATHER.STORM"));
    }

    @Test
    void weatherStationReportSunnyPrintsPublishedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        WeatherStation station = new WeatherStation();
        station.reportSunny("Bright and clear day");

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("WeatherPubSub.WeatherStation publishing message: Bright and clear day to topic: WEATHER.SUNNY"));
    }

    @Test
    void weatherStationReportFoggyPrintsPublishedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        WeatherStation station = new WeatherStation();
        station.reportFoggy("Heavy fog expected");

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("WeatherPubSub.WeatherStation publishing message: Heavy fog expected to topic: WEATHER.FOGGY"));
    }

    @Test
    void weatherStationHandlesNullMessageGracefully() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        WeatherStation station = new WeatherStation();
        station.reportSunny(null);

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("WeatherPubSub.WeatherStation publishing message: null to topic: WEATHER.SUNNY"));
    }
}