package ru.demo.data.dictionaries;

public enum DictType {
    OBJ("obj"),
    FORM("form"),
    REPORT("report");

    private final String code;

    DictType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
