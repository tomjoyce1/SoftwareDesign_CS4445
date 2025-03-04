package modelsTest.decoratorsTest.radarDecoratorTest;

import static org.mockito.Mockito.mockStatic;

import models.decorators.radardecorator.BasicRadarDisplay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import views.ConsoleLogger;

class BasicRadarDisplayTest {

    private BasicRadarDisplay basicRadarDisplay;

    @BeforeEach
    void setUp() {
        basicRadarDisplay = new BasicRadarDisplay();
    }

    @Test
    void showDisplaysBasicRadarWithAircraftCoordinates() {
        try (var mockedLogger = mockStatic(ConsoleLogger.class)) {
            basicRadarDisplay.show();
            mockedLogger.verify(() -> ConsoleLogger.logInfo("Displaying basic radar with aircraft coordinates."));
        }
    }
}