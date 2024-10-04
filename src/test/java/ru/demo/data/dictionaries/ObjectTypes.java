package ru.demo.data.dictionaries;

public enum ObjectTypes {
    BUILDING("06", "Здание"),
    LAND_PLOT("05", "Земельный участок");

    private final String code,
            name;

    ObjectTypes(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getObjectTypeCode() { return code; }

    public String getObjectTypeName() {
        return name;
    }
}
