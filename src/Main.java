public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        Flight basicFlight = FlightFactory.createFlight(FlightType.PRIVATE, "PF001");
        FuelDecorator privateFlight = new FuelDecorator(basicFlight);
        
        privateFlight.setFuelConsumptionRate(15);
        
        System.out.println("Flight type is: " + privateFlight.getType());
        System.out.println("Flight number is: " + privateFlight.getFlightNumber());
        System.out.println("Initial state is: " + privateFlight.getState());
        System.out.println("Initial fuel: " + privateFlight.getFuel());

        privateFlight.takeOff();
        System.out.println("Current state is: " + privateFlight.getState());
        System.out.println("Fuel after takeoff: " + privateFlight.getFuel());

        privateFlight.hold();
        privateFlight.land();
        System.out.println("Current state is: " + privateFlight.getState());

        privateFlight.land();
        System.out.println("Current state is: " + privateFlight.getState());
=======
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
>>>>>>> a7c100bad2b5cac0fe9dc6ef18099c7f45ff1220
    }
}
