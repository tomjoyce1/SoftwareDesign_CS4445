package WeatherPubSub;

public interface Subscriber {
    void receive(String topic, String message);
}