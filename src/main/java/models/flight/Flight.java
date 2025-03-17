package models.flight;

import models.map.MapCell;
import models.states.FlightState;
import models.states.InAirState;
import models.states.OnRunwayState;
import views.ConsoleLogger;
import weatherpubsub.WeatherBroker;

public class Flight implements FlightInterface {
    private final String flightNumber;
    private final WeatherBroker broker;
    private FlightState state;
    private int fuel = 100;
    private boolean stormNotified = false;
    private boolean scheduled = false;
    private MapCell currentAirportCell;


    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
        this.state = new OnRunwayState();
        this.broker = WeatherBroker.getInstance();

        broker.subscribe("WEATHER.STORM", this);
        broker.subscribe("WEATHER.SUNNY", this);
        broker.subscribe("WEATHER.FOGGY", this);
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String getType() {
        return "Base Flight";
    }

    @Override
    public boolean takeOff() {
        if(!isOperable()) {
            return false;
        }
        if (!isTakeOffPermitted()) {
            return false;
        }
        boolean success = state.takeOff(this);
        if(success) {
        consumeFuel();
        }
        return success;
    }

    @Override
    public void land() {
        state.land(this);
    }

    @Override
    public void hold() {
        state.hold(this);
    }

    @Override
    public int getFuel() {
        return fuel;
    }

    @Override
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    @Override
    public void consumeFuel() {
        fuel -= 10;
    }

    @Override
    public String getState() {
        return state.getStateName();
    }

    @Override
    public void setState(FlightState newState) {
        this.state = newState;
    }

    @Override
    public boolean isStormNotified() {
        return stormNotified;
    }

    @Override
    public boolean isScheduled() {
        return scheduled;
    }

    @Override
    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public MapCell getCurrentAirportCell() {
        return currentAirportCell;
    }

    @Override
    public void setCurrentAirportCell(MapCell cell) {
        this.currentAirportCell = cell;
    }

    @Override
    public void receive(String topic, String message) {
        ConsoleLogger.logInfo(getType() + " " + flightNumber + " received " + topic + ": " + message);

        if ("WEATHER.STORM".equals(topic)) {
            stormNotified = true;
            if (state instanceof InAirState) {
                ConsoleLogger.logWarning(getType() + " " + flightNumber + " holding at current location due to storm");
                hold();
            }
        } else if ("WEATHER.SUNNY".equals(topic) || "WEATHER.FOGGY".equals(topic)) {
            stormNotified = false;
        }
    }

    private boolean isTakeOffPermitted() {
        if (stormNotified) {
            ConsoleLogger.logError(getType() + " " + flightNumber + " cannot take off due to storm conditions.");
            return false;
        }
        if (fuel <= 80) {
            ConsoleLogger.logError(getType() + " " + flightNumber + " cannot take off due to insufficient fuel (Fuel level: " + fuel + ").");
            return false;
        }
        return true;
    }

    private boolean isOperable() {
        if ("Crashed".equals(getState())) {
            ConsoleLogger.logError(getType() + " " + flightNumber + " has crashed and cannot perform further operations.");
            return false;
        }
        return true;
    }
}
