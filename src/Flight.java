public abstract class Flight {
    private String flightNumber;
    private FlightState state;

    public String getFlightNumber() {
        return flightNumber;
    }
    
    public abstract String getType();

    public Flight (String flightNumber) {
        this.flightNumber = flightNumber;
        this.state = new OnRunwayState();
    }

    public void setState(FlightState state){
        this.state = state;
    }
    public String getState(){
        return state.getStateName();
    }

    public void takeOff() {
        state.takeOff(this);
    }

    public void land() {
        state.land(this);
    }
    
    public void hold() {
        state.hold(this);
    }
}
