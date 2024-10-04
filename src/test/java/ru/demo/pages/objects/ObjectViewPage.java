package ru.demo.pages.objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ObjectViewPage {
    private final SelenideElement objectInformation = $(".common-info");

    @Step("The object with {values} fields should be displayed")
    public void objectWithRequiredFieldsisDisplayed(String ... values) {
        for (String value : values) {
            objectInformation.shouldHave(text(value));
        }
    }
}
