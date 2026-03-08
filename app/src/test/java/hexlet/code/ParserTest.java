package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ParserTest {
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
            "timeout", 50,
            "proxy", "123.234.53.22",
            "follow", false
        );
    }

    private Map<String, Object> getSecondFileData() {
        return Map.of(
            "timeout", 20,
            "verbose", true,
            "host", "hexlet.io"
        );
    }
}
