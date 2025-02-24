package commandsTest.gameCommandTest;

import commands.gamecommand.QuitCommand;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuitCommandTest {

    @Test
    void executePrintsExitingMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        QuitCommand quitCommand = new QuitCommand();
        quitCommand.execute();

        System.setOut(originalOut);
        assertTrue(outContent.toString().contains("Exiting the system..."));
    }
}