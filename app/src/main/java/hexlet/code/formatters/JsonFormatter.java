package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import java.util.List;

public class JsonFormatter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String format(List<DiffNode> diff) throws Exception {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }
}
