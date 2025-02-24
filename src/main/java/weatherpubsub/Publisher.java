package weatherpubsub;

public interface Publisher {
    void publish(String topic, String message);
}