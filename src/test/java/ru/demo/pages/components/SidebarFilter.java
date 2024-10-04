package ru.demo.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class SidebarFilter {
    private final SelenideElement adressInput = $("#uo-address"),
            submitButton = $("p-button[ng-reflect-label='Найти']");

    @Step("Fill the address field with '{value}'")
    public void setAddress(String value) {
        adressInput.setValue(value);
    }

    @Step("Click the Submit button in the Sidebar filter")
    public void clickSubmitButton() {
        submitButton.click();
    }
}
