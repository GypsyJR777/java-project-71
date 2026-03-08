package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);
        StringBuilder diff = new StringBuilder();
        diff.append("{\n");

        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data2.containsKey(key)) {
                diff.append(String.format("  - %s: %s%n", key, stringify(data1.get(key))));
            } else if (!data1.containsKey(key)) {
                diff.append(String.format("  + %s: %s%n", key, stringify(data2.get(key))));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                diff.append(String.format("    %s: %s%n", key, stringify(data1.get(key))));
            } else {
                diff.append(String.format("  - %s: %s%n", key, stringify(data1.get(key))));
                diff.append(String.format("  + %s: %s%n", key, stringify(data2.get(key))));
            }
        }

        diff.append("}");
        return diff.toString();
    }

    static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Path.of(filePath).toAbsolutePath().normalize();
        byte[] content = Files.readAllBytes(path);
        return OBJECT_MAPPER.readValue(content, new TypeReference<>() { });
    }

    private static String stringify(Object value) {
        return String.valueOf(value);
    }
}
