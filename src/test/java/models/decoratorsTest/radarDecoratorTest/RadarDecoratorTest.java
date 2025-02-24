package models.decoratorsTest.radarDecoratorTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import models.decorators.radardecorator.RadarDecorator;
import models.decorators.radardecorator.RadarDisplay;

class RadarDecoratorTest {

    @Test
    void decoratorDelegatesShowCallToDecoratedRadar() {
        DummyRadarDisplay dummy = new DummyRadarDisplay();
        RadarDecorator decorator = new RadarDecorator(dummy) {};
        decorator.show();
        assertTrue(dummy.isShowCalled());
    }

    @Test
    void decoratorDelegatesMultipleShowCallsToDecoratedRadar() {
        DummyRadarDisplay dummy = new DummyRadarDisplay();
        RadarDecorator decorator = new RadarDecorator(dummy) {};
        decorator.show();
        decorator.show();
        assertEquals(2, dummy.getShowCallCount());
    }

    private static class DummyRadarDisplay implements RadarDisplay {
        private int showCallCount = 0;

        @Override
        public void show() {
            showCallCount++;
        }

        public boolean isShowCalled() {
            return showCallCount > 0;
        }

        public int getShowCallCount() {
            return showCallCount;
        }
    }
}