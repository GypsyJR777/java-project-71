package hexlet.code;

public record DiffNode(String key, DiffStatus status, Object oldValue, Object newValue) {
}
