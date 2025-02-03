public interface FlightState{
    void takeOff(Flight flight);
    void land(Flight flight);
    void hold(Flight flight);
    String getStateName();
}