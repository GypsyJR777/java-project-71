package hexlet.code;

import java.util.List;

public class Formatter {
    public static String format(List<DiffNode> diff, String formatName) {
        return switch (formatName) {
            case "stylish" -> StylishFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unsupported format: " + formatName);
        };
    }
}
