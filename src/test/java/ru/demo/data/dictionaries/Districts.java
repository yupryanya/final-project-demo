package ru.demo.data.dictionaries;

public enum Districts {
    ARBAT("0101", "Арбат"),
    AKADEMICHESKIY("0701", "Академический"),
    BASMANNYY("0102", "Басманный"),
    DANILOVSKKIY("0609", "Даниловский");
    private final String code,
            name;

    Districts(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getDistrictName() {
        return name;
    }
}
