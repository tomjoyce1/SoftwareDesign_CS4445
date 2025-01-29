public abstract class Flight {
    private String flightNumber;

    public String getFlightNumber() {
        return flightNumber;
    }
    
    public abstract String getType();

    public Flight (String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void takeOff() {
        System.out.println("Taking off");
    }

    public void land() {
        System.out.println("Landing");
    }
}
