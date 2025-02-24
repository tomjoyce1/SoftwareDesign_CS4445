package views;

import models.decorators.radardecorator.RadarDisplay;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;

class SimulatorViewTest {

    @Test
    void displayMenuPrintsAllMenuOptions() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        RadarDisplay dummyRadar = () -> { };
        SimulatorView view = new SimulatorView(dummyRadar);
        view.displayMenu();

        System.setOut(originalOut);
        String output = outContent.toString();
        // Updated expected string by removing the unnecessary backslash.
        assertTrue(output.contains("=== Main Menu ==="));
        assertTrue(output.contains("1. Create new flight"));
        assertTrue(output.contains("2. Control flight"));
        assertTrue(output.contains("3. Update weather"));
        assertTrue(output.contains("4. List all flights"));
        assertTrue(output.contains("5. Check Flight Status"));
        assertTrue(output.contains("Q. Quit"));
        assertTrue(output.contains("Choose an option:"));
    }

    @Test
    void getUserInputReturnsUpperCaseText() {
        String simulatedInput = "q";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        RadarDisplay dummyRadar = () -> { };
        SimulatorView view = new SimulatorView(dummyRadar);
        String input = view.getUserInput();
        System.setIn(originalIn);
        assertEquals("Q", input);
    }

    @Test
    void displayMessagePrintsProvidedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        RadarDisplay dummyRadar = () -> { };
        SimulatorView view = new SimulatorView(dummyRadar);
        view.displayMessage("Hello World");

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Hello World"));
    }

    @Test
    void displayRadarPrintsHeaderAndCallsRadarShow() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        RadarDisplay dummyRadar = () -> System.out.print("Radar called");
        SimulatorView view = new SimulatorView(dummyRadar);
        view.displayRadar();

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("--- Radar Display ---"));
        assertTrue(output.contains("Radar called"));
    }
}