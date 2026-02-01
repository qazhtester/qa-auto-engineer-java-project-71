package hexlet.code;

public final class Diff {
    private final DiffType type;
    private final String value;
    private String newValue;

    public Diff(DiffType diffType, String value, String newValue) {
        this.type = diffType;
        this.value = value;
        this.newValue = newValue;
    }

    public Diff(DiffType diffType, String value) {
        this.type = diffType;
        this.value = value;
    }

    public DiffType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getNewValue() {
        return newValue;
    }
}
