package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(readContent(filePath1), getType(filePath1));
        Map<String, Object> data2 = Parser.parse(readContent(filePath2), getType(filePath2));
        List<DiffNode> diff = DiffBuilder.build(data1, data2);
        return Formatter.format(diff, format);
    }

    private static String readContent(String pathToData) throws Exception {
        Path path = Path.of(pathToData).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    private static String getType(String pathToData) {
        String name = Path.of(pathToData).getFileName().toString();
        int dotIndex = name.lastIndexOf('.');
        return name.substring(dotIndex + 1);
    }
}
