package models.Decorators.RadarDecorator;

public abstract class RadarDecorator implements RadarDisplay {
    protected RadarDisplay decoratedRadar;

    public RadarDecorator(RadarDisplay radar) {
        this.decoratedRadar = radar;
    }

    @Override
    public void show() {
        decoratedRadar.show();
    }
}
