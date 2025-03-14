package models.flight;

import models.map.MapCell;
import models.states.FlightState;
import models.states.InAirState;
import models.states.OnRunwayState;
import views.ConsoleLogger;
import weatherpubsub.Subscriber;
import weatherpubsub.WeatherBroker;

public abstract class Flight implements Subscriber {
    private final String flightNumber;
    private final WeatherBroker broker;
    private FlightState state;
    private int fuel = 100;
    private boolean stormNotified = false;
    private MapCell currentAirportCell;

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
        ConsoleLogger.logInfo(getType() + " " + flightNumber + " received " + topic + ": " + message);

        if (topic.equals("WEATHER.STORM")) {
            stormNotified = true;
            // Optionally, if in air, hold the flight:
            if (state instanceof InAirState) {
                ConsoleLogger.logWarning(getType() + " " + flightNumber + " holding at current location due to storm");
                hold();
            }
        } else if (topic.equals("WEATHER.SUNNY") || topic.equals("WEATHER.FOGGY")) {
            // Clear storm notification when weather improves
            stormNotified = false;
        }
    }

    public boolean isStormNotified() {
        return stormNotified;
    }

    public void setState(FlightState state) {
        this.state = state;
    }

    public String getState() {
        return state.getStateName();
    }

    public boolean takeOff() {
        if (stormNotified) {
            ConsoleLogger.logError(getType() + " " + flightNumber + " cannot take off due to storm conditions.");
            return false;
        }
        state.takeOff(this);
        consumeFuel();
        return true;
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

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void consumeFuel() {
        fuel -= 10;
    }
    
    public MapCell getCurrentAirportCell() {
        return currentAirportCell;
    }

    public void setCurrentAirportCell(MapCell currentAirportCell) {
        this.currentAirportCell = currentAirportCell;
    }
}
