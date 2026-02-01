package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        Path path1 = FileUtil.resolvePath(filepath1);
        Path path2 = FileUtil.resolvePath(filepath2);

        Map<String, String> file1Map = FileUtil.readFileAsMap(path1);
        Map<String, String> file2Map = FileUtil.readFileAsMap(path2);
        Map<String, Diff> diffMap = getDiff(file1Map, file2Map);
        return buildResult(diffMap);
    }

    private static Map<String, Diff> getDiff(Map<String, String> source, Map<String, String> target) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(source.keySet());
        allKeys.addAll(target.keySet());
        Map<String, Diff> diffMap = new TreeMap<>();

        for (String key : allKeys) {
            String sourceValue = source.get(key);
            String targetValue = target.get(key);

            if (sourceValue != null && targetValue != null) {
                if (!Objects.equals(sourceValue, targetValue)) {
                    Diff updated = new Diff(DiffType.UPDATED, sourceValue, targetValue);
                    diffMap.put(key, updated);
                } else {
                    Diff notChanged = new Diff(DiffType.NOT_CHANGED, sourceValue);
                    diffMap.put(key, notChanged);
                }
            } else if (sourceValue != null) {
                Diff removed = new Diff(DiffType.REMOVED, sourceValue);
                diffMap.put(key, removed);
            } else {
                Diff added = new Diff(DiffType.ADDED, targetValue);
                diffMap.put(key, added);
            }
        }
        return diffMap;
    }

    private static String buildResult(Map<String, Diff> diffMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (Map.Entry<String, Diff> entry : diffMap.entrySet()) {
            String field = entry.getKey();
            Diff diff = entry.getValue();

            switch (diff.getType()) {
                case UPDATED -> {
                    sb.append("   - ").append(field).append(": ").append(diff.getValue()).append("\n");
                    sb.append("   + ").append(field).append(": ").append(diff.getNewValue()).append("\n");
                }
                case NOT_CHANGED -> sb.append("     ").append(field).append(": ").append(diff.getValue()).append("\n");
                case ADDED -> sb.append("   + ").append(field).append(": ").append(diff.getValue()).append("\n");
                case REMOVED -> sb.append("   - ").append(field).append(": ").append(diff.getValue()).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
