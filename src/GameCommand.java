import java.util.List;
import java.util.Scanner;

@FunctionalInterface
interface GameCommand extends Command {}

class CreateFlightCommand implements GameCommand {
    private List<Flight> flights;
    private Scanner scanner;
    private InterceptorDispatcher dispatcher;

    public CreateFlightCommand(List<Flight> flights, Scanner scanner, InterceptorDispatcher dispatcher) {
        this.flights = flights;
        this.scanner = scanner;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Create New Flight ===");
        System.out.println("Flight types: PRIVATE, PASSENGER, MILITARY, CARGO");
        System.out.print("Enter flight type: ");
        String typeStr = scanner.nextLine().toUpperCase();

        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine().toUpperCase();
        dispatcher.dispatch("Flight number is " + flightNumber + ", type is " + typeStr);

        try {
            FlightType type = FlightType.valueOf(typeStr);
            Flight flight = FlightFactory.createFlight(type, flightNumber);
            flights.add(flight);
            System.out.println("Created " + flight.getType() + " " + flightNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid flight type!");
        }
    }
}

class ControlFlightCommand implements GameCommand {
    private List<Flight> flights;
    private Scanner scanner;
    private InterceptorDispatcher dispatcher;

    public ControlFlightCommand(List<Flight> flights, Scanner scanner, InterceptorDispatcher dispatcher) {
        this.flights = flights;
        this.scanner = scanner;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        if (flights.isEmpty()) {
            System.out.println("No flights available!");
            return;
        }

        new ListFlightsCommand(flights).execute();
        System.out.print("Enter flight number to control: ");
        String flightNumber = scanner.nextLine().toUpperCase();

        Flight selectedFlight = flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);

        if (selectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.println("\n=== Flight Controls ===");
        System.out.println("1. Take off");
        System.out.println("2. Land");
        System.out.println("3. Hold");
        System.out.print("Choose action: ");

        String action = scanner.nextLine();
        dispatcher.dispatch("Controlling " + flightNumber + action);

        FlightCommand command = null;

        switch (action) {
            case "1":
                command = new TakeOffCommand(selectedFlight);
                break;
            case "2":
                command = new LandCommand(selectedFlight);
                break;
            case "3":
                command = new HoldCommand(selectedFlight);
                break;
            default:
                System.out.println("Invalid action!");
                return;
        }

        command.execute();
    }
}

class UpdateWeatherCommand implements GameCommand {
    private WeatherStation weatherStation;
    private Scanner scanner;
    private InterceptorDispatcher dispatcher;

    public UpdateWeatherCommand(WeatherStation weatherStation, Scanner scanner, InterceptorDispatcher dispatcher) {
        this.weatherStation = weatherStation;
        this.scanner = scanner;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Weather Control ===");
        System.out.println("1. Report Storm");
        System.out.println("2. Report Sunny");
        System.out.println("3. Report Foggy");
        System.out.print("Choose weather: ");

        String choice = scanner.nextLine();
        System.out.print("Enter weather details: ");
        String details = scanner.nextLine();
        dispatcher.dispatch(choice + details);

        switch (choice) {
            case "1":
                weatherStation.reportStorm(details);
                break;
            case "2":
                weatherStation.reportSunny(details);
                break;
            case "3":
                weatherStation.reportFoggy(details);
                break;
            default:
                System.out.println("Invalid weather type!");
        }
    }
}

class ListFlightsCommand implements GameCommand {
    private List<Flight> flights;

    public ListFlightsCommand(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Current Flights ===");
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        for (Flight flight : flights) {
            System.out.printf("%s %s - Status: %s%n",
                    flight.getType(),
                    flight.getFlightNumber(),
                    flight.getState());
        }
    }
}

class QuitCommand implements GameCommand {
    @Override
    public void execute() {
        System.out.println("Exiting the system...");
    }
}
