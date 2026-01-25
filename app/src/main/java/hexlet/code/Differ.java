package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        System.out.println(path1);
        System.out.println(path2);

        String file1Content = Files.readString(path1);
        String file2Content = Files.readString(path2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> file1Map = objectMapper.readValue(file1Content, new TypeReference<>() {
        });
        Map<String, String> file2Map = objectMapper.readValue(file2Content, new TypeReference<>() {
        });

        System.out.println(file1Map);
        System.out.println(file2Map);
        return "no result yet";
    }
}
