package enums;

public enum FieldSize {
    FIELD_WIDTH_SIZE(260),
    HALF_FIELD_WIDTH_SIZE(130),
    FIELD_HEIGHT_SIZE(40),
    FIELD_SIZE_100(100),
    MARGIN(20);

    private int value;

    FieldSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
