package ru.askir.limits.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "app")
public class PropertyConfig {
    private final BigDecimal sumLimit;

    public PropertyConfig(BigDecimal sumLimit) {
        this.sumLimit = sumLimit;
    }

    public BigDecimal getSumLimit() {
        return sumLimit;
    }
}
