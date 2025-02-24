package weatherPubSubTest;

import weatherpubsub.WeatherBroker;
import weatherpubsub.Subscriber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherBrokerTest {

    private static class DummySubscriber implements Subscriber {
        private final List<String> receivedMessages = new ArrayList<>();

        @Override
        public void receive(String topic, String message) {
            receivedMessages.add(topic + ":" + message);
        }

        public List<String> getReceivedMessages() {
            return receivedMessages;
        }
    }

    @Test
    public void singletonGetInstanceReturnsSameInstance() {
        WeatherBroker broker1 = WeatherBroker.getInstance();
        WeatherBroker broker2 = WeatherBroker.getInstance();
        assertSame(broker1, broker2);
    }

    @Test
    public void subscribeAddsSubscriberAndReceivesPublishedMessage() {
        WeatherBroker broker = WeatherBroker.getInstance();
        DummySubscriber subscriber = new DummySubscriber();
        broker.subscribe("WEATHER.SUNNY", subscriber);
        broker.publish("WEATHER.SUNNY", "Clear skies");
        assertFalse(subscriber.getReceivedMessages().isEmpty());
        assertTrue(subscriber.getReceivedMessages().getFirst().contains("Clear skies"));
    }

    @Test
    public void unsubscribeRemovesSubscriberAndStopsReceivingMessages() {
        WeatherBroker broker = WeatherBroker.getInstance();
        DummySubscriber subscriber = new DummySubscriber();
        broker.subscribe("WEATHER.FOGGY", subscriber);
        broker.unsubscribe("WEATHER.FOGGY", subscriber);
        broker.publish("WEATHER.FOGGY", "Low visibility");
        assertTrue(subscriber.getReceivedMessages().isEmpty());
    }

    @Test
    public void publishOnNonExistentTopicDoesNotThrowException() {
        WeatherBroker broker = WeatherBroker.getInstance();
        assertDoesNotThrow(() -> broker.publish("NON_EXISTENT", "Message"));
    }
}