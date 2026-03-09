package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {
    public static String format(List<DiffNode> diff, String formatName) {
        return switch (formatName) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unsupported format: " + formatName);
        };
    }
}
