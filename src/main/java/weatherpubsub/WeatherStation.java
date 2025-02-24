package weatherpubsub;

public class WeatherStation implements Publisher {
    // this is the pubsub publisher

    private final WeatherBroker broker;

    public WeatherStation() {
        broker = WeatherBroker.getInstance();
    }

    @Override
    public void publish(String topic, String message) {
        System.out.println("WeatherPubSub.WeatherStation publishing message: " + message + " to topic: " + topic);
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