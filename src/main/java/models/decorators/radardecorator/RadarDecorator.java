package models.decorators.radardecorator;

public abstract class RadarDecorator implements RadarDisplay {
    protected RadarDisplay decoratedRadar;

    protected RadarDecorator(RadarDisplay radar) {
        this.decoratedRadar = radar;
    }

    @Override
    public void show() {
        decoratedRadar.show();
    }
}
