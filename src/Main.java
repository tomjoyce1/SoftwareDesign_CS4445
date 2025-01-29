public class Main {
    public static void main(String[] args) {
        Flight privateFlight = FlightFactory.createFlight(FlightType.PRIVATE, "PF001");
        System.out.println("flight type is: " + privateFlight.getType());
        System.out.println("flight number is: " + privateFlight.getFlightNumber());
        System.out.println("Initial state is: " + privateFlight.getState());

        privateFlight.takeOff();
        System.out.println("Current state is: " + privateFlight.getState());

        privateFlight.hold();
        privateFlight.land();
        System.out.println("Current state is: " + privateFlight.getState());

        privateFlight.land();

        System.out.println("Current state is: " + privateFlight.getState());
        privateFlight.land();

    }
}
