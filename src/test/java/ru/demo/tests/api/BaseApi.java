package ru.demo.tests.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.demo.api.objects.ObjectsApi;
import ru.demo.config.App;

public class BaseApi {
    protected final ObjectsApi objectsApi = new ObjectsApi();

    @BeforeAll
    static void init() {
        RestAssured.baseURI = App.appConfig.apiUrl();
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
