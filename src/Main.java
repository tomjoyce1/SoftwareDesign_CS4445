public class Main {
    public static void main(String[] args) {
        Flight privateFlight = FlightFactory.createFlight(FlightType.PRIVATE, "PF001");
        System.out.println(privateFlight.getType());
        System.out.println(privateFlight.getFlightNumber());
    }
}
