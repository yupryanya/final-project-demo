package ru.demo.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/web/${web}.properties"
})

public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("114")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("selenoid.autotests.cloud")
    String remoteUrl();
}