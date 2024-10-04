package ru.demo.pages.main;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class MainPage {
    @Step("Open Main page")
    public void openPage() {
        Selenide.open("/");
    }
}
