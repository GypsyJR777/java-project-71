package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DifferTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void shouldBuildDefaultDiffForJsonInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json"
        );

        assertEquals(readFixture("expected_stylish.txt"), actual);
    }

    @Test
    void shouldBuildDefaultDiffForYmlInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml"
        );

        assertEquals(readFixture("expected_stylish.txt"), actual);
    }

    @Test
    void shouldBuildStylishDiffForJsonInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json",
            "stylish"
        );

        assertEquals(readFixture("expected_stylish.txt"), actual);
    }

    @Test
    void shouldBuildStylishDiffForYmlInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml",
            "stylish"
        );

        assertEquals(readFixture("expected_stylish.txt"), actual);
    }

    @Test
    void shouldBuildPlainDiffForJsonInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json",
            "plain"
        );

        assertEquals(readFixture("expected_plain.txt"), actual);
    }

    @Test
    void shouldBuildPlainDiffForYmlInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml",
            "plain"
        );

        assertEquals(readFixture("expected_plain.txt"), actual);
    }

    @Test
    void shouldBuildJsonDiffForJsonInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json",
            "json"
        );

        assertEquals(OBJECT_MAPPER.readTree(readFixture("expected_json.json")), OBJECT_MAPPER.readTree(actual));
    }

    @Test
    void shouldBuildJsonDiffForYmlInput() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml",
            "json"
        );

        assertEquals(OBJECT_MAPPER.readTree(readFixture("expected_json.json")), OBJECT_MAPPER.readTree(actual));
    }

    private String readFixture(String name) throws Exception {
        return Files.readString(Path.of("src/test/resources/fixtures", name)).trim();
    }
}
