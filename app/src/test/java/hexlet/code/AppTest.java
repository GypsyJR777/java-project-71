package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class AppTest {
    @Test
    void helpMessageShouldContainExpectedText() {
        OutputStream output = new ByteArrayOutputStream();
        CommandLine commandLine = new CommandLine(new App());

        commandLine.usage(new PrintWriter(output, true));

        String helpMessage = output.toString();
        assertTrue(helpMessage.contains("Usage: gendiff [-hV] [-f=format] filepath1 filepath2"));
        assertTrue(helpMessage.contains("Compares two configuration files and shows a difference."));
        assertTrue(helpMessage.contains("filepath1"));
        assertTrue(helpMessage.contains("path to first file"));
        assertTrue(helpMessage.contains("filepath2"));
        assertTrue(helpMessage.contains("path to second file"));
        assertTrue(helpMessage.contains("-f, --format=format"));
        assertTrue(helpMessage.contains("output format [default: stylish]"));
        assertTrue(helpMessage.contains("-h, --help"));
        assertTrue(helpMessage.contains("-V, --version"));
    }
}
