public class Main {
    public static void main(String[] args) {
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
    }
}
