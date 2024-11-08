package ru.askir.limits.dto;

import java.math.BigDecimal;

public record LimitRequest(Long userId, BigDecimal amount) {
}
