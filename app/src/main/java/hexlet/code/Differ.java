package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);
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

    private static String stringify(Object value) {
        return String.valueOf(value);
    }
}
