package com.broit.task.manager;

import com.broit.task.configuration.DriverConfigurationProperties;
import com.codeborne.selenide.Configuration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultDriverConfigurationManager implements DriverConfigurationManager {

    private final DriverConfigurationProperties properties;

    @Override
    public void setUp() {
        Configuration.browser = properties.getBrowser();
        Configuration.driverManagerEnabled = properties.isDriverManagerEnabled();
        Configuration.webdriverLogsEnabled = properties.isWebDriverLogsEnabled();
        Configuration.headless = properties.isDriverHeadless();
        Configuration.browserSize = properties.getBrowserSize();
    }
}