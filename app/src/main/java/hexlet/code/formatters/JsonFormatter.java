package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import java.util.List;

public class JsonFormatter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to format diff as json", e);
        }
    public static String format(List<DiffNode> diff) throws Exception {
    }
}
