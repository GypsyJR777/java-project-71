package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DifferTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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

    @Test
    void shouldBuildPlainDiffForNestedJsonFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json",
            "plain"
        );

        assertEquals(getExpectedPlainDiff(), actual);
    }

    @Test
    void shouldBuildPlainDiffForNestedYmlFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml",
            "plain"
        );

        assertEquals(getExpectedPlainDiff(), actual);
    }

    @Test
    void shouldBuildJsonDiffForNestedJsonFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.json",
            "src/test/resources/fixtures/file2_nested.json",
            "json"
        );

        assertEquals(OBJECT_MAPPER.readTree(getExpectedJsonDiff()), OBJECT_MAPPER.readTree(actual));
    }

    @Test
    void shouldBuildJsonDiffForNestedYmlFiles() throws Exception {
        String actual = Differ.generate(
            "src/test/resources/fixtures/file1_nested.yml",
            "src/test/resources/fixtures/file2_nested.yml",
            "json"
        );

        assertEquals(OBJECT_MAPPER.readTree(getExpectedJsonDiff()), OBJECT_MAPPER.readTree(actual));
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

    private String getExpectedPlainDiff() {
        return """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
    }

    private String getExpectedJsonDiff() {
        return """
            [
              {
                "key" : "chars1",
                "status" : "UNCHANGED",
                "oldValue" : [ "a", "b", "c" ],
                "newValue" : [ "a", "b", "c" ]
              },
              {
                "key" : "chars2",
                "status" : "UPDATED",
                "oldValue" : [ "d", "e", "f" ],
                "newValue" : false
              },
              {
                "key" : "checked",
                "status" : "UPDATED",
                "oldValue" : false,
                "newValue" : true
              },
              {
                "key" : "default",
                "status" : "UPDATED",
                "oldValue" : null,
                "newValue" : [ "value1", "value2" ]
              },
              {
                "key" : "id",
                "status" : "UPDATED",
                "oldValue" : 45,
                "newValue" : null
              },
              {
                "key" : "key1",
                "status" : "REMOVED",
                "oldValue" : "value1",
                "newValue" : null
              },
              {
                "key" : "key2",
                "status" : "ADDED",
                "oldValue" : null,
                "newValue" : "value2"
              },
              {
                "key" : "numbers1",
                "status" : "UNCHANGED",
                "oldValue" : [ 1, 2, 3, 4 ],
                "newValue" : [ 1, 2, 3, 4 ]
              },
              {
                "key" : "numbers2",
                "status" : "UPDATED",
                "oldValue" : [ 2, 3, 4, 5 ],
                "newValue" : [ 22, 33, 44, 55 ]
              },
              {
                "key" : "numbers3",
                "status" : "REMOVED",
                "oldValue" : [ 3, 4, 5 ],
                "newValue" : null
              },
              {
                "key" : "numbers4",
                "status" : "ADDED",
                "oldValue" : null,
                "newValue" : [ 4, 5, 6 ]
              },
              {
                "key" : "obj1",
                "status" : "ADDED",
                "oldValue" : null,
                "newValue" : {
                  "nestedKey" : "value",
                  "isNested" : true
                }
              },
              {
                "key" : "setting1",
                "status" : "UPDATED",
                "oldValue" : "Some value",
                "newValue" : "Another value"
              },
              {
                "key" : "setting2",
                "status" : "UPDATED",
                "oldValue" : 200,
                "newValue" : 300
              },
              {
                "key" : "setting3",
                "status" : "UPDATED",
                "oldValue" : true,
                "newValue" : "none"
              }
            ]""";
    }
}
