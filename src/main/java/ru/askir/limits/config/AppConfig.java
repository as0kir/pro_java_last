package ru.askir.limits.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(PropertyConfig.class)
public class AppConfig {
    private final PropertyConfig propertyConfig;

    public AppConfig(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    public PropertyConfig getPropertyConfig() {
        return propertyConfig;
    }
}
