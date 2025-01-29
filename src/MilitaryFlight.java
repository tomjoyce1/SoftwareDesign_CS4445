public class MilitaryFlight extends Flight {
    @Override
    public String getType() {
        return "MilitaryFlight";
    }

    public MilitaryFlight(String flightNumber) {
        super(flightNumber);
    }
}
