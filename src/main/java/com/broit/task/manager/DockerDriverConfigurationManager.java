package com.broit.task.manager;

import com.broit.task.configuration.DriverConfigurationProperties;
import com.codeborne.selenide.Configuration;
import lombok.AllArgsConstructor;
import org.openqa.selenium.remote.DesiredCapabilities;

@AllArgsConstructor
public class DockerDriverConfigurationManager implements DriverConfigurationManager {

    private final DriverConfigurationProperties properties;

    @Override
    public void setUp() {
        Configuration.remote = properties.getRemoteUrl();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVideo", properties.isEnableVideo());
        capabilities.setCapability("enableVNC", properties.isEnableVNC());
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = properties.getBrowser();
        Configuration.headless = properties.isDriverHeadless();
        Configuration.browserSize = properties.getBrowserSize();
        Configuration.driverManagerEnabled = properties.isDriverManagerEnabled();
        Configuration.webdriverLogsEnabled = properties.isWebDriverLogsEnabled();
    }
}
