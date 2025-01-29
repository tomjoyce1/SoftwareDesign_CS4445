public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        
        Flight privateFlight = FlightFactory.createFlight(FlightType.PRIVATE, "PF001");
        Flight passengerFlight = FlightFactory.createFlight(FlightType.PASSENGER, "PA001");
        Flight militaryFlight = FlightFactory.createFlight(FlightType.MILITARY, "MF001");
        
        // Take off some flights
        privateFlight.takeOff();
        passengerFlight.takeOff();
        militaryFlight.takeOff();
        
        System.out.println("\n=== Weather Updates ===");
        weatherStation.reportSunny("Visibility excellent, ceiling unlimited");
        weatherStation.reportStorm("Severe thunderstorm approaching");
        
        System.out.println("\n=== Checking States ===");
        System.out.println("Private flight: " + privateFlight.getState());
        System.out.println("Passenger flight: " + passengerFlight.getState());
        System.out.println("Military flight: " + militaryFlight.getState());
    }
}
