package ru.demo.config;

import org.aeonbits.owner.ConfigFactory;

public class App {
    public static WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
    public static AppConfig appConfig = ConfigFactory.create(AppConfig.class, System.getProperties());
}