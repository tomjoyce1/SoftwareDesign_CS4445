package models.decoratorsTest.radarDecoratorTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import models.decorators.radardecorator.BasicRadarDisplay;

class BasicRadarDisplayTest {

    @Test
    void showDisplaysExpectedMessage() {
        BasicRadarDisplay display = new BasicRadarDisplay();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        display.show();
        System.out.flush();
        System.setOut(originalOut);
        assertEquals("Displaying basic radar with aircraft coordinates." + System.lineSeparator(), output.toString());
    }

    @Test
    void showCalledMultipleTimesPrintsMultipleMessages() {
        BasicRadarDisplay display = new BasicRadarDisplay();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        display.show();
        display.show();
        System.out.flush();
        System.setOut(originalOut);
        String expected = "Displaying basic radar with aircraft coordinates." + System.lineSeparator() +
                "Displaying basic radar with aircraft coordinates." + System.lineSeparator();
        assertEquals(expected, output.toString());
    }
}