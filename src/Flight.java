public abstract class Flight {
    private String flightNumber;

    public String getFlightNumber() {
        return flightNumber;
    }
    
    public abstract String getType();

    public Flight (String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
