package main.java;
public class PassengerFlight extends Flight {
    @Override
    public String getType() {
        return "PassengerFlight";
    }

    public PassengerFlight(String flightNumber) {
        super(flightNumber);
    }
    
}
