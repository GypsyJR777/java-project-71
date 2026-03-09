package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DiffNode> diff) {
        List<String> lines = new ArrayList<>();

        for (DiffNode node : diff) {
            switch (node.status()) {
                case REMOVED -> lines.add(String.format("Property '%s' was removed", node.key()));
                case ADDED -> lines.add(
                    String.format("Property '%s' was added with value: %s", node.key(), stringify(node.newValue()))
                );
                case UPDATED -> lines.add(
                    String.format(
                        "Property '%s' was updated. From %s to %s",
                        node.key(),
                        stringify(node.oldValue()),
                        stringify(node.newValue())
                    )
                );
                case UNCHANGED -> {
                }
                default -> throw new IllegalStateException("Unsupported diff status: " + node.status());
            }
        }

        return String.join("\n", lines);
    }

    private static String stringify(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
