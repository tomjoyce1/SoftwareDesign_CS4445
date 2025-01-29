// flight class
/* flight factory which produces different factories
 * in flight it'll have attribute flight type e.g. military, passanger, cargo, private
 * 
 */
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