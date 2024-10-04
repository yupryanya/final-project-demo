package ru.demo.data.app;

public enum MenuItems {
    MAIN("Главная"),
    REPORTS("Отчеты"),
    DATA("Значения"),
    OBJECTS("Объекты"),
    LIBRARY("Библиотека документов"),
    QUEUE("История загрузок");

    private final String displayedName;

    MenuItems(String displayedName) { this.displayedName = displayedName; }

    public String getDisplayedName() { return displayedName; }
}
