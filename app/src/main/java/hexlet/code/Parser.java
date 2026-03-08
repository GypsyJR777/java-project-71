package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String filePath) throws Exception {
        Path path = Path.of(filePath).toAbsolutePath().normalize();
        byte[] content = Files.readAllBytes(path);
        ObjectMapper mapper = getMapper(path);
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(Path path) {
        String fileName = path.getFileName().toString();
        if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            return YAML_MAPPER;
        }
        return JSON_MAPPER;
    }
}
