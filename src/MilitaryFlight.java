public class MilitaryFlight extends Flight {
    @Override
    public String getType() {
        return "MilitaryFlight";
    }

    @Override
    protected boolean shouldSubscribeToWeather() {
        return false;
    }

    public MilitaryFlight(String flightNumber) {
        super(flightNumber);
    }
}
