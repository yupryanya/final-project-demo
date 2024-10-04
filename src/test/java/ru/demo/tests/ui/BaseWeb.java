package ru.demo.tests.ui;

import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.demo.api.objects.ObjectsApi;
import ru.demo.config.App;
import ru.demo.helpers.WebDriverProvider;
import ru.demo.helpers.Attach;
import ru.demo.pages.auth.LoginPage;
import ru.demo.pages.components.Header;
import ru.demo.pages.components.SidebarFilter;
import ru.demo.pages.main.MainPage;
import ru.demo.pages.objects.CreateObjectPage;
import ru.demo.pages.objects.ObjectViewPage;
import ru.demo.pages.objects.ObjectsListPage;

import static com.codeborne.selenide.Selenide.*;

public class BaseWeb {
    protected final LoginPage loginPage = new LoginPage();
    protected final Header header = new Header();
    protected final MainPage mainPage = new MainPage();
    protected final ObjectsListPage objectsListPage = new ObjectsListPage();
    protected final CreateObjectPage createObjectPage = new CreateObjectPage();
    protected final ObjectViewPage objectViewPage = new ObjectViewPage();
    protected final SidebarFilter sidebarFilter = new SidebarFilter();
    protected final ObjectsApi objectsApi = new ObjectsApi();

    @BeforeAll
    static void init() {
        WebDriverProvider.webDriverConfigInit();
        RestAssured.baseURI = App.appConfig.apiUrl();
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (App.webConfig.isRemote()) {
            Attach.addVideo();
        }
        closeWebDriver();
    }
}
