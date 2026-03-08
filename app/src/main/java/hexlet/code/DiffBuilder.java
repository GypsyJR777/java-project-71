package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
    public static List<DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        List<DiffNode> nodes = new ArrayList<>();
        for (String key : keys) {
            if (!data2.containsKey(key)) {
                nodes.add(new DiffNode(key, DiffStatus.REMOVED, data1.get(key), null));
            } else if (!data1.containsKey(key)) {
                nodes.add(new DiffNode(key, DiffStatus.ADDED, null, data2.get(key)));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                nodes.add(new DiffNode(key, DiffStatus.UNCHANGED, data1.get(key), data2.get(key)));
            } else {
                nodes.add(new DiffNode(key, DiffStatus.UPDATED, data1.get(key), data2.get(key)));
            }
        }

        return nodes;
    }
}
