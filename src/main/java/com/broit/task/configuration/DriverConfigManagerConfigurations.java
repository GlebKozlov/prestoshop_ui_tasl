package com.broit.task.configuration;

import com.broit.task.manager.DefaultDriverConfigurationManager;
import com.broit.task.manager.DockerDriverConfigurationManager;
import com.broit.task.manager.DriverConfigurationManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableConfigurationProperties(DriverConfigurationProperties.class)
@Configuration
public class DriverConfigManagerConfigurations {
    @Bean
    @ConditionalOnMissingBean(DriverConfigurationManager.class)
    public DriverConfigurationManager defaultDriverConfigurationManager(DriverConfigurationProperties properties) {
        return new DefaultDriverConfigurationManager(properties);
    }

    @Bean
    @Profile("docker")
    public DriverConfigurationManager dockerDriverConfigurationManager(DriverConfigurationProperties properties) {
        return new DockerDriverConfigurationManager(properties);
    }
}