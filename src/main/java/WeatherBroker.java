package main.java;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherBroker {
    private static WeatherBroker instance;
    private Map<String, List<Subscriber>> topics;

    private WeatherBroker() {
        topics = new HashMap<>();
        // Initialize default weather topics
        topics.put("WEATHER.STORM", new ArrayList<>());
        topics.put("WEATHER.SUNNY", new ArrayList<>());
        topics.put("WEATHER.FOGGY", new ArrayList<>());
    }

    public static WeatherBroker getInstance() {
        if (instance == null) {
            instance = new WeatherBroker();
        }
        return instance;
    }

    public void subscribe(String topic, Subscriber subscriber) {
        topics.computeIfAbsent(topic, k -> new ArrayList<>()).add(subscriber);
    }

    public void unsubscribe(String topic, Subscriber subscriber) {
        if (topics.containsKey(topic)) {
            topics.get(topic).remove(subscriber);
        }
    }

    public void publish(String topic, String message) {
        if (topics.containsKey(topic)) {
            topics.get(topic).forEach(subscriber -> subscriber.receive(topic, message));
        }
    }
} 