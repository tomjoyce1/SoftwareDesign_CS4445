public class PrivateFlight extends Flight {
    @Override
    public String getType() {
        return "PrivateFlight";
    }

    public PrivateFlight(String flightNumber) {
        super(flightNumber);
    }
    
}
