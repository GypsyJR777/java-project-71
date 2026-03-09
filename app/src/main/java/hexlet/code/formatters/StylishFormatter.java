package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;

public class StylishFormatter {
    public static String format(List<DiffNode> diff) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (DiffNode node : diff) {
            switch (node.status()) {
                case REMOVED -> result.append(
                        String.format("  - %s: %s%n", node.key(), stringify(node.oldValue()))
                );
                case ADDED -> result.append(
                        String.format("  + %s: %s%n", node.key(), stringify(node.newValue()))
                );
                case UNCHANGED -> result.append(
                        String.format("    %s: %s%n", node.key(), stringify(node.oldValue()))
                );
                case UPDATED -> {
                    result.append(String.format("  - %s: %s%n", node.key(), stringify(node.oldValue())));
                    result.append(String.format("  + %s: %s%n", node.key(), stringify(node.newValue())));
                }
                default -> throw new IllegalStateException("Unsupported diff status: " + node.status());
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String stringify(Object value) {
        return String.valueOf(value);
    }
}
