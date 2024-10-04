package ru.demo.helpers;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

import static com.codeborne.selenide.Configuration.*;
import static ru.demo.config.App.appConfig;
import static ru.demo.config.App.webConfig;

public class WebDriverProvider {
        public static void webDriverConfigInit() {
            pageLoadStrategy = "eager";
            browser = webConfig.browser();
            browserVersion = webConfig.browserVersion();
            browserSize = webConfig.browserSize();
            timeout = 15000;
            baseUrl = appConfig.webUrl();

            if (webConfig.isRemote()) {
                remote = "https://user1:1234@" + webConfig.remoteUrl() + "/wd/hub";
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser);
                capabilities.setCapability("browserVersion", browserVersion);
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
                browserCapabilities = capabilities;
            }
        }
}


