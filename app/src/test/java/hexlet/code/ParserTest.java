package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ParserTest {
    private static final int FIRST_TIMEOUT = 50;
    private static final int SECOND_TIMEOUT = 20;

    @Test
    void shouldParseJsonByRelativePath() throws Exception {
        Map<String, Object> actual = Parser.parse("src/test/resources/fixtures/file1.json");

        assertEquals(getFirstFileData(), actual);
    }

    @Test
    void shouldParseJsonByAbsolutePath() throws Exception {
        Path path = Path.of("src/test/resources/fixtures/file2.json").toAbsolutePath().normalize();
        Map<String, Object> actual = Parser.parse(path.toString());

        assertEquals(getSecondFileData(), actual);
    }

    @Test
    void shouldParseYmlByRelativePath() throws Exception {
        Map<String, Object> actual = Parser.parse("src/test/resources/fixtures/file1.yml");

        assertEquals(getFirstFileData(), actual);
    }

    @Test
    void shouldParseYamlByRelativePath() throws Exception {
        Map<String, Object> actual = Parser.parse("src/test/resources/fixtures/file2.yaml");

        assertEquals(getSecondFileData(), actual);
    }

    private Map<String, Object> getFirstFileData() {
        return Map.of(
            "host", "hexlet.io",
            "timeout", FIRST_TIMEOUT,
            "proxy", "123.234.53.22",
            "follow", false
        );
    }

    private Map<String, Object> getSecondFileData() {
        return Map.of(
            "timeout", SECOND_TIMEOUT,
            "verbose", true,
            "host", "hexlet.io"
        );
    }
}
