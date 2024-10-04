package ru.demo.data.dictionaries;

public enum ObjectLevels {
    CITY("Город"),
    CITY_PART("Часть города"),
    OKRUG("Округ"),
    DISTRICT("Район/Поселение"),
    BUILDING("Здание/Сооружение");
    private final String levelNumber;

    ObjectLevels(String levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getLevelName() {
        return levelNumber;
    }
}
