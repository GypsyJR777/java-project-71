package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String type) throws Exception {
        ObjectMapper mapper = getMapper(type);
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String type) throws Exception {
        if ("yaml".equals(type) || "yml".equals(type)) {
            return YAML_MAPPER;
        }
        if ("json".equals(type)) {
            return JSON_MAPPER;
        }
        throw new Exception("Unsupported data format: " + type);
    }
}
