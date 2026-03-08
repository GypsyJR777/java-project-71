package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DifferTest {
    @Test
    void shouldReadAndParseJsonByRelativePath() throws Exception {
        var actual = Differ.getData("src/test/resources/fixtures/file1.json");

        var expected = Map.of(
            "host", "hexlet.io",
            "timeout", 50,
            "proxy", "123.234.53.22",
            "follow", false
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldReadAndParseJsonByAbsolutePath() throws Exception {
        var path = Path.of("src/test/resources/fixtures/file2.json").toAbsolutePath().normalize();
        var actual = Differ.getData(path.toString());

        var expected = Map.of(
            "timeout", 20,
            "verbose", true,
            "host", "hexlet.io"
        );

        assertEquals(expected, actual);
    }

    @Test
    void generateShouldReadBothFiles() {
        assertDoesNotThrow(() -> Differ.generate(
            "src/test/resources/fixtures/file1.json",
            "src/test/resources/fixtures/file2.json"
        ));
    }

    @Test
    void shouldBuildDiffForFlatJsonFiles() throws Exception {
        var actual = Differ.generate(
            "src/test/resources/fixtures/file1.json",
            "src/test/resources/fixtures/file2.json"
        );

        var expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        assertEquals(expected, actual);
    }

    @Test
    void shouldBuildSameDiffForAbsolutePaths() throws Exception {
        var path1 = Path.of("src/test/resources/fixtures/file1.json").toAbsolutePath().normalize();
        var path2 = Path.of("src/test/resources/fixtures/file2.json").toAbsolutePath().normalize();

        var actual = Differ.generate(path1.toString(), path2.toString());

        var expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        assertEquals(expected, actual);
    }
}
