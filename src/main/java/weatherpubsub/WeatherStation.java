package weatherpubsub;

import views.ConsoleLogger;

public class WeatherStation implements Publisher {
    private final WeatherBroker broker;

    public WeatherStation() {
        broker = WeatherBroker.getInstance();
    }

    @Override
    public void publish(String topic, String message) {
        ConsoleLogger.logInfo("WeatherStation publishing message: " + message + " to topic: " + topic);
        broker.publish(topic, message);
    }

    public void reportStorm(String details) {
        publish("WEATHER.STORM", details);
    }

    public void reportSunny(String details) {
        publish("WEATHER.SUNNY", details);
    }

    public void reportFoggy(String details) {
        publish("WEATHER.FOGGY", details);
    }
}