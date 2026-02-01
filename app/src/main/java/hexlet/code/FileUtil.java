package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class FileUtil {

    private FileUtil() {
    }

    public static Path resolvePath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }

    public static Map<String, String> readFileAsMap(Path filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(bytes, new TypeReference<>() {
        });
    }
}
