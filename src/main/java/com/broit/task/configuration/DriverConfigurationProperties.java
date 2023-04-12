package com.broit.task.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class DriverConfigurationProperties {

    private boolean driverManagerEnabled;
    private boolean enableVNC;
    private boolean webDriverLogsEnabled;
    private boolean enableVideo;
    private boolean driverHeadless;
    private String defaultUrl;
    private String remoteUrl;
    private String browser;
    private String browserSize;
}
