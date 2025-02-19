package Models;

import Models.Flight.Flight;
import WeatherPubSub.WeatherStation;
import Interceptors.InterceptorDispatcher;
import Interceptors.LoggingInterceptor;
import java.util.ArrayList;
import java.util.List;

public class SimulatorModel {
    private final WeatherStation weatherStation;
    private final List<Flight> flights;
    private final InterceptorDispatcher dispatcher;

    public SimulatorModel() {
        this.weatherStation = new WeatherStation();
        this.flights = new ArrayList<>();
        this.dispatcher = new InterceptorDispatcher();
        dispatcher.addInterceptor(new LoggingInterceptor());
    }
    
    public WeatherStation getWeatherStation() {
        return weatherStation;
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public InterceptorDispatcher getDispatcher() {
        return dispatcher;
    }
}