import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

    @Test
    void mainExecutionCompletesWithoutExceptionOnQuitInput() {
        String simulatedInput = "Q\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertDoesNotThrow(() -> Main.main(new String[]{}));

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}