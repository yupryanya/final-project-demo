package ru.demo.pages.objects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateObjectPage {
    private final String REQUIRED_MESSAGE = "Поле обязательно для заполнения";
    private final SelenideElement objectEditForm = $(".object-edit-form"),
            objectTypeInput = $("app-dict-input[formcontrolname='objType'] .p-dropdown-trigger"),
            commonDropdown = $(".im-options"),
            codeInput = $("input[formcontrolname='code']"),
            parentCodeInput = $("app-dict-input[formcontrolname='parentCode'] .p-dropdown-trigger"),
            addressInput = $("input[formcontrolname='address']"),
            nameInput = $("textarea[formcontrolname='name']"),
            submitButton = $("p-button[ng-reflect-label='Сохранить']"),
            objectCreatePage = $("app-object-create-page");

    @Step("Open 'New Object' page")
    public CreateObjectPage openPage() {
        open("/objects/new");
        return this;
    }

    @Step("The 'New Object' form should be displayed")
    public void newObjectFormIsDisplayed() {
        objectCreatePage.isDisplayed();
    }

    @Step("Set object type to '{value}'")
    public CreateObjectPage setObjectType(String value) {
        objectTypeInput.click();
        commonDropdown.$(byText(value)).click();
        return this;
    }

    @Step("Set object parent to '{value}'")
    public CreateObjectPage setParentCode(String value) {
        parentCodeInput.click();
        commonDropdown.$(byText(value)).click();
        return this;
    }

    @Step("Fill the object address field with '{value}'")
    public CreateObjectPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    @Step("Fill the object name field with '{value}'")
    public CreateObjectPage setObjectName(String value) {
        nameInput.setValue(value);
        return this;
    }

    @Step("Click the Submit button")
    public void clickSubmitButton() {
        submitButton.click();
    }

    @Step("Get object code from 'Code' field")
    public String getObjectCode() {
        return codeInput.getValue();
    }

    @Step("The required field error message should be displayed")
    public void requiredFieldErrorMessageIsDisplayed() {
        objectEditForm.shouldHave(text(REQUIRED_MESSAGE));
    }
}