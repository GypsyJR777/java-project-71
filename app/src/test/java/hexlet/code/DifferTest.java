package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DifferTest {
    @Test
    void generateShouldReadBothJsonFiles() {
        assertDoesNotThrow(() -> Differ.generate(
            "src/test/resources/fixtures/file1.json",
            "src/test/resources/fixtures/file2.json"
        ));
    }

    @Test
    void shouldBuildDiffForFlatJsonFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1.json",
            "src/test/resources/fixtures/file2.json"
        );

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldBuildSameJsonDiffForAbsolutePaths() throws Exception {
        Path path1 = Path.of("src/test/resources/fixtures/file1.json").toAbsolutePath().normalize();
        Path path2 = Path.of("src/test/resources/fixtures/file2.json").toAbsolutePath().normalize();

        String actual = Differ.generate(path1.toString(), path2.toString());

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldBuildDiffForFlatYmlFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1.yml",
            "src/test/resources/fixtures/file2.yml"
        );

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldBuildDiffForFlatYamlFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1.yaml",
            "src/test/resources/fixtures/file2.yaml"
        );

        assertEquals(getExpectedDiff(), actual);
    }

    private String getExpectedDiff() {
        return """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";
    }
}
