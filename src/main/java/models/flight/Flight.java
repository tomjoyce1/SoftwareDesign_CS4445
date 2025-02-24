package models.flight;

import models.states.FlightState;
import models.states.InAirState;
import models.states.OnRunwayState;
import weatherpubsub.Subscriber;
import weatherpubsub.WeatherBroker;

public abstract class Flight implements Subscriber {
    private final String flightNumber;
    private final WeatherBroker broker;
    private FlightState state;
    private int fuel = 100;

    public String getFlightNumber() {
        return flightNumber;
    }

    public abstract String getType();

    protected Flight(String flightNumber) {
        this.flightNumber = flightNumber;
        this.state = new OnRunwayState();
        this.broker = WeatherBroker.getInstance();

        if (shouldSubscribeToWeather()) {
            subscribeToWeatherTopics();
        }
    }

    protected boolean shouldSubscribeToWeather() {
        return true;
    }

    private void subscribeToWeatherTopics() {
        broker.subscribe("WEATHER.STORM", this);
        broker.subscribe("WEATHER.SUNNY", this);
        broker.subscribe("WEATHER.FOG", this);
    }

    @Override
    public void receive(String topic, String message) {
        System.out.println(getType() + " " + flightNumber + " received " + topic + ": " + message);

        // If there's a storm and the flight is in the air, go into holding pattern
        if (topic.equals("WEATHER.STORM") && state instanceof InAirState) {
            System.out.println(getType() + " " + flightNumber + " holding at current location due to storm");
            hold();
        }
    }

    public void setState(FlightState state) {
        this.state = state;
    }

    public String getState() {
        return state.getStateName();
    }

    public void takeOff() {
        state.takeOff(this);
        consumeFuel();
    }

    public void land() {
        state.land(this);
    }

    public void hold() {
        state.hold(this);
    }

    public int getFuel() {
        return fuel;
    }

    public void consumeFuel() {
        fuel -= 10;
    }

    public void setFuel(int newFuel) {
        this.fuel = newFuel;
    }
}
