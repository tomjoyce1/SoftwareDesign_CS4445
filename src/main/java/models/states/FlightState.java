package models.states;

    import factories.StateFactory;
    import models.flight.Flight;

    public interface FlightState {
        void takeOff(Flight flight);

        void land(Flight flight);

        void hold(Flight flight);

        String getStateName();

        default FlightState getNextState(String stateName) {
            return StateFactory.getState(stateName);
        }
    }