package WeatherPubSub;

public interface Publisher {
    void publish(String topic, String message);
}