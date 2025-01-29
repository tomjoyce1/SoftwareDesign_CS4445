public class Flight {
    private int planeNumber;

    public Flight(int planeNumber) {
        this.planeNumber = planeNumber;
    }

    public void takeOff() {
        System.out.println(planeNumber + " is taking off");
    }

    public void land() {
        System.out.println(planeNumber + " is landing");
    }
}
