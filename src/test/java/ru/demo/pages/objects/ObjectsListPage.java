package ru.demo.pages.objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ObjectsListPage {
    private final String NO_OBJECTS_MESSAGE = "Записей не найдено";
    private final SelenideElement sidebarFilterButton = $("button[aria-controls='collapseFilter']"),
            searchInput = $("input[type='search']"),
            mainSearchSubmitButton = $("button[type='submit']"),
            addObjectButton = $("p-button[ng-reflect-label='Добавить']"),
            levelFilter = $(".level-wrapper"),
            elementsList = $(".p-datatable-tbody"),
            levelFilterDropdown = $(".p-dropdown-items"),
            objectsListPage = $("app-object-list");

    @Step("Open 'Objects' page")
    public void openPage() {
        open("/objects");
    }

    @Step("The 'Objects' page should be displayed")
    public void objectsListPageIsDisplayed() {
        objectsListPage.isDisplayed();
    }

    @Step("Expand the 'Sidebar filter' using filter button")
    public void expandSidebarFilter() {
        sidebarFilterButton.click();
    }

    @Step("Fill the upper 'Main search' field with '{value}'")
    public void fillTheMainSearchField(String value) {
        searchInput.setValue(value);
        mainSearchSubmitButton.click();
    }

    @Step("Click the 'New Object' button")
    public void clickNewObjectButton() {
        addObjectButton.click();
    }

    @Step("Set upper 'Level Filter' value to '{value}'")
    public void setLevelFilterValue(String value) {
        levelFilter.click();
        levelFilterDropdown.$(byText(value)).click();
    }

    @Step("Click the first element in objects list")
    public void clickFirstElementInList() {
        elementsList.$("a").click();
    }

    @Step("'No objects found' message should be displayed")
    public void noObjectsFoundMessageIsDisplayed() {
        elementsList.shouldHave(text(NO_OBJECTS_MESSAGE));
    }

    @Step("'No objects found' message should be displayed")
    public void valueIsDisplayedInObjectsList(String value) {
        elementsList.shouldHave(text(value));
    }
}