package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DifferTest {
    @Test
    void shouldBuildStylishDiffForNestedJsonFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json"
        );

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldBuildStylishDiffForNestedYmlFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml"
        );

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldBuildStylishDiffForNestedYamlFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yaml",
            "src/test/resources/fixtures/file2_nested.yaml"
        );

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldBuildSameNestedJsonDiffForAbsolutePaths() throws Exception {
        Path path1 = Path.of("src/test/resources/fixtures/file1_nested.json").toAbsolutePath().normalize();
        Path path2 = Path.of("src/test/resources/fixtures/file2_nested.json").toAbsolutePath().normalize();

        String actual = Differ.generate(path1.toString(), path2.toString(), "stylish");

        assertEquals(getExpectedDiff(), actual);
    }

    @Test
    void shouldUseStylishAsDefaultFormat() {
        assertDoesNotThrow(() -> Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json"
        ));
    }

    private String getExpectedDiff() {
        return """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";
    }
}
