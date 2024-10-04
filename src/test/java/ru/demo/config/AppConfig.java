package ru.demo.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/env/${env}.properties"
})

public interface AppConfig extends Config {
    @Key("apiUrl" )
    @DefaultValue("https://bas.comfortsoft.ru/bas" )
    String apiUrl();

    @Key("webUrl" )
    @DefaultValue("https://bas.comfortsoft.ru/frontbas" )
    String webUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();
}