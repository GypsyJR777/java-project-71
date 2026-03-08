package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String generate(String filePath1, String filePath2) throws Exception {
        getData(filePath1);
        getData(filePath2);
        return "";
    }

    static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Path.of(filePath).toAbsolutePath().normalize();
        byte[] content = Files.readAllBytes(path);
        return OBJECT_MAPPER.readValue(content, new TypeReference<>() { });
    }
}
