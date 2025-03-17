package weatherPubSubTest;

import weatherpubsub.WeatherStation;
import org.junit.jupiter.api.Test;
import views.ConsoleLogger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherStationTest {

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
    void weatherStationReportStormPrintsPublishedMessage() {
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        WeatherStation station = new WeatherStation();
        station.reportStorm("Severe storm incoming");

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("WeatherStation publishing message: Severe storm incoming to topic: WEATHER.STORM"));
    }

    @Test
    void weatherStationReportSunnyPrintsPublishedMessage() {
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        WeatherStation station = new WeatherStation();
        station.reportSunny("Bright and clear day");

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("WeatherStation publishing message: Bright and clear day to topic: WEATHER.SUNNY"));
    }

    @Test
    void weatherStationReportFoggyPrintsPublishedMessage() {
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        WeatherStation station = new WeatherStation();
        station.reportFoggy("Heavy fog expected");

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("WeatherStation publishing message: Heavy fog expected to topic: WEATHER.FOGGY"));
    }

    @Test
    void weatherStationHandlesNullMessageGracefully() {
        TestHandler testHandler = new TestHandler();
        ConsoleLogger.logger.addHandler(testHandler);
        ConsoleLogger.logger.setLevel(Level.ALL);

        WeatherStation station = new WeatherStation();
        station.reportSunny(null);

        String logOutput = testHandler.getLogMessages();
        assertTrue(logOutput.contains("WeatherStation publishing message: null to topic: WEATHER.SUNNY"));
    }
}