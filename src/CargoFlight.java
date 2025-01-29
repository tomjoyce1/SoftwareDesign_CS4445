public class CargoFlight extends Flight {
    @Override
    public String getType() {
        return "CargoFlight";
    }

    public CargoFlight(String flightNumber) {
        super(flightNumber);
    }
    
}
